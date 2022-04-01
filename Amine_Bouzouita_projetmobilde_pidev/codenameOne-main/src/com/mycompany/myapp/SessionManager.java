/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.io.Preferences;

/**
 *
 * @author amine
 */
public class SessionManager {

    public static Preferences pref;

    private static int id;

    private static String email;
   
    private static String password;
    //private static String role;
    private static String tel;
    private static String address;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id", id);
    }

    public static void setId(int id) {
        pref.set("id", id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    
/*    public static String getRoles() {
        return pref.get("role", role);
    }

    public static void setRole(String role) {
        pref.set("role", role);
    }
*/

   

    public static String getAddress() {
        return pref.get("address", address);
    }

    public static void setAddress(String address) {
        pref.set("address", address);
    }

    public static String getTel() {
        return pref.get("tel", tel);
    }

    public static void setTel(String tel) {
        pref.set("tel", tel);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd", password);
    }

    public static void setPassowrd(String passowrd) {
        pref.set("passowrd", passowrd);
    }

}
