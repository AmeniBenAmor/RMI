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
public interface ProxyInterface extends Remote{
    public List<Facture> getFacturesParapharmaceutiques() throws RemoteException;
    public List<Facture> getVehiculeFacturesFromSIEntr2() throws RemoteException;
    public List<Facture> getVehiculeFacturesFromSIEntr3() throws RemoteException;
    public double sommeDesFactures() throws RemoteException;
    public double moyenneDesFactures() throws RemoteException;
    
}