/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author thien
 */
public class Subpage {
    String website;
    String subpage;
    int id;

    public Subpage(String website, String subpage) {
        this.website = website;
        this.subpage = subpage;
    }

    public Subpage(String website, String subpage, int id) {
        this.website = website;
        this.subpage = subpage;
        this.id = id;
    }
    
    public Subpage() {
    }
    

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSubpage() {
        return subpage;
    }

    public void setSubpage(String subpage) {
        this.subpage = subpage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
