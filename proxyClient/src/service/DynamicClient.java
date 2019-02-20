/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.lang.reflect.Constructor;
import java.rmi.RMISecurityManager;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;

/**
 *
 * @author alro36
 */
public class DynamicClient {
    public DynamicClient () throws Exception {
    Properties p = System.getProperties();
    String url = p.getProperty("java.rmi.server.codebase");
    Class ClasseClient = RMIClassLoader.loadClass(url,
    "service.ProxyClient");
    // lancer le client
        Constructor [] C = ClasseClient.getConstructors();
        System.out.println(C[0]);
        C[0].newInstance(new Object[]{});
        System.err.println("ha3");
    
    }
    public static void main (String [] args) {
    System.setSecurityManager(new RMISecurityManager());
    try{
        System.out.println("heh");
        DynamicClient cli = new DynamicClient();
    }
    catch (Exception e) {
        System.out.println (e.toString());
    }
    }
}

