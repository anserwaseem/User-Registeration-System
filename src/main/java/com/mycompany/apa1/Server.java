/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apa1;

import java.net.*;
import java.io.*;

/**
 * <h1>Server</h1>
 * This program starts a server, entertains client's requests by verifying via
 * connecting to database.
 * <p>
 * <b>Note:</b> It runs separately from the Client program.
 *
 * @author Anser Waseem
 * @version 1.0
 * @since 2021-05-21
 */
public class Server {

    /**
     * This is the main method which makes use of multiple methods to start a
     * server
     *
     * @param args arguments
     * @see #verifyRegisteration(java.lang.String, java.lang.String)
     * @see #verifyLogin(java.lang.String, java.lang.String)
     * @see #changeUsername(java.lang.String, java.lang.String,
     * java.lang.String)
     * @see #changePassword(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(2227)) {
            ss.setReuseAddress(true);
            System.out.println("Server started...");
            int clientNumber=1;

            while (true) {
                try {
                    Socket s = ss.accept(); //this socket will be closed inside run method
                    System.out.printf("%nClient %d request received...%n", clientNumber++);

                    ClientHandler clientSock = new ClientHandler(s);
                    new Thread(clientSock).start();

                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (InputStream in = clientSocket.getInputStream()) {
                try (ObjectInputStream input = new ObjectInputStream(in)) {
//                    System.out.println("inputS: "+input.available());
                    Request req = (Request) input.readObject();
                    
                    switch (req.getRequestMethod()) {
                        case "register": {
                            try (OutputStream out = clientSocket.getOutputStream()) {
                                try (ObjectOutputStream output = new ObjectOutputStream(out)) {
                                    Boolean vr = verifyRegisteration(req.getUsernameTF(), req.getPasswordPF());
                                    output.writeBoolean(vr);
                                    output.flush();
                                }
                            }
                            break;
                        }
                        case "login": {
                            try (OutputStream out = clientSocket.getOutputStream()) {
                                try (ObjectOutputStream output = new ObjectOutputStream(out)) {
                                    Boolean vl = verifyLogin(req.getUsernameTF(), req.getPasswordPF());
                                    output.writeBoolean(vl);
                                    output.flush();
                                }
                            }
                            break;
                        }
                        case "new username": {
                            try (OutputStream out = clientSocket.getOutputStream()) {
                                try (ObjectOutputStream output = new ObjectOutputStream(out)) {
                                    int cu = changeUsername(req.getUsernameTF(), req.getPasswordPF(), req.getNewUsernameTF());
                                    output.writeInt(cu);
                                    output.flush();
                                }
                            }
                            break;
                        }
                        case "new password": {
                            try (OutputStream out = clientSocket.getOutputStream()) {
                                try (ObjectOutputStream output = new ObjectOutputStream(out)) {
                                    int cp = changePassword(req.getUsernameTF(), req.getPasswordPF(), req.getNewPasswordPF());
                                    output.writeInt(cp);
                                    output.flush();
                                }
                            }
                            break;
                        }
                        default:
                            break;
                    }
                    req.print();
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            } finally {
                try {
                    this.clientSocket.close();
                    System.out.println("closed clientSocket");
                } catch (IOException e) {
                    System.out.println("clientSocket server run" + e);
                }
            }
        }
    }

    /**
     * Verifies registration of the user by connecting to database
     *
     * @param username the username given by the user
     * @param password the password given by the user
     * @return either user is registered or not
     * @see com.mycompany.apa1.DBconnection
     * @see com.mycompany.apa1.Crypt
     * @see com.mycompany.apa1.UserInfo
     */
    public static Boolean verifyRegisteration(String username, String password) {
        DBconnection dbc = new DBconnection();

        Boolean cu = dbc.checkUser(username);
        // if user does not exist
        if (Boolean.FALSE.equals(cu)) {
            Crypt vp = new Crypt();
            String encryptedPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe", password);

            UserInfo ui = new UserInfo(username, encryptedPassword);
            dbc.registerUser(ui);
            return true;
        }
        return false;
    }

    /**
     * Verifies login of the user by connecting to database
     *
     * @param username the username given by the user
     * @param password the password given by the user
     * @return either user is logged in or not
     * @see com.mycompany.apa1.DBconnection
     * @see com.mycompany.apa1.Crypt
     * @see com.mycompany.apa1.UserInfo
     */
    public static Boolean verifyLogin(String username, String password) {
        DBconnection dbc = new DBconnection();

        Crypt vp = new Crypt();
        String encryptGivenPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe", password);

        if (Boolean.TRUE.equals(dbc.checkUser(username))) {
            return (dbc.getEncPassword(username).equals(encryptGivenPassword));
        }
        return false;
    }

    /**
     * Changes user's name by connecting to database
     *
     * @param username old username given by the user
     * @param password old password given by the user
     * @param newUsername new username given by the user
     * @return either user's name is changed or not
     * @see com.mycompany.apa1.DBconnection
     * @see com.mycompany.apa1.Crypt
     * @see com.mycompany.apa1.UserInfo
     */
    public static int changeUsername(String username, String password, String newUsername) {

        if (!username.equals(newUsername)) {
            DBconnection dbc = new DBconnection();

            Crypt vp = new Crypt();
            String encryptedPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe", password);

            UserInfo ui = new UserInfo(username, encryptedPassword);
            Boolean cu = dbc.changeUsername(ui, newUsername);

            if (Boolean.FALSE.equals(cu)) {
                return 2;
            }

            return 1;
        }
        return 0;
    }

    /**
     * Changes user's password by connecting to database
     *
     * @param username old username given by the user
     * @param password old password given by the user
     * @param newPassword new password given by the user
     * @return either user's password is changed or not
     * @see com.mycompany.apa1.DBconnection
     * @see com.mycompany.apa1.Crypt
     * @see com.mycompany.apa1.UserInfo
     */
    public static int changePassword(String username, String password, String newPassword) {

        DBconnection dbc = new DBconnection();

        Crypt vp = new Crypt();
        String encryptedPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe", password);
        String encryptedNewPassword = vp.encrypt("AnserWaseemMalik", "InitializationVe", newPassword);

        if (!encryptedPassword.equals(encryptedNewPassword)) {
            UserInfo ui = new UserInfo(username, encryptedPassword);
            Boolean cp = dbc.changePassword(ui, encryptedNewPassword);

            if (Boolean.FALSE.equals(cp)) {
                return 2;
            }

            return 1;
        }
        return 0;
    }
}
