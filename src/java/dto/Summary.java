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
    
}
