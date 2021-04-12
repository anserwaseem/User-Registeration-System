package Q1;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelB)
            fr.setVisible(false);
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
                int cp = changePassword(usernameTF, passwordPF, newPasswordPF);
                if (cp == 1)
                    loginOutput.setText("Password changed.");
                else if (cp == 0)
                    loginOutput.setText("Same password cannot be applied.");
                else if (cp == 2)
                    loginOutput.setText("Wrong credentials applied.");
                else
                    loginOutput.setText("Unknown error while changing password.");
            }
        } else {
            loginOutput.setText("Change password canceled.");
        }
    }

    public int changePassword(JTextField username, JPasswordField password, JPasswordField newPassword) {

        Filing file = new Filing();
        file.load();

        Crypt vp = new Crypt();
        String encryptedPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe",
                new String(password.getPassword()));
        String encryptedNewPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe",
                new String(newPassword.getPassword()));
                
        if (!encryptedPassword.equals(encryptedNewPassword)) {
            UserInfo ui = new UserInfo(username.getText(), encryptedPassword);
            Boolean cp = file.changePassword(ui, encryptedNewPassword);

            if (Boolean.FALSE.equals(cp))
                return 2;

            file.save();
            return 1;
        }
        return 0;
    }
}
