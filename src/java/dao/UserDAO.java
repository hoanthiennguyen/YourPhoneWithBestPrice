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

/**
 *
 * @author thien
 */
public class UserDAO {
    public boolean login(String username, String password) throws ClassNotFoundException, SQLException{
        boolean result = false;
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT username FROM admin WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        result = resultSet.next();
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return result;
    }
}
