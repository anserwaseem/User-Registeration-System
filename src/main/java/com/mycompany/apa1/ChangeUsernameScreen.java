package com.mycompany.apa1;

import java.awt.event.*;
import javax.swing.*;

public class ChangeUsernameScreen extends ScreenFields implements ActionListener {
    JLabel newUsernameL;
    JTextField newUsernameTF;

    ChangeUsernameScreen() {
        super.init1("Change");
        cuSInit();
        super.init2();
    }

    public void cuSInit() {
        super.fr.setTitle("Change Username");

        newUsernameL = new JLabel("New Username: ");
        newUsernameL.setHorizontalAlignment(SwingConstants.CENTER);
        newUsernameTF = new JTextField();
        newUsernameTF.setHorizontalAlignment(SwingConstants.CENTER);
        
        fieldLimit(newUsernameTF, 20);

        super.con.add(newUsernameL);
        super.con.add(newUsernameTF);

        cancelB.addActionListener(this);
        submitB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelB)
            super.fr.dispose();
        else if (e.getSource() == submitB) {
            if (usernameTF.getText().equals("") && passwordPF.getPassword().length == 0) {
                loginOutput.setText("Username & Password cannot be empty.");
            } else if (passwordPF.getPassword().length == 0) {
                loginOutput.setText("Password cannot be empty.");
            } else if (usernameTF.getText().equals("")) {
                loginOutput.setText("Username cannot be empty.");
            } else if (newUsernameTF.getText().equals("")) {
                loginOutput.setText("New Username cannot be empty.");
            } else if (passwordPF.getPassword().length<4) {
                loginOutput.setText("Password length cannot be less than 4.");
            } else {
                Client cvr=new Client();
                int cu = cvr.changeUsername(usernameTF.getText(), new String(passwordPF.getPassword()), newUsernameTF.getText());
                switch (cu) {
                    case 0:
                        loginOutput.setText("Same username cannot be applied.");
                        break;
                    case 1:
                        loginOutput.setText("Username changed.");
                        break;
                    case 2:
                        loginOutput.setText("Wrong credentials applied.");
                        break;
                    default:
                        loginOutput.setText("Unknown error while changing username.");
                        break;
                }
            }
        } else {
            loginOutput.setText("Change username canceled.");
        }
    }
}
