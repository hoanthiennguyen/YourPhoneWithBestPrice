/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author thien
 */
public class Summary implements Serializable{
    String category;
    int averagePrice;
    int numOfWebsites;

    public Summary() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    

    public int getNumOfWebsites() {
        return numOfWebsites;
    }

    public void setNumOfWebsites(int numOfWebsite) {
        this.numOfWebsites = numOfWebsite;
    }

    public Summary(String category, int averagePrice, int numOfWebsites) {
        this.category = category;
        this.averagePrice = averagePrice;
        this.numOfWebsites = numOfWebsites;
    }
    public String getDisplayPrice(){
        String rawPrice = String.valueOf(averagePrice);
        int n = rawPrice.length();
        
        if(n > 6){
            String part_1 = rawPrice.substring(0,n-6);
            String part_2 = rawPrice.substring(n-6, n - 3);
            String part_3 = rawPrice.substring(n-3);
            return part_1 + "." + part_2 + "." + part_3 + " ₫";
        }
        else {
            String part_1 = rawPrice.substring(0,n-3);
            String part_2 = rawPrice.substring(n-3);
            return part_1 + "." + part_2 + " ₫";
        }
    }
}
