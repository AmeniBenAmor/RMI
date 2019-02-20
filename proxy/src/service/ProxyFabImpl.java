/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author alro36
 */
public class ProxyFabImpl extends UnicastRemoteObject  implements ProxyFabInterface{
    public ProxyFabImpl() throws RemoteException{
        super();
    }

    @Override
    public ProxyInterface newProxy() throws RemoteException {
        return new ProxyIMP();
    }
    
}
