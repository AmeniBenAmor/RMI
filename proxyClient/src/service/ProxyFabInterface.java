/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alro36
 */
public interface ProxyFabInterface extends Remote {
    public ProxyInterface newProxy() throws RemoteException;
    
}
