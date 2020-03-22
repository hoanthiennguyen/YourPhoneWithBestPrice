/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textprocessor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thien
 */
public class MyNode {

    String text;
    List<MyNode> children;
    List<String> childrenText;

    public MyNode() {
        text = "";
        children = new ArrayList<>();
        childrenText = new ArrayList<>();
    }

    public MyNode(String part) {
        this.text = part;
        children = new ArrayList<>();
        childrenText = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MyNode> getChildren() {
        return children;
    }

    public void setChildren(List<MyNode> children) {
        this.children = children;
    }

    public List<String> getChildrenText() {
        return childrenText;
    }

    public void setChildrenText(List<String> childrenText) {
        this.childrenText = childrenText;
    }

    public boolean childrenContains(String stringToBeAdded) {
        return childrenText.contains(stringToBeAdded);
    }

    public MyNode addChildText(String part) {
        if (findChildString(part) == null) {
            MyNode mynode = new MyNode(part);
            children.add(mynode);
            childrenText.add(part);
            return mynode;
        } else {
            return findChildString(part);
        }

    }

    public MyNode findChildString(String part) {
        for (int i = 0; i < children.size(); i++) {
            MyNode child = children.get(i);
            if (child.getText().equalsIgnoreCase(part)) {
                return child;
            }
        }
        return null;
    }
    @Override
    public String toString(){
        String result = "";
        for(String str: childrenText){
            result += str + ",";
        }
        return result;
    }
}
