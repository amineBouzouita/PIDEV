/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.userService;
import java.util.ArrayList;
import com.mycompany.myapp.entities.user ;


/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Shai Almog
 */
public class adminuserprofile extends SideMenuBaseForm {

    public adminuserprofile(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container remainingTasks = BoxLayout.encloseY(
                new Label("Email", "CenterTitle"),
                new Label(SessionManager.getEmail(), "CenterSubTitle")
        );

/*       Label role = new Label("role: " + SessionManager.getRoles());

        String roles = "";
        if (role.getText().contains("client")) {
            roles = "Client";
        }
        if (role.getText().contains("coach")) {
            roles = "coach";
        }
        if (role.getText().contains("ADMIN")) {
            roles = "Admin";
        }
*/
        String phoneNumber = SessionManager.getTel();
       
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("Phone number", "CenterTitle"),
                new Label(phoneNumber, "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                BoxLayout.encloseX(new Label(SessionManager.getEmail(), "Title"), new Label(SessionManager.getTel(), "Title"))
                              
                        )
                ),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );
         ArrayList<user> listUsers =userService.getInstance().affichageUsers();
        for (user us : listUsers){
            Container c = new Container();
            c.setLayout(BoxLayout.x());
            c.add(new Label(us.getEmail()+" "+us.getTel()));
            Container paddX = new Container();
            paddX.setPreferredSize(new Dimension(10,8));
           
            c.add(paddX);
          
            add(c);
            Container padd = new Container();
            padd.setPreferredSize(new Dimension(8,10));
            add(padd);
            c.setUIID("containerQuestion");
        }
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        Button edit = new Button("Edit user");
        edit.setUIID("LoginButton");
        edit.addActionListener((l) -> {
            new adminedit(res).show();
        });
        Button deleteuser = new Button("Delete user");
        edit.setUIID("LoginButton");
        deleteuser.addActionListener((l) -> {
            
            
                refreshTheme();
                new admindelete(res).show();
             
            
        });
        
            Button adduser = new Button("add user");
      
        adduser.addActionListener((l) -> {
            
            
                refreshTheme();
                new adminadd(res).show();
             
            
        });
        
          
          Button deco = new Button("deconnexion");
        deco.addActionListener((l) -> {
            new LoginForm(res).show();
        });

        Container buttons = BoxLayout.encloseX(GridLayout.encloseIn(4, edit, deleteuser,adduser,deco));
        add(buttons);

        setupSideMenu(res);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
