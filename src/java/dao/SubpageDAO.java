/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import dto.Subpage;
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
public class SubpageDAO {
    public List<Subpage> getListSubpage() throws SQLException, ClassNotFoundException{
        Connection cnn = DBConnection.getConnection();
        String sql = "SELECT website,subpage,id FROM subpage";
        PreparedStatement preStm = cnn.prepareStatement(sql);
        ResultSet rs = preStm.executeQuery();
        List<Subpage> result = new ArrayList<>();
        while(rs.next()){
            String website = rs.getString("website");
            String subpage = rs.getString("subpage");
            int id = rs.getInt("id");
            result.add(new Subpage(website, subpage, id));
        }
        rs.close();
        preStm.close();
        cnn.close();
        return result;
    }
    public List<String> getListSubpageFrom(String website) throws SQLException, ClassNotFoundException{
        Connection cnn = DBConnection.getConnection();
        String sql = "SELECT subpage FROM subpage WHERE website = ?";
        PreparedStatement preStm = cnn.prepareStatement(sql);
        preStm.setString(1, website);
        ResultSet rs = preStm.executeQuery();
        List<String> result = new ArrayList<>();
        while(rs.next()){
            String subpage = rs.getString("subpage");
            result.add(subpage);
        }
        rs.close();
        preStm.close();
        cnn.close();
        return result;
    }
    public void insertSubpages(String website, String[] subpages) throws ClassNotFoundException, SQLException{
        
        Connection cnn = DBConnection.getConnection();
        String sql = "INSERT INTO subpage(website, subpage) values(?,?)";
        PreparedStatement preStm = cnn.prepareStatement(sql);
        preStm.setString(1, website);
        for(String subpage: subpages){
            preStm.setString(2, subpage);
            preStm.executeUpdate();
        }
    }
}
