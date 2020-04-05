/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dto.Summary;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author thien
 */
public class StringUtil {
    public static String getXslFileNameFromWebsite(String website){
        return website.replaceAll("\\W", "") + ".xsl";
    }
    public static void main(String[] args){
        System.out.println(getCategoryFromRawName("Vivo Y15 - 99%"));
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
        String regex = "[\\[\\(-]";
        String[] arr = rawName.split(regex);
        return capitalize(arr[0].toLowerCase());
    }
    public static StringBuilder convertToCSV(List<Summary> list, int numOfWebsite){
        StringBuilder writer = new StringBuilder();
        writer.append("No.");
        writer.append(',');
	writer.append("Category");
	writer.append(',');
	writer.append("Average Price (VND)");
	writer.append(',');
	writer.append("Number of websites selling (Total: ").append(numOfWebsite).append(")");
        writer.append(',');
        writer.append("Last updated: ").append(new Date(System.currentTimeMillis()));
	writer.append('\n');
        if(list != null){
            Summary summary;
            for(int i = 0; i < list.size(); i++){
                summary = list.get(i);
                writeSummaryToCSV(writer, i, summary);
            }
        }
        return writer;
    }

    private static void writeSummaryToCSV(StringBuilder writer, int i, Summary summary) {
        writer.append(i+1);
        writer.append(",");
        writer.append(summary.getCategory());
        writer.append(",");
        writer.append(summary.getAveragePrice());
        writer.append(",");
        writer.append(summary.getNumOfWebsites());
        writer.append('\n');
    }
    public static String createCrawlingInfo(String websiteCrawled, int numOfCrawl, int numOfSave){
        int numOfInvalid = numOfCrawl - numOfSave;
        return "From " + websiteCrawled + "<br/> Crawl " + numOfCrawl + " item(s) " + ", save to DB: " + numOfSave + " item(s), "
                    + numOfInvalid + " invalid item(s)";
    }
}
