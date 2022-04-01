/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;


import com.mycompany.myapp.services.userService;

/**
 *
 * @author louay
 */
public class adminedit extends SideMenuBaseForm {

    public adminedit(Resources res) {
        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Edit profile", "Title")
                        )
                )
        );

        tb.setTitleComponent(titleCmp);
        
         TextField id = new TextField( "id ...",TextField.NUMERIC);
        id.getStyle().setBgColor(0x000000);
        id.getStyle().setFgColor(0x000000);
        id.getStyle().setBorder(Border.createRoundBorder(50, 50));
        id.getStyle().setElevation(1);
        id.getStyle().setPadding(3, 3, 0, 0);
        id.getStyle().setUnderline(false);

        TextField tel = new TextField( "tel ...");
        tel.getStyle().setBgColor(0x000000);
        tel.getStyle().setFgColor(0x000000);
        tel.getStyle().setBorder(Border.createRoundBorder(50, 50));
        tel.getStyle().setElevation(1);
        tel.getStyle().setPadding(3, 3, 0, 0);
        tel.getStyle().setUnderline(false);

        TextField address = new TextField( "address...");
        address.getStyle().setBgColor(0x000000);
        address.getStyle().setFgColor(0x000000);
        address.getStyle().setBorder(Border.createRoundBorder(50, 50));
        address.getStyle().setElevation(1);
        address.getStyle().setPadding(3, 3, 0, 0);
        address.getStyle().setUnderline(false);

        TextField password = new TextField("", "password ...", 20, TextField.PASSWORD);
        password.getStyle().setBgColor(0x000000);
        password.getStyle().setFgColor(0x000000);
        password.getStyle().setBorder(Border.createRoundBorder(50, 50));
        password.getStyle().setElevation(1);
        password.getStyle().setPadding(3, 3, 0, 0);
        password.getStyle().setUnderline(false);

       

       
        Button addPub = new Button("Edit");
        addPub.getStyle().setBgColor(0xffffff);
        addPub.getStyle().setFgColor(0x0583D2);
        addPub.getStyle().setBgTransparency(255);
        addPub.getStyle().setBorder(Border.createRoundBorder(30, 30));
        addPub.getStyle().setMargin(13, 13, 80, 80);
        addPub.getStyle().setPadding(3, 3, 0, 0);

        Container pub = BoxLayout.encloseY(
                BorderLayout.center(
                        BoxLayout.encloseY(
                               id, address, tel, password, addPub
                        )
                )
        );
        pub.getStyle().setPadding(70, 70, 40, 40);

        add(pub);

        addPub.addActionListener(l -> {

            InfiniteProgress ip = new InfiniteProgress();
            final Dialog iDialog = ip.showInfiniteBlocking();
            userService.getInstance().editUseradmin(id.getText(), tel.getText(), address.getText(), password.getText());
            iDialog.dispose();
            new adminuserprofile(res).show();
            refreshTheme();

        }
        );
        setupSideMenu(res);
    }

    @Override

    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
