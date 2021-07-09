package com.mycompany.apa1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame implements ActionListener {
    JButton loginB, registerB, changePassB, changeUserB, exitB;

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
        exitB = new JButton("Exit");

        loginB.addActionListener(this);
        registerB.addActionListener(this);
        changePassB.addActionListener(this);
        changeUserB.addActionListener(this);
        exitB.addActionListener(this);

        con.add(chooseL);
        con.add(loginB);
        con.add(registerB);
        con.add(changePassB);
        con.add(changeUserB);
        con.add(exitB);

        setSize(800, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
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
        else if(e.getSource()==exitB){
            Client ce=new Client();
            Boolean ve = ce.sendExitRequest("exit");
            
            if(Boolean.TRUE.equals(ve))
                System.out.println("Server closed successfully.");
            else
                System.out.println("Server not closed.");
        }
    }
}
