/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alro36
 */
public class ProxyClient {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Facture> factures=new ArrayList<>();
        try {
            
            Registry reg= LocateRegistry.getRegistry("localhost",1099);
            ProxyInterface proxy= (ProxyInterface) reg.lookup("proxy");
            Scanner reader = new Scanner(System.in);  
            System.out.println("\nLa somme: "+proxy.sommeDesFactures());
            System.out.println("\nLa moyenne: "+proxy.moyenneDesFactures());
            System.out.println("\nEnter 1 to show Factures from SI Entr1 \nEnter 2 to show Factures from SI "
                    + "Entr2 \nEnter 3 to show Factures from SI Entr3 \nEnter 4 to show the sum of all Factures "
                    + "\nEnter 5 to show moy of Factures ");
            int n = reader.nextInt();
            while(n!=-1){
                switch(n){
                    case 1: 
                        for (Facture facture : proxy.getFacturesParapharmaceutiques()) {
                        System.out.println(facture);
                        }
                        break;
                    case 2:
                        for (Facture facture : proxy.getVehiculeFacturesFromSIEntr2()) {
                        System.out.println(facture);
                        }
                        break;
                    case 3:
                        for (Facture facture : proxy.getVehiculeFacturesFromSIEntr3()) {
                        System.out.println(facture);
                        }
                        break;
                    case 4:
                        System.out.println("\nLa somme: "+proxy.sommeDesFactures());
                        break;
                    case 5:
                        System.out.println("\nLa moyenne: "+proxy.moyenneDesFactures());
                        break;
                }
                System.out.println("Enter 1 to show Factures from SI Entr1 \nEnter 2 to show Factures from SI "
                    + "Entr2 \nEnter 3 to show Factures from SI Entr3 \nEnter 4 to show the sum of all Factures "
                    + "\nEnter 5 to show moy of Factures ");
                n = reader.nextInt();
            }
        } catch (Exception ex) {
            Logger.getLogger(ProxyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}