/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apa1;

import java.net.*;
import java.io.*;
import java.util.Arrays;

/**
* <h1>Client</h1>
* This program sends request to the server to fulfill user's demand.
* <p>
* <b>Note:</b> It runs separately from the Server program.
*
* @author  Anser Waseem
* @version 1.0
* @since   2021-05-21
*/
public class Client {
    
    Client(){
    }
    
    /**
     * Verifies registration of the user by sending a request to server
     * @param username  the username given by the user
     * @param password  the password given by the user
     * @return          either user is registered or not
     */
    public Boolean verifyRegisteration(String username, String password){
        return sendRequest("register", username, password);
    }
    
    /**
     * Verifies login of the user by sending a request to server
     * @param username  the username given by the user
     * @param password  the password given by the user
     * @return          either user is logged in or not
     */
    public Boolean verifyLogin(String username, String password) {
        return sendRequest("login", username, password);
    }
    
    /**
     * Changes user's name by sending request to server
     * @param username      old username given by the user
     * @param password      old password given by the user
     * @param newUsername   new username given by the user 
     * @return              either user's name is changed or not
     */
    public int changeUsername(String username, String password, String newUsername) {
        return sendRequest("new username", username, password, newUsername);
    }
    
    /**
     * Changes user's password by sending request to server
     * @param username      old username given by the user
     * @param password      old password given by the user
     * @param newPassword   new password given by the user 
     * @return              either user's password is changed or not
     */
    public int changePassword(String username, String password, String newPassword) {
        return sendRequest("new password", username, password, newPassword);
    }
    
    public Boolean sendExitRequest(String reqExitMethod){
        Boolean ret=false;
        
        try (Socket s = new Socket("localhost", 2227)){
            Request req=new Request("", "", reqExitMethod, "", "");
            
            //Sending data to Server
            try (OutputStream out = s.getOutputStream()) {
                try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                    oos.writeObject(req);
                    oos.flush();
            
                    //receiveing data from Server
                    try (InputStream in = s.getInputStream()) {
                        System.out.println("inC: "+in.available());
                        try (ObjectInputStream input = new ObjectInputStream(in)) {
                            System.out.println("inputC: "+input.available());
                            try {
                                ret=input.readBoolean();
                                System.out.println("input.readBoolean(): "+ret);
                                return ret;
                            } catch (EOFException ef) {
                                System.out.println("- - - -EOF- - - -\n"+ef);
                                System.out.println(Arrays.toString(ef.getStackTrace()));
                            }
                        }            
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return ret;
    }
    
    /**
     * Connects to the server, writes Request object to it, and reads either the requested method is fulfilled or not 
     * (For Register and Login methods only)
     * @param reqMethod     the method being requested by the client
     * @param username      the username given by the user
     * @param password      the password given by the user
     * @return              either reqMethod is fulfilled or not
     * @see                 com.mycompany.apa1.Request
     */
    private Boolean sendRequest(String reqMethod, String username, String password){
        Boolean ret=false;
        
        try (Socket s = new Socket("localhost", 2227)){
            System.out.println(1);
            Request req=new Request(username, password, reqMethod, "", "");
            System.out.println(2);
            
            /**
             * Sending data to Server
             */
            try (OutputStream out = s.getOutputStream()) {
                System.out.println(3);
                try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                    System.out.println(4);
                    oos.writeObject(req);
                    System.out.println(5);
                    oos.flush();
                    System.out.println(6); 
            
                    //receiveing data from Server
                    try (InputStream in = s.getInputStream()) {
                        System.out.println("inC: "+in.available());
                        System.out.println(7);
                        try (ObjectInputStream input = new ObjectInputStream(in)) {
                            System.out.println("inputC: "+input.available());
                            System.out.println(8);
                            try {
                                ret=input.readBoolean();
                                System.out.println("input.readBoolean(): "+ret);
                                return ret;
                            } catch (EOFException ef) {
                                System.out.println("- - - -EOF- - - -\n"+ef);
                                System.out.println(Arrays.toString(ef.getStackTrace()));
                            }
                        }            
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(9);
            System.out.println(e);
            System.out.println(10);
        }
        System.out.println(11);
        return ret;
    }
    
    /**
     * Connects to the server, writes Request object to it, and reads either the requested method is fulfilled or not 
     * (For ChangeUsername and ChangePassword methods only)
     * @param reqMethod     the method being requested by the client
     * @param username      the username given by the user
     * @param password      the password given by the user
     * @param extraField    either new username or new password
     * @return              either reqMethod is fulfilled or not
     * @see                 com.mycompany.apa1.Request
     */
    private int sendRequest(String reqMethod, String username, String password, String extraField){
        int ret=-2;
        
        try (Socket s = new Socket("localhost", 2227)){
            System.out.println(1);
            Request req=new Request(username, password, "new password",
                    reqMethod.equals("new username") ? extraField : "",
                    reqMethod.equals("new username") ? extraField : "");
            System.out.println(2);
            
            try (OutputStream out = s.getOutputStream()) {
                System.out.println(3);
                try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                    System.out.println(4);
                    oos.writeObject(req);
                    System.out.println(5);
                    oos.flush();
                    System.out.println(6);
                    try (InputStream in = s.getInputStream()) {
                        System.out.println("inC: " + in.available());
                        System.out.println(7);
                        try (ObjectInputStream input = new ObjectInputStream(in)) {
                            System.out.println("inputC: " + input.available());
                            System.out.println(8);
                            try {
                                ret = input.readInt();
                                System.out.println("input.readInt(): " + ret);
                                return ret;
                            } catch (EOFException ef) {
                                System.out.println("- - - -EOF- - - -\n" + ef);
                                System.out.print(Arrays.toString(ef.getStackTrace()));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(9);
            System.out.println(e);
            System.out.println(10);
        }
        System.out.println(11);
        return ret;
    }
}
