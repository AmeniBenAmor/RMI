/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alro36
 */
public class ProxyIMP extends UnicastRemoteObject implements ProxyInterface {

    public ProxyIMP() throws RemoteException{
        super();
    }
    
    public List<Facture> getFacturesParapharmaceutiques() throws RemoteException{
        List<Facture> result=new ArrayList<>();
        
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            String[] receivedSentences;
            int numFacture=1;
            String sentence = "hello";
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5500);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String receivedSentence = new String(receivePacket.getData());
            while(!receivedSentence.contains("end of sending")){
                /*receivedSentences = receivedSentence.split(";");
                try {
                    System.out.println("\nFacture "+numFacture+":\nCode: " +receivedSentences[0]+"\nNom: "+receivedSentences[1]+"\nPrice: "+receivedSentences[2]);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                numFacture++;*/
                String[] split = receivedSentence.split(";");
                result.add(new Facture(Integer.parseInt(split[0]), split[1], Float.parseFloat(split[2])));
                receiveData = new byte[1024];
                receivePacket=new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                receivedSentence = new String(receivePacket.getData());
                
                
            }
            System.out.println("\nterminé");
            clientSocket.close();
            return(result);
        } catch (Exception ex) {
            Logger.getLogger(ProxyIMP.class.getName()).log(Level.SEVERE, null, ex);
            return(result);
        }
        
    }

    @Override
    public List<Facture> getVehiculeFacturesFromSIEntr2() throws RemoteException {
        List<Facture> factures= new ArrayList<>();
        try {
            String sentence;
            String modifiedSentence;
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost",5400);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFormServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //sentence = inFromUser.readLine();
            outToServer.writeBytes( "hello" + '\n');
            sentence= inFormServer.readLine();
            while(!sentence.contains("end of sending")){
                String[] split = sentence.split(";");
                factures.add(new Facture(Integer.parseInt(split[0]), split[1], Float.parseFloat(split[2])));

                
                sentence= inFormServer.readLine();
            }
            
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProxyIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(factures);
        
    
    }

    @Override
    public List<Facture> getVehiculeFacturesFromSIEntr3() throws RemoteException {
         List<String> factures=new ArrayList<String>();
         List<Facture> result=new ArrayList<>();
        try {
            
            SRMIInterface stub= (SRMIInterface) Naming.lookup("rmi://localhost:5100/SR");
            factures=stub.getLesFacturesVehicule();
            for (String facture : factures) {
                String[] split = facture.split(";");
                result.add(new Facture(Integer.parseInt(split[0]), split[1], Float.parseFloat(split[2])));
        
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public double sommeDesFactures() throws RemoteException {
        List<Facture> factures= new ArrayList<>();
        double somme = 0;
        factures.addAll(this.getFacturesParapharmaceutiques());
        factures.addAll(this.getVehiculeFacturesFromSIEntr3());
        for (Facture facture : factures) {
            
            somme +=(double) facture.getMontant();
        
            
        }
        return somme;
    }

    @Override
    public double moyenneDesFactures() throws RemoteException {
    
       int nbrFacture=0;
       List<Facture> factures= new ArrayList<>();
        double somme = 0;
        factures.addAll(this.getFacturesParapharmaceutiques());
        factures.addAll(this.getVehiculeFacturesFromSIEntr3());
        for (Facture facture : factures) {
            nbrFacture++;
            somme +=(double) facture.getMontant();
        
            
        }
        return (double)(somme/(double)nbrFacture);
    }
}
    
