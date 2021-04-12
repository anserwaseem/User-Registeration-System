package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame implements ActionListener {
    JButton loginB, registerB, changePassB, changeUserB;

    MainScreen() {
        init();
    }

    public void init() {
        Container con = getContentPane();
        con.setLayout(new GridBagLayout());

        JLabel chooseL = new JLabel("Choose any option:");
        loginB = new JButton("Login");
        registerB = new JButton("Register");
        changePassB = new JButton("Change Password");
        changeUserB = new JButton("Change Username");

        loginB.addActionListener(this);
        registerB.addActionListener(this);
        changePassB.addActionListener(this);
        changeUserB.addActionListener(this);

        con.add(chooseL);
        con.add(loginB);
        con.add(registerB);
        con.add(changePassB);
        con.add(changeUserB);

        setSize(800, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == loginB){
            LoginScreen ls =new LoginScreen();
        }
        else if(e.getSource()==registerB){
            RegisterScreen rs=new RegisterScreen();
        }
        else if(e.getSource()==changePassB){
            ChangePasswordScreen cps=new ChangePasswordScreen();
        }
        else if(e.getSource()==changeUserB){
            ChangeUsernameScreen cus=new ChangeUsernameScreen();
        }
    }
}
