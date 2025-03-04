/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import dto.Website;
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
public class WebsiteDAO {
    
    public List<String> getListWebsiteString() throws ClassNotFoundException, SQLException{
        Connection cnn = DBConnection.getConnection();
        String sql = "SELECT website FROM website";
        PreparedStatement preStm = cnn.prepareStatement(sql);
        ResultSet rs = preStm.executeQuery();
        List<String> result = new ArrayList<>();
        while(rs.next()){
            String website = rs.getString("website");
            result.add(website);
        }
        rs.close();
        preStm.close();
        cnn.close();
        return result;
    }
    public void insertWebsite(String website) throws ClassNotFoundException, SQLException{
        Connection cnn = DBConnection.getConnection();
        String sql = "INSERT INTO website(website) values(?)";
        PreparedStatement preStm = cnn.prepareStatement(sql);
        preStm.setString(1, website);
        preStm.executeUpdate();
        preStm.close();
        cnn.close();
    }
}
