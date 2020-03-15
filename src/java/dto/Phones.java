/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author thien
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "phone"
})
@XmlRootElement(name = "phones")
public class Phones{
    @XmlElement(name = "phone")  
    List<Phone> phone;
    
    public List<Phone> getList() {
        return phone;
    }

    public void setList(List<Phone> phone) {
        this.phone = phone;
    }

    public Phones() {
    }

    
}
