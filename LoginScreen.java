package Q1;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelB) {
            fr.setVisible(false);
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
                Boolean vl = verifyLogin(usernameTF, passwordPF);
                if (Boolean.TRUE.equals(vl))
                    loginOutput.setText("Login successful.");
                else
                    loginOutput.setText("Wrong Credentials.");
            }
        } else {
            loginOutput.setText("Login canceled.");
        }

    }

    private Boolean verifyLogin(JTextField username, JPasswordField password) {
        Filing file = new Filing();
        file.load();

        Crypt vp = new Crypt();
        String encryptGivenPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe",
                new String(password.getPassword()));
        return (file.getEncPassword(username.getText()).equals(encryptGivenPassword)
                && Boolean.TRUE.equals(file.checkUser(username.getText())));
    }
}