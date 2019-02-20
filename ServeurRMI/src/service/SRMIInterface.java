/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author alro36
 */
public interface SRMIInterface extends Remote{
    public List<String> getLesFacturesVehicule() throws RemoteException;
    
}
