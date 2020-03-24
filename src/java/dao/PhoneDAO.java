/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import constant.Hoangha;
import crawler.Crawler;
import dto.Phone;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author thien
 */
public class PhoneDAO {

    private Connection cnn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    private void closeConnnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException e) {

        }
    }

    public int insertPhoneList(List<Phone> list, String website, String subpage) throws Exception {
        int result = 0;
        cnn = DBConnection.getConnection();

        int subpageid = getSubpageId(website, subpage);
        for (Phone phone : list) {
            if (phone.getPrice() > 0 && !phone.getName().isEmpty()) {
                String sql = "UPDATE phone SET price = ?, updatedDate = ? WHERE name = ? AND subpageid = ?";
                preStm = cnn.prepareStatement(sql);
                preStm.setInt(1, phone.getPrice());
                preStm.setDate(2, new Date(System.currentTimeMillis()));
                preStm.setString(3, phone.getName());
                preStm.setInt(4, subpageid);
                if (preStm.executeUpdate() == 0) {
                    //phone does not exist
                    sql = "INSERT into phone(name, price, subpageid, link, updatedDate) values(?,?,?,?,?)";
                    preStm = cnn.prepareStatement(sql);
                    preStm.setString(1, phone.getName());
                    preStm.setInt(2, phone.getPrice());
                    preStm.setInt(3, subpageid);
                    preStm.setString(4, phone.getLink());
                    preStm.setDate(5, new Date(System.currentTimeMillis()));
                    preStm.execute();
                }
                result++;
            }

        }
        closeConnnection();
        return result;
    }

    private int getSubpageId(String website, String subpage) throws SQLException {
        int result = 0;
        String sql = "SELECT id FROM subpage WHERE website = ? AND subpage = ?";
        PreparedStatement localPreStm = cnn.prepareStatement(sql);
        localPreStm.setString(1, website);
        localPreStm.setString(2, subpage);
        ResultSet localRs = localPreStm.executeQuery();
        if (localRs.first()) {
            result = localRs.getInt("id");
        }
        if (localRs != null) {
            localRs.close();
        }
        if (localPreStm != null) {
            localPreStm.close();
        }
        return result;
    }

    private void deleteAllInSubpage(int id) throws SQLException {
        String sql = "DELETE FROM phone WHERE subpageid = ?";
        PreparedStatement localPreStm = cnn.prepareStatement(sql);
        localPreStm.setInt(1, id);
        localPreStm.execute();
        if (localPreStm != null) {
            localPreStm.close();
        }
    }

    public String searchAllPhoneWithPhoneName(String search) throws ClassNotFoundException, SQLException {
        String result = null;
        cnn = DBConnection.getConnection();
        String sql = "SELECT CONCAT('<phones>',\n"
                + "	GROUP_CONCAT('<phone><name>', name, '</name>',\n"
                + "				'<price>', price,'</price>',\n"
                + "                '<link>', link, '</link></phone>' SEPARATOR ''), \n"
                + "	'</phones>') AS xmldoc\n"
                + "FROM yourphone.phone WHERE name LIKE ?";
        preStm = cnn.prepareStatement(sql);
        preStm.setString(1, search + "%");
        rs = preStm.executeQuery();
        if (rs.next()) {
            result = rs.getString(1);
        }
        return result;
    }

    public static void main(String[] args) {
        PhoneDAO dao = new PhoneDAO();

        try {

            System.out.println(dao.searchAllPhoneWithPhoneName("Honor 8A"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
