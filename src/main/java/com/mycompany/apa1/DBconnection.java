/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apa1;

import java.sql.*;

/**
* <h1>Database Connection</h1>
* This program connects the server with database
* <p>
*
* @author  Anser Waseem
* @version 1.0
* @since   2021-05-21
*/
public class DBconnection {
    
    String user = "root";
    String pass = "183341128";
    String url = "jdbc:mysql://localhost:3306/aphw3";// ?user=root&password=183341128
    Connection con;
    
    DBconnection() {
        try {
            this.con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Check if the given username exists in the database or not
     * @param username  username to be checked
     * @return          either user's username exists or not
     */
    public synchronized Boolean checkUser(String username) {
        Boolean ret=false;
        int i=0;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(url);
            rs = stmt.executeQuery("select username from UserInfo");
            
            while(rs.next()){
                if(rs.getString(1).equals(username)){
                    ret=true; 
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        return ret;
    }
    
    /**
     * It closes different open connections.
     * @param rs opened ResultSet object
     * @param stmt opened PreparedStatement object
     * @param con opened Connection object
     * @throws SQLException 
     */
    private void closeRSC(ResultSet rs, PreparedStatement stmt, Connection con) throws SQLException{
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
    }
    // get user's encrypted password from the database
    public String getEncPassword(String username) {
        String retPassword=null;
        int i=0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement("select pass from UserInfo where username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                retPassword=rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                this.closeRSC(rs, stmt, con);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return retPassword;
    }
    
    //register new user and save the information in database
    public synchronized int registerUser(UserInfo ui) {
        int ret=1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement("insert into UserInfo values(?,?)");
            
            stmt.setString(1, ui.getUsername()); //non-encrypted username
            stmt.setString(2, ui.getPassword()); //encrypted password
            
            int i=stmt.executeUpdate();  
            System.out.println(i+" records inserted"); 
        } catch (SQLException ex) {
            ret=0;
            System.out.println(ex);
        } finally {
            try {
                this.closeRSC(rs, stmt, con);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return ret;
    }
    
    //change old username to new one in the database
    public synchronized Boolean changeUsername(UserInfo oui, String username) {
        Boolean ret=false;
        PreparedStatement stmt = null;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement("update UserInfo set username=? where username=? and pass=?");
            stmt.setString(1, username);
            stmt.setString(2, oui.getUsername());
            stmt.setString(3, oui.getPassword());

            int i=stmt.executeUpdate();  
            if(i>0)
                ret=true;
            System.out.println(i+" username updated");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                this.closeRSC(null, stmt, con);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return ret;
    }
    
    //change old password to new one in the database
    public synchronized Boolean changePassword(UserInfo oui, String password) {
        Boolean ret=false;
        PreparedStatement stmt = null;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement("update UserInfo set pass=? where username=? and pass=?");
            stmt.setString(1, password);
            stmt.setString(2, oui.getUsername());
            stmt.setString(3, oui.getPassword());

            int i=stmt.executeUpdate();  
            if(i>0)
                ret=true;
            System.out.println(i+" password updated");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                this.closeRSC(null, stmt, con);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return ret;
    }
}