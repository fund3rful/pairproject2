/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Andrew
 */
public class PatronInsert {
    String name;
    String address;
    String city;
    String stateCode;
    String zip;
    String email;
    String dateOfBirth;
    
    public PatronInsert(String name, String address, String city, String stateCode, 
                    String zip, String email, String dateOfBirth){
        this.address = address;
        this.city = city;
        this.name = name;
        this.stateCode = stateCode;
        this.zip = zip;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    
}
