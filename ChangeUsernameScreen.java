package Q1;

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
            } else if (newUsernameTF.getText().equals("")) {
                loginOutput.setText("New Username cannot be empty.");
            } else if (passwordPF.getPassword().length<4) {
                loginOutput.setText("Password length cannot be less than 4.");
            } else {
                int cu = changeUsername(usernameTF, passwordPF, newUsernameTF);
                if (cu==1)
                    loginOutput.setText("Username changed.");
                else if(cu==0)
                    loginOutput.setText("Same username cannot be applied.");
                else if(cu==2)
                    loginOutput.setText("Wrong credentials applied.");
                else
                    loginOutput.setText("Unknown error while changing username.");
            }
        } else {
            loginOutput.setText("Change username canceled.");
        }
    }

    public int changeUsername(JTextField username, JPasswordField password, JTextField newUsername) {
        
        if(!username.getText().equals(newUsername.getText())){
            Filing file = new Filing();
            file.load();

            Crypt vp = new Crypt();
            String encryptedPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe",
                    new String(password.getPassword()));

            UserInfo ui = new UserInfo(username.getText(), encryptedPassword);
            Boolean cu = file.changeUsername(ui, newUsername.getText());

            if (Boolean.FALSE.equals(cu))
                return 2;

            file.save();
            return 1;
    }
        return 0;
    }
}
