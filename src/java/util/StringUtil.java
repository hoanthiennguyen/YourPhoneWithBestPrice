/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author thien
 */
public class StringUtil {
    public static String getXslFileNameFromWebsite(String website){
        return website.replaceAll("\\W", "") + ".xsl";
    }
    public static void main(String[] args){
        System.out.println(capitalize("i am Thien"));
    }
    public static String convertToXML(String names){
        String result = null;
        String[] arr = names.split(",");
        if(arr.length > 0){
            result = "<names>";
            for(String name: arr){
                result += "<name>" + name +"</name>";
            }
            result += "</names>";
        } 
        return result;
    }
    public static String capitalize(String src){
        StringBuilder buffer = new StringBuilder();
        boolean isAfterSpace = true;
        for(int i = 0;  i < src.length(); i++){
            String c = src.substring(i, i+1);
            if(isAfterSpace) 
                c = c.toUpperCase();
            buffer.append(c);
            isAfterSpace = c.equals(" ");
            
        }
        return buffer.toString();
    }
    public static String getCategoryFromRawName(String rawName) {
        String regex = "[^\\w /]";
        String[] arr = rawName.split(regex);
        return capitalize(arr[0].toLowerCase());
    }
}
