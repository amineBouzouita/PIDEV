/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author louay
 */
public class user {

    private int id;
    private String email;
    private String password;
    private String tel;
    private String address;
    public user(){
        
    }
    public user(String mail, String password, String tel, String address) {
        this.email = mail;
        this.password = password;
        this.tel = tel;
        this.address = address;
        this.password = password;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 
    @Override
    public String toString() {
        return "user{" + "id=" + id + ", adresse mail=" + email + ", tel=" + tel + '}';
    }
}
