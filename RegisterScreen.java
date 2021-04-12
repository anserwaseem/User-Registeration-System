package Q1;

import java.awt.event.*;
import javax.swing.*;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelB)
            fr.setVisible(false);
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
                Boolean vl = verifyRegisteration(usernameTF, passwordPF);
                if (Boolean.TRUE.equals(vl))
                    loginOutput.setText("Registeration successful.");
                else
                    loginOutput.setText("This user is already registered.");
            }
        } else {
            loginOutput.setText("Registeration canceled.");
        }
    }

    public Boolean verifyRegisteration(JTextField username, JPasswordField password) {
        Filing file = new Filing();
        file.load();
        Boolean cu = file.checkUser(username.getText());
        // if user exists
        if (Boolean.FALSE.equals(cu)) {
            Crypt vp = new Crypt();
            String encryptedPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe",
                    new String(password.getPassword()));

            UserInfo ui = new UserInfo(username.getText(), encryptedPassword);
            file.addUser(ui);
            file.save();
            return true;
        }
        return false;
    }
}
