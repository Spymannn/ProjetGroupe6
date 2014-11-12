package be.condorcet.projetgroupe.modele;

import java.sql.Connection;

public class TestSportDB {
	
	public static void main(String[] args){
		 DBConnection dbc =new DBConnection();
	        Connection con = dbc.getConnection(); 
	        if(con==null){
	            System.out.println("Connexion impossible");
	            System.exit(0);
	        }
	        else
	        	System.out.println("Connexion réussie");
	        
	}

}
