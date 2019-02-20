/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;

/**
 *
 * @author alro36
 */
public class Facture implements Serializable{
    private int code;
    private String nom;
    private float montant;
    
    public Facture(int code,String nom,float montant){
        this.code=code;
        this.nom=nom;
        this.montant=montant;
    } 
    
    public int getCode(){
        return code;
    }
    public String getNom(){
        return nom;
    }
    public float getMontant(){
        return montant;
    }
    public String toString(){
        return "Debut Facture:\nCode: "+code+"\nNom: "+nom+"\nMontant: "+montant+"\nfin facture\n";
    }
    
}
