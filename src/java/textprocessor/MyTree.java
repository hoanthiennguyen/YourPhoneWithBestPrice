/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textprocessor;

import java.util.List;

/**
 *
 * @author thien
 */
public class MyTree {
    MyNode root;

    public MyTree() {
        root = new MyNode();
    }
    public void addName(String name){
        String[] parts = name.split(" ");
        MyNode current = root;
        for(String part: parts){
            current = current.addChildText(part);
        }
    }
    public void addNames(List<String> names){
        names.forEach(this::addName);
    }
    public MyNode findString(String search){
        if(search == null || search.isEmpty())
            return root;
        MyNode current = root;
        String[] parts = search.split(" ");
        for(String part: parts){
            current = current.findChildString(part);
        }
        return current;
    }
    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        String s1 = "Samsung Galaxy A10";
        String s2 = "Samsung Galaxy A20";
        String s3 = "Oppo A9";
        String s4 = "Samsung Note 7";
        String s5 = "Samsung Galaxy A50";
        myTree.addName(s1);
        myTree.addName(s2);
        myTree.addName(s3);
        myTree.addName(s4);
        myTree.addName(s5);
        MyNode child1 = myTree.findString("Samsung Galaxy");
        
        
        
    }
    
}
