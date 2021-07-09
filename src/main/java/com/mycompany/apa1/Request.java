/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apa1;

import java.io.Serializable;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author AnserWaseem
 */
public class Request implements Serializable{
    private String usernameTF;
    private String passwordPF;
    private String requestMethod;
    private String newUsernameTF;
    private String newPasswordPF;
    
    Request(){}
    Request(String u, String p, String rm, String nu, String np){
        usernameTF=u;
        passwordPF=p;
        requestMethod=rm;
        newUsernameTF=nu;
        newPasswordPF=np;
    }
    
    public void setUsernameTF(String u){
        this.usernameTF=u;
    }
    public void setPasswordPF(String p){
        this.passwordPF=p;
    }
    public void setRequestMethod(String rm){
        this.requestMethod=rm;
    }
    public void setNewUsernameTF(String nu){
        this.newUsernameTF=nu;
    }
    public void setNewPasswordPF(String np){
        this.newPasswordPF=np;
    }
    
    public String getUsernameTF(){
        return this.usernameTF;
    }
    public String getPasswordPF(){
        return this.passwordPF;
    }
    public String getRequestMethod(){
        return this.requestMethod;
    }
    public String getNewUsernameTF(){
        return this.newUsernameTF;
    }
    public String getNewPasswordPF(){
        return this.newPasswordPF;
    }
    
    @Override
    public String toString(){
        return "username: " + usernameTF + " password: " + passwordPF + " Request Method: " + requestMethod+ "\nNew Username: " + newUsernameTF + " New Password: " + newPasswordPF;
    }
    
    public void print() {
        StringBuffer sb=new StringBuffer();
        sb.append("Client request received...\nRequested Method: ").append(requestMethod);
        
        switch (requestMethod) {
            case "register":
            case "login":
                sb.append("\nUsername: ").append(usernameTF);
                sb.append("\nPassword: ").append(passwordPF);
                break;
                
            case "new username":
            case "new password":
                sb.append("\nOld Username: ").append(usernameTF);
                sb.append("\nOld Password: ").append(passwordPF);
                if(requestMethod.equals("change username"))
                    sb.append("\nNew Username: ").append(newUsernameTF);
                else
                    sb.append("\nNew Password: ").append(newPasswordPF);
                break;
                
            case "exit":
                sb.append("\nNo more client requests will be entertained.").append("\nCLOSING the server now...");
                break;
                
            default:
                break;
        }
        JOptionPane.showMessageDialog(null, sb, "S E R V E R", INFORMATION_MESSAGE);
    }
}
