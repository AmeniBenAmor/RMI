/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alro36
 */
public class ServeurRMIImp extends UnicastRemoteObject implements SRMIInterface{

    protected ServeurRMIImp() throws RemoteException{
        super();
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public List<String> getLesFacturesVehicule() throws RemoteException {
        Connect connection=new Connect();
        return((List<String>)connection.getFacturesList());
        
    }
    
}
