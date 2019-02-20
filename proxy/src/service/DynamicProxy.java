/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;

/**
 *
 * @author alro36
 */
public class DynamicProxy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(System.getSecurityManager() == null) System.setSecurityManager
        (new RMISecurityManager());
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            //ProxyFabImpl ob= new ProxyFabImpl();
            Properties p= System.getProperties();
            String url=p.getProperty("java.rmi.server.codebase");
            Class ClasseServeur = RMIClassLoader.loadClass(url,
            "service.ProxyFabImpl");
            registry.rebind("proxy",(Remote)ClasseServeur.newInstance());
        } catch (Exception ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


