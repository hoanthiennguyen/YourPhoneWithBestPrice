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
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection cnn;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database=yourphone";
        cnn = DriverManager.getConnection(url, "sa", "thien");
        

        return cnn;
    }
}
