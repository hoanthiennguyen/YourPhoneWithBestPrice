/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textprocessor;

import dao.CategoryDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author thien
 */
public class MyTreeUtil {
    public static MyTree createSearchTree() throws ClassNotFoundException, SQLException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<String> names = categoryDAO.getAllPhoneCategory();
        MyTree myTree = new MyTree();
        myTree.addNames(names);
        return myTree;
    }
    public static void updateSearchTree(MyTree myTree) throws ClassNotFoundException, SQLException{
        CategoryDAO categoryDAO = new CategoryDAO();
        List<String> names = categoryDAO.getAllPhoneCategory();
        myTree.addNames(names);
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
