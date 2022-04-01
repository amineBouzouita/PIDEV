/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.userService;
import java.util.Vector;

/**
 *
 * @author louay
 */
public class adminadd extends Form {

    public adminadd(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite")
        );

        getTitleArea().setUIID("Container");

        TextField email = new TextField("", "email", 20, TextField.EMAILADDR);
        TextField tel = new TextField("", "tel", 20, TextField.ANY);
        TextField address = new TextField("", "address", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);

        email.getAllStyles().setMargin(LEFT, 0);
        tel.getAllStyles().setMargin(LEFT, 0);
        address.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);

        Label emailIcon = new Label("", "TextField");
        Label telIcon = new Label("", "TextField");
        Label addressIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");

        emailIcon.getAllStyles().setMargin(RIGHT, 0);
        telIcon.getAllStyles().setMargin(RIGHT, 0);
        addressIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);

        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_MAIL_OUTLINE, 3);
        FontImage.setMaterialIcon(telIcon, FontImage.MATERIAL_PHONE, 3);
        FontImage.setMaterialIcon(addressIcon, FontImage.MATERIAL_CODE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

     /*   Vector<String> vectorRole;
        vectorRole = new Vector();
        vectorRole.add("Coach");
        vectorRole.add("Client");

        ComboBox<String> roles;
        roles = new ComboBox<>(vectorRole); */

        Button adduser = new Button("login");
        adduser.setUIID("LoginButton");
        adduser.addActionListener(e -> {
            userService.getInstance().SignUp(email, password,  address, tel,  theme);
            if (userService.getInstance().SignUp(email, password,  address, tel,  theme)) {
                Dialog.show("success", "connected", "OK", null);
                new adminuserprofile(theme).show();
            }
        });

       

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
               
                BorderLayout.center(tel).
                        add(BorderLayout.WEST, telIcon),
                BorderLayout.center(address).
                        add(BorderLayout.WEST, addressIcon),
               // BorderLayout.center(roles),
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                adduser
                
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
