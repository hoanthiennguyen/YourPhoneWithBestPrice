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

    private static List<String> getAllPhoneName() throws ClassNotFoundException, SQLException {
        List<String> result = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT name from phone";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String rawName = rs.getString("name");
            String name = getNameFromRawName(rawName);
            result.add(name);
        }
        return result;
    }

    private static void print(List<String> names, int begin, int end) {

        for (int i = begin; i < end && i < names.size(); i++) {
            System.out.println(i + ": " + names.get(i));
        }
    }

    private static String getNameFromRawName(String rawName) {
        String regex = "[^\\w /]";
        String[] arr = rawName.split(regex);
        return arr[0];
    }

    public static MyTree createSearchTree() throws ClassNotFoundException, SQLException {
        List<String> names = getAllPhoneName();
        MyTree myTree = new MyTree();
        myTree.addNames(names);
        return myTree;
    }
   

    public static void main(String[] args) {
        try {
            MyTree myTree = createSearchTree();
            System.out.println(myTree.findString("iPhone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
