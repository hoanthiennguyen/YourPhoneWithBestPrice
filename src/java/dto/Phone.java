/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author thien
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "phone" ,propOrder = {
    "name",
    "price",
    "link",
    "img"
})
public class Phone {
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected int price;
    @XmlElement (required = true)
    protected String link;
    @XmlElement (required = true)
    protected String img;
    public Phone() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getPriceDisplay(){
        String textPrice = price + "";
        int length = textPrice.length();
        int index = length - 1;
        
        return textPrice;
    }
}
