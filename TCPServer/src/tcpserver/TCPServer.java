/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alro36
 */


public class TCPServer {
    
    ArrayList<String> facturesList = new ArrayList<String>();
    
    /*public Connection getConnection()
    {   
        Connection con = null;
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = DriverManager.getConnection("jdbc:mysql://localhost/TPjava","root","root");
                
                return con;
            } 
            catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
    }
    public ArrayList<String> getFacturesList()
    {
        
        Connection con = getConnection();
        String query = "SELECT * FROM products";
        Statement st;
        ResultSet rs;
        try {
        st = con.createStatement();
        rs = st.executeQuery(query);
        String product;
        while(rs.next())
        {
        product = rs.getInt("id")+rs.getString("name")+rs.getString("price")+rs.getString("add_date");
        facturesList.add(product);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }
        return facturesList;
    }
    
    public void sendList(DataOutputStream outToClient){
        facturesList=getFacturesList();
        for (String string : facturesList) {
            try {
                outToClient.writeBytes(string);
            } catch (IOException ex) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String clientSentence;
        String CapitalizedSentence;
        Connect connection= new Connect();
        ArrayList<String> facturesList = connection.getFacturesList();
        try {    
            ServerSocket welcomeSocket = new ServerSocket(5400);
            
            while (true){
            Socket connectionSocket = welcomeSocket.accept();
            
            BufferedReader inFormClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            
            clientSentence=inFormClient.readLine();
            System.out.println("Received: "+ clientSentence);
            DataOutputStream outToClient= new DataOutputStream(connectionSocket.getOutputStream());
            CapitalizedSentence= clientSentence.toUpperCase() + '\n';
                for (String string : facturesList) {
                    outToClient.writeBytes(string+'\n');
                }
            outToClient.writeBytes("end of sending\n");
            
        }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
