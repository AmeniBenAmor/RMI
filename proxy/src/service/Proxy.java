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
public class Proxy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            ProxyIMP ob= new ProxyIMP();
            Naming.bind("proxy", ob);
        } catch (Exception ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}