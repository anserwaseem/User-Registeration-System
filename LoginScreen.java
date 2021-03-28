import java.util.*;
import java.io.*;
import javax.swing.*;

public class LoginScreen {
    LoginScreen(){}
    public void init(){
        // Scanner sc = new Scanner(System.in);

        // UserInfo ui=new UserInfo();

        // System.out.println("Username:");
        // ui.setUsername(sc.nextLine());

        // System.out.println("Username:");
        // ui.setPassword(sc.nextLine());

        // System.out.println("Press enter to submit");

        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        
        Object[] message = {
            "Username:", username,
            "Password:", password
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (username.getText().equals("") && password.getText().equals("")) {
                System.out.println("Username & Password cannot be empty.");
            } else if(password.getText().equals("")){
                System.out.println("Password cannot be empty.");
            } else if(username.getText().equals("")){
                System.out.println("Username cannot be empty.");
            } else{
                System.out.println("Login successful.");
            }
        } else {
            System.out.println("Login canceled.");
        }
    }
}
