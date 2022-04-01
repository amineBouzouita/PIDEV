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
public class resetpwd extends SideMenuBaseForm {

    public resetpwd(Resources res) {
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


        TextField email = new TextField(SessionManager.getEmail(), "mail...");
        email.getStyle().setBgColor(0x000000);
        email.getStyle().setFgColor(0x000000);
        email.getStyle().setBorder(Border.createRoundBorder(50, 50));
        email.getStyle().setElevation(1);
        email.getStyle().setPadding(3, 3, 0, 0);
        email.getStyle().setUnderline(false);

     
       

       
        Button addPub = new Button("send mail");
        addPub.getStyle().setBgColor(0xffffff);
        addPub.getStyle().setFgColor(0x0583D2);
        addPub.getStyle().setBgTransparency(255);
        addPub.getStyle().setBorder(Border.createRoundBorder(30, 30));
        addPub.getStyle().setMargin(13, 13, 80, 80);
        addPub.getStyle().setPadding(3, 3, 0, 0);

        Container pub = BoxLayout.encloseY(
                BorderLayout.center(
                        BoxLayout.encloseY(
                               email, addPub
                        )
                )
        );
        pub.getStyle().setPadding(70, 70, 40, 40);

        add(pub);

        addPub.addActionListener(l -> {

            InfiniteProgress ip = new InfiniteProgress();
            final Dialog iDialog = ip.showInfiniteBlocking();
            userService.getInstance().resetpwd(email.getText());
            iDialog.dispose();
            new ProfileForm(res).show();
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
