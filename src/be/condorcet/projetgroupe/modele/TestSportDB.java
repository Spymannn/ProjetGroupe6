package be.condorcet.projetgroupe.modele;

import java.sql.Connection;
import java.util.ArrayList;

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
	        
	        /*try{
	            System.out.println("\nTest de lecture d'un sport qui fonctionne");
	            System.out.println("=========================================");
	            sp1 = new SportDB("kayak");
	            sp1.read();	            
	            
	            System.out.println("Sp1 : "+sp1);
	            System.out.println("Okay, sport lu");
	        }
	        catch(Exception e){
	            System.err.println("MAUVAIS!!! Exception lors de la lecture d'un sport");
	        }*/
	        
	        /*try{
	            System.out.println("\nTest d'afficher tous les sports");
	            System.out.println("==================================");
	            ArrayList<SportDB> tab = SportDB.afficheTousSport();
	            for(SportDB t : tab){
	                System.out.println(t);
	            }
	            System.out.println("okay tous les sports affichés ");
	        }
	        catch(Exception e){
	            
	            System.err.println("MAUVAIS!!! Exception lors de la lecture des sports "+e);
	        }*/
	        
	        /*try{
	            System.out.println("\nTest d'update nom sport");
	            System.out.println("==========================");
	            SportDB sp2 = new SportDB(22,"saut en hauteur");
	            sp2.update();
	            
	            sp2.read();
	            System.out.println("sp2 : "+sp2);
	            System.out.println("Ok update fonctionnel");
	        }
	        catch(Exception e){
	            System.err.println("ERREUR lors de l'update");
	        }*/
	        
	        try{
	        	System.out.println("\nTest de suppression d'un sport");
	        	System.out.println("\n==============================");
	        	sp1 = new SportDB(22);
	        	sp1.delete();
	        	//sp1.read();
	        	//System.out.println("Sp1 : "+ sp1);
	        	
	        }
	        catch(Exception e){
	        	System.err.println("Erreur lors de la suppression");
	        }
	}

}
