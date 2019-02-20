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
public class SRMIFabTmpl extends UnicastRemoteObject implements SRMIFabInterface{
    public SRMIFabTmpl() throws RemoteException{
    super();
    };

    @Override
    public SRMIInterface newRMIServeur() throws RemoteException {
        return new ServeurRMIImp();
    }
    
    
    
    
}
