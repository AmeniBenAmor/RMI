/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author alro36
 */
public class UDPServer {

    /**
     * @param args the command line arguments
     */
    
    private static List<String> getFactures() {
        List<String> lines = new ArrayList<String>();
        try {
            String line;
            BufferedReader facturesFile = new BufferedReader(new FileReader("facturesParapharmaceutiques.txt"));
            line = facturesFile.readLine();
            while (line != null) {                
                lines.add(line);
                line = facturesFile.readLine();
            }
            facturesFile.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            
        }
        finally{
            return lines;
        }
        
    }
    
    public static void main(String[] args) throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(5500);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        List<String> factures = getFactures();
        while (true) {            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
         
            InetAddress IPClient= receivePacket.getAddress();
            int PortClient = receivePacket.getPort();
            System.out.println("RECEIVED: "+ sentence + "From "+IPClient+":"+PortClient);
            for (String facture : factures) {
                facture = facture;
                sendData = facture.getBytes();
               
                DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length, IPClient,PortClient);
                serverSocket.send(sendPacket);
            }
            String end="end of sending";
            sendData = end.getBytes();
               
                DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length, IPClient,PortClient);
                serverSocket.send(sendPacket);
            
        }
        
        
        
        
        
        
    }
    
    
}