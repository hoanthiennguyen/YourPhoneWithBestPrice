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
    public static String getDomainFromFullWebsite(String website){
        return website.replaceAll("\\W", "");
    }
    public static void main(String[] args){
        System.out.println(getDomainFromFullWebsite("https://bachlongmobile.com/"));
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
}
