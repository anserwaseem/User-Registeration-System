package com.mycompany.apa1;

import java.awt.event.*;
import javax.swing.*;

public class LoginScreen extends ScreenFields implements ActionListener {

    LoginScreen() {
        super.init1("Login");
        super.init2();
        lSInit();
    }

    public void lSInit() {
        super.fr.setTitle(" L O G I N ");

        cancelB.addActionListener(this);
        submitB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelB) {
            super.fr.dispose();
        } else if (e.getSource() == submitB) {
            if (usernameTF.getText().equals("") && passwordPF.getPassword().length == 0) {
                loginOutput.setText("Username & Password cannot be empty.");
            } else if (passwordPF.getPassword().length == 0) {
                loginOutput.setText("Password cannot be empty.");
            } else if (usernameTF.getText().equals("")) {
                loginOutput.setText("Username cannot be empty.");
            } else if (passwordPF.getPassword().length<4) {
                loginOutput.setText("Password length cannot be less than 4.");
            } else {
                Client cvr=new Client();
                Boolean vl = cvr.verifyLogin(usernameTF.getText(), new String(passwordPF.getPassword()));
                
                if (Boolean.TRUE.equals(vl))
                    loginOutput.setText("Login successful.");
                else
                    loginOutput.setText("Wrong Credentials.");
            }
        } else {
            loginOutput.setText("Login canceled.");
        }
    
}