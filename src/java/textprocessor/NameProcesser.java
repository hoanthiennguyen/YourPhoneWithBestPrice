/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textprocessor;

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
public class NameProcesser {

    private static List<String> getAllPhoneCategory() throws ClassNotFoundException, SQLException {
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

    private static void print(List<String> names, int begin, int end) {

        for (int i = begin; i < end && i < names.size(); i++) {
            System.out.println(i + ": " + names.get(i));
        }
    }

    public static MyTree createSearchTree() throws ClassNotFoundException, SQLException {
        List<String> names = getAllPhoneCategory();
        MyTree myTree = new MyTree();
        myTree.addNames(names);
        return myTree;
    }

    public static void main(String[] args) {
        try {
            MyTree myTree = createSearchTree();
            System.out.println(myTree.findString("Vsmart"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
