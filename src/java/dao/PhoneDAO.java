/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import dto.Phone;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import util.StringUtil;

/**
 *
 * @author thien
 */
public class PhoneDAO {

    private Connection cnn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public int savePhoneList(List<Phone> list, String website) throws Exception {
        int result = 0;
        String sql;
        cnn = DBConnection.getConnection();

        for (Phone phone : list) {
            if (phone.getPrice() > 0 && !phone.getName().isEmpty()) {
                sql = "UPDATE phone SET price = ?, updatedDate = ?, img = ? WHERE name = ? AND website = ?";
                preStm = cnn.prepareStatement(sql);
                preStm.setInt(1, phone.getPrice());
                preStm.setDate(2, new Date(System.currentTimeMillis()));
                preStm.setString(3, phone.getImg());
                preStm.setString(4, phone.getName());
                preStm.setString(5, website);
                if (preStm.executeUpdate() == 0) {
                    insertNewPhone(phone, website);
                }
                preStm.close();
                result++;
            }

        }
        cnn.close();
        return result;
    }

    private void insertNewPhone(Phone phone, String website) throws SQLException {
        String category = StringUtil.getCategoryFromRawName(phone.getName());
        if (!checkCategory(category)) {
            insertNewCategory(category);
        }
        String sql = "INSERT INTO phone(name, price, link, updatedDate, category, img, website) values(?,?,?,?,?,?,?)";
        preStm = cnn.prepareStatement(sql);
        preStm.setString(1, phone.getName());
        preStm.setInt(2, phone.getPrice());
        preStm.setString(3, phone.getLink());
        preStm.setDate(4, new Date(System.currentTimeMillis()));
        preStm.setString(5, category);
        preStm.setString(6, phone.getImg());
        preStm.setString(7, website);
        preStm.execute();
    }

    private void insertNewCategory(String category) throws SQLException {
        String sql = "INSERT INTO category(category) values(?)";
        PreparedStatement preparedStatement = cnn.prepareStatement(sql);
        preparedStatement.setString(1, category);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private boolean checkCategory(String category) throws SQLException {
        String sql = "SELECT category FROM category WHERE category = ?";
        PreparedStatement preparedStatement = cnn.prepareStatement(sql);
        preparedStatement.setString(1, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();

    }

    public String searchAllPhoneWithPhoneName(String search) throws ClassNotFoundException, SQLException {
        String result = null;
        cnn = DBConnection.getConnection();
        String sql = "select cast ((SELECT img,name,price,website,link FROM phone"
                + " WHERE name like ? "
                + " order by price FOR XML PATH('phone'), ROOT('phones'))"
                + " as nvarchar(Max))";
        preStm = cnn.prepareStatement(sql);
        preStm.setString(1, search + "%");
        rs = preStm.executeQuery();
        if (rs.next()) {
            result = rs.getString(1);
        }
        rs.close();
        preStm.close();
        cnn.close();
        return result;
    }

    public static void main(String[] args) {
        PhoneDAO dao = new PhoneDAO();

        try {

            System.out.println(dao.searchAllPhoneWithPhoneName("Nokia 106"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
