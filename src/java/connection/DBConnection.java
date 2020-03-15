/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thien
 */
public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, Exception {
        Connection cnn;

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/yourphone";
        cnn = DriverManager.getConnection(url, "root", "123456");
        

        return cnn;
    }
}
