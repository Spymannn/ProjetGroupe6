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
	        
	        try{
	            System.out.println("\nTest d'ajout d'un sport fonctionnel");
	            System.out.println("=========================================");
	            sp1 = new SportDB("kayak");
	            sp1.create();	            
	            
	            System.out.println("Sp1 : "+sp1);
	            System.out.println("Okay, sport créée");
	        }
	        catch(Exception e){
	            System.err.println("MAUVAIS!!! Exception lors de la création d'un sport");
	        }
	        try{
	            System.out.println("\nTest d'ajout d'un sport doublon");
	            System.out.println("=========================================");
	            sp1 = new SportDB("basketball");
	            sp1.create();	            
	            
	            System.out.println("Sp1 : "+sp1);
	            System.err.println("ERREUR, sport créée");
	        }
	        catch(Exception e){
	            System.out.println("Okay, exception normale et logique, pas d'ajout de doublon");
	        }
	        
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
	        try{
	            System.out.println("\nTest de lecture d'un sport inexistant");
	            System.out.println("=========================================");
	            sp1 = new SportDB("kayak");
	            sp1.read();	            
	            
	            System.out.println("Sp1 : "+sp1);
	            System.err.println("MAUVAIS, sport inexistant logiquement");
	        }
	        catch(Exception e){
	            System.out.println("Okay, exception normal et logique, le sport n'éxiste pas");
	        }
	        
	        try{
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
	        }
	        
	        
	        try{
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
	        }
	        try{
	            System.out.println("\nTest d'update d'un sport inconnu");
	            System.out.println("==========================");
	            SportDB sp2 = new SportDB(23,"saut en hauteur");
	            sp2.update();
	            
	            sp2.read();
	            System.err.println("sp2 : "+sp2);
	            System.err.println("ERREUR, rien ne devrait être update");
	        }
	        catch(Exception e){
	            System.out.println("okay, rien ne devrait être update");
	        }
	        
	        try{
	            System.out.println("\nTest d'update d'un sport avec nom déjà utilisé");
	            System.out.println("==============================================");
	            SportDB sp2 = new SportDB(21,"basketball");
	            sp2.update();
	            
	            sp2.read();
	            System.err.println("sp2 : "+sp2);
	            System.err.println("ERREUR, rien ne devrait être update");
	        }
	        catch(Exception e){
	            System.out.println("okay, rien ne devrait être update");
	        }
	        
	        try{
	        	System.out.println("\nTest de suppression d'un sport");
	        	System.out.println("\n==============================");
	        	sp1 = new SportDB(22);
	        	sp1.delete();
	        	System.out.println("Okay, suppression effectuée");
	        	
	        }
	        catch(Exception e){
	        	System.err.println("Erreur lors de la suppression");
	        }
	        
	        try{
	        	System.out.println("\nTest de suppression d'un sport qui est utilisé pour un groupe");
	        	System.out.println("\n=============================================================");
	        	sp1 = new SportDB(2);
	        	sp1.delete();
	        	System.err.println("Erreur, le sport ne doit pas être supprimé");
	        	
	        }
	        catch(Exception e){
	        	System.out.println("Okay, suppression non effectuée, exception normale");
	        }
	        try{
	        	System.out.println("\nTest de suppression d'un sport inexistant");
	        	System.out.println("\n=============================================================");
	        	sp1 = new SportDB(24);
	        	sp1.delete();
	        	System.err.println("Erreur, le sport ne doit pas être supprimé");
	        	
	        }
	        catch(Exception e){
	        	System.out.println("Okay, suppression non effectuée, exception normale");
	        }
	        
	        
	}

}
