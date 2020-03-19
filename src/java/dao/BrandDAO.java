/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thien
 */
public class BrandDAO {

    public String getListBrand() throws SQLException, ClassNotFoundException {
        String result = null;
        Connection cnn = DBConnection.getConnection();
//        String sql = "SELECT CONCAT('<brands>\n', "
//                + "     GROUP_CONCAT('  <brand>', brand, '</brand>\\n' SEPARATOR ''), \n"
//                + "    '</brands>') AS xmldoc \n"
//                + "    FROM yourphone.brand";
        String sql = "SELECT CONCAT( \n"
                + "     GROUP_CONCAT( brand SEPARATOR ',')\n"
                + "    ) AS xmldoc \n"
                + "    FROM yourphone.brand";
        PreparedStatement preStm = cnn.prepareStatement(sql);
        ResultSet rs = preStm.executeQuery();
        if (rs.next()) {
            result = rs.getString(1);
        }
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (cnn != null) {
            cnn.close();
        }
        return result;
    }

    public static void main(String[] args) {
        BrandDAO dao = new BrandDAO();
        try {
            System.out.println("Result: \n" + dao.getListBrand());
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
