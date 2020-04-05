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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thien
 */
public class CategoryDAO {
    public List<String> getAllPhoneCategory() throws ClassNotFoundException, SQLException {
        List<String> result = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT category from category";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String category = rs.getString("category");
            result.add(category);
        }
        return result;
    }
}
