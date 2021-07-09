package com.mycompany.apa1;

import java.awt.event.*;

public class RegisterScreen extends ScreenFields implements ActionListener {
    RegisterScreen() {
        super.init1("Register");
        super.init2();
        rSInit();
    }

    public void rSInit() {
        super.fr.setTitle(" R E G I S T E R ");
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
            } else if (passwordPF.getPassword().length<4) {
                loginOutput.setText("Password length cannot be less than 4.");
            } else {
                Client cvr=new Client();
                Boolean vr = cvr.verifyRegisteration(usernameTF.getText(), new String(passwordPF.getPassword()));
                if (Boolean.TRUE.equals(vr))
                    loginOutput.setText("Registeration successful.");
                else
                    loginOutput.setText("This user is already registered.");
            }
        } else {
            loginOutput.setText("Registeration canceled.");
        }
    }
}
