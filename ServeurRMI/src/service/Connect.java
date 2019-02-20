/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alro36
 */
public class Connect {
    ArrayList<String> facturesList;

    public Connect() {
        facturesList=new ArrayList<String>();
    }
    
    
    public Connection getConnection()
    {   
        Connection con = null;
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = DriverManager.getConnection("jdbc:mysql://localhost/SAR","root","root");
                
                return con;
            } 
            catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
    }
    public ArrayList<String> getFacturesList()
    {
        
        Connection con = getConnection();
        String query = "SELECT * FROM vehiculefactures";
        Statement st;
        ResultSet rs;
        try {
        st = con.createStatement();
        rs = st.executeQuery(query);
        String product;
        while(rs.next())
        {
        product = rs.getInt("code")+";"+rs.getString("nom")+";"+rs.getFloat("montant");
        facturesList.add(product);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }
        return facturesList;
    }
    public void afficher(){
        facturesList=getFacturesList();
        for (String string : facturesList) {
            
                System.out.println(string);
           
                }
    }
    
    
}
