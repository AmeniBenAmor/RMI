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
