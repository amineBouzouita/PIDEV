/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.myapp.entities.user ;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author amine
 */
public class userService {

    public static userService instance = null;
    String json;
    private boolean resultat;
    public ArrayList<user> users ;

    private ConnectionRequest req;

    public static userService getInstance() {
        if (instance == null) {
            instance = new userService();
        }
        return instance;
    }

    public userService() {
        req = new ConnectionRequest();
    }

    public boolean SignUp( TextField email, TextField password,  TextField address, TextField tel,  Resources res) {

        String url = Statics.BASE_URL + "inscription?mail="
                + email.getText().toString()
              
                + "&password=" + password.getText().toString()
               
                + "&address=" + address.getText().toString()
                + "&tel=" + tel.getText().toString()
                ;

        req.setUrl(url);

        if (email.getText().equals(" ")) {
            Dialog.show("Error", "field can't be empty", "OK", null);
        } else {
            resultat = req.getResponseCode() == 200;
        }

        req.addResponseListener((e) -> {
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

    public boolean SignIn(TextField email, TextField password, Resources res) {
        String url = Statics.BASE_URL + "connexion?email="
                + email.getText().toString()
                + "&password=" + password.getText().toString();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            try {
                if (json.equals("null")) {
                    Dialog.show("Error", "no user found", "OK", null);
                } else if (json.equals("incorrect password")) {
                    Dialog.show("Error", "no user found", "OK", null);
                } else {
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    float id = Float.parseFloat(user.get("id").toString());

                    SessionManager.setId((int) id);
                
                    SessionManager.setEmail(user.get("email").toString());
                    SessionManager.setPassowrd(user.get("password").toString());
                //    SessionManager.setRole(user.get("roles").toString());
                    SessionManager.setAddress(user.get("address").toString());
                    SessionManager.setTel(user.get("tel").toString());
                
                 

                    if (user.size() > 0) {
                        resultat = req.getResponseCode() == 200;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

    public void editUser(int id, String tel, String address, String password) {
        String url = Statics.BASE_URL + "edit-user?id=" + id + "&tel=" + tel + "&address=" + address + "&password=" + password ;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void editUseradmin(String id, String tel, String address, String password) {
        String url = Statics.BASE_URL + "edit-user?id=" + id + "&tel=" + tel + "&address=" + address + "&password=" + password ;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }


    public boolean deleteProduct(int id) {
        String url = Statics.BASE_URL + "supprimer-user?id=" + id;

        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }
    
    public boolean deleteUseradmin(String id) {
        String url = Statics.BASE_URL + "supprimer-user?id=" + id;

        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }
    
     public boolean resetpwd(String email) {
        String url = Statics.BASE_URL + "motpasseoublie?email=" + email;

        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }
     
     public ArrayList<user> getAllUsers() {
        String url = Statics.BASE_URL + "give-users";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return users;
    }
    
      public ArrayList<user> affichageUsers() {
        ArrayList<user> result = new ArrayList<>();
        String url = Statics.BASE_URL + "give-users";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        user ur = new user();
                        String email = obj.get("email").toString();
                        String tel = obj.get("tel").toString();
                        String address = obj.get("address").toString();
                        
                        
                        
                        ur.setEmail(email);
                        ur.setTel(tel);
                        ur.setAddress(address);
                     
                        

                        result.add(ur);
                    }
                } catch (Exception e) {
                    System.out.print("");
                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
