/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alro36
 */
public class ServeurRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(5100);
            SRMIFabTmpl fab= new SRMIFabTmpl();
            Naming.rebind("rmi://localhost:5100/SR", fab);
        } catch (Exception ex) {
            Logger.getLogger(ServeurRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
