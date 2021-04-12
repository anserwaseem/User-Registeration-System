package Q1;

import java.awt.event.*;
import java.io.Serializable;

public class UserInfo implements Serializable{
    private String username;
    private String password;

    UserInfo() {}
    UserInfo(String u, String p){
        this.username=u;
        this.password=p;
    }
    public void setUsername(String u){
        this.username=u;
    }
    public void setPassword(String p){
        this.password=p;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    @Override
    public String toString(){
        return "Username: "+username+" Password: "+password+"\n";
    }
    // public void storeDetails(String u, String p){
        
    // }

    // public void actionPerformed(ActionEvent e){

    //     if(e.getSource()==buttonName){
    //     }
    // }
}
