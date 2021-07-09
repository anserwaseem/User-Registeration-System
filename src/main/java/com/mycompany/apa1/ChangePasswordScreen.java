package com.mycompany.apa1;

import java.awt.event.*;
import javax.swing.*;

public class ChangePasswordScreen extends ScreenFields implements ActionListener {
    JLabel newPasswordL;
    JPasswordField newPasswordPF;

    ChangePasswordScreen() {
        super.init1("Change");
        cpSInit();
        super.init2();
    }

    public void cpSInit() {
        super.fr.setTitle("Change Password");

        newPasswordL = new JLabel("New Password: ");
        newPasswordL.setHorizontalAlignment(SwingConstants.CENTER);
        newPasswordPF = new JPasswordField();
        newPasswordPF.setHorizontalAlignment(SwingConstants.CENTER);

        fieldLimit(newPasswordPF, 10);

        super.con.add(newPasswordL);
        super.con.add(newPasswordPF);

        cancelB.addActionListener(this);
        submitB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelB)
            super.fr.dispose();
        else if (e.getSource() == submitB) {
            if (usernameTF.getText().equals("") && passwordPF.getPassword().length == 0) {
                loginOutput.setText("Username, Password & New Password cannot be empty.");
            } else if (passwordPF.getPassword().length == 0) {
                loginOutput.setText("Password cannot be empty.");
            } else if (newPasswordPF.getPassword().length == 0) {
                loginOutput.setText("New Password cannot be empty.");
            } else if (usernameTF.getText().equals("")) {
                loginOutput.setText("Username cannot be empty.");
            } else if (passwordPF.getPassword().length<4) {
                loginOutput.setText("Password length cannot be less than 4.");
            } else if (newPasswordPF.getPassword().length<4) {
                loginOutput.setText("New Password length cannot be less than 4.");
            } else {
                Client cvr=new Client();
                int cp = cvr.changePassword(usernameTF.getText(), new String(passwordPF.getPassword()), new String(newPasswordPF.getPassword()));
                switch (cp) {
                    case 0:
                        loginOutput.setText("Same password cannot be applied.");
                        break;
                    case 1:
                        loginOutput.setText("Password changed.");
                        break;
                    case 2:
                        loginOutput.setText("Wrong credentials applied.");
                        break;
                    default:
                        loginOutput.setText("Unknown error while changing password.");
                        break;
                }
            }
        } else {
            loginOutput.setText("Change password canceled.");
        }
    }
}
