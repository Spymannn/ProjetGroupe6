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
	        SportDB.setConnection(con);
	        SportDB sp1;
	        
	        /*try{
	            System.out.println("\nTest d'ajout d'un sport qui fonctionne");
	            System.out.println("=========================================");
	            sp1 = new SportDB("kayak");
	            sp1.create();	            
	            
	            System.out.println("Sp1 : "+sp1);
	            System.out.println("Okay, sport créée");
	        }
	        catch(Exception e){
	            System.err.println("MAUVAIS!!! Exception lors de la création d'un sport");
	        }*/
	        
	        try{
	            System.out.println("\nTest de lecture d'un sport qui fonctionne");
	            System.out.println("=========================================");
	            sp1 = new SportDB("kayak");
	            sp1.read();	            
	            
	            System.out.println("Sp1 : "+sp1);
	            System.out.println("Okay, sport lu");
	        }
	        catch(Exception e){
	            System.err.println("MAUVAIS!!! Exception lors de la lecture d'un sport");
	        }
	}

}
