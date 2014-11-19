package be.condorcet.projetgroupe.modele;

import java.sql.Connection;
import java.util.ArrayList;

public class TestParticipantDB {

	public static void main(String[] args){
		 DBConnection dbc =new DBConnection();
	        Connection con = dbc.getConnection(); 
	        if(con==null){
	            System.out.println("Connexion impossible");
	            System.exit(0);
	        }
	        else
	        	System.out.println("Connexion réussie");
	        ParticipantDB.setConnection(con);
	        ParticipantDB p1;
	        
	        
	        try{
	            System.out.println("\nTest d'un ajout d'un participant");
	            System.out.println("=========================================");
	            p1 = new ParticipantDB(61,25);
	            p1.create();	            
	            
	            System.out.println("p1 : "+p1);
	            System.out.println("Okay, participant créée");
	        }
	        catch(Exception e){
	            System.err.println("MAUVAIS!!! Exception lors de la création d'un sport");
	        }
	        
	        try{
	            System.out.println("\nTest d'un ajout doublon d'un participant");
	            System.out.println("=========================================");
	            p1 = new ParticipantDB(61,25);
	            p1.create();	            
	            
	            System.out.println("p1 : "+p1);
	            System.err.println("Erreur, ça doit lancer une exception");
	        }
	        catch(Exception e){
	            System.out.println("ok, exception correcte et logique");
	        }
	        
	        try{
	            System.out.println("\nTest de lecture d'un participant qui fonctionne");
	            System.out.println("=============================================");
	            p1 = new ParticipantDB(61,25);
	            p1.read();	            
	            
	            System.out.println("p1 : "+p1);
	            System.out.println("Okay, participant lu");
	        }
	        catch(Exception e){
	            System.err.println("MAUVAIS!!! Exception lors de la lecture d'un participant");
	        }
	        
	        try{
	            System.out.println("\nTest de lecture d'un participant qui non fonctionnel");
	            System.out.println("=============================================");
	            p1 = new ParticipantDB(64,25);
	            p1.read();	            
	            
	            System.out.println("p1 : "+p1);
	            System.err.println("Erreur, participant inexistant");
	        }
	        catch(Exception e){
	            System.out.println("Okay, exception normal est logique"+e);
	        }
	        
	       try{
	            System.out.println("\nTest d'afficher tous les groupes d'un user");
	            System.out.println("==================================");
	            p1 = new ParticipantDB(61);
	            ArrayList<ParticipantDB> tab = p1.listeGroupe();
	            for(Participant t : tab){
	                System.out.println(t);
	            }
	            System.out.println("okay tous les groupes affichés ");
	        }
	        catch(Exception e){
	            
	            System.err.println("MAUVAIS!!! Exception lors de la lecture des groupes "+e);
	        }
	        
	        try{
	            System.out.println("\nTest d'afficher tous les users d'un groupe");
	            System.out.println("==================================");
	            p1 = new ParticipantDB("25");
	            ArrayList<ParticipantDB> tab = p1.listeUser();
	            for(Participant t : tab){
	                System.out.println(t);
	            }
	            System.out.println("okay tous les users affichés ");
	        }
	        catch(Exception e){
	            
	            System.err.println("MAUVAIS!!! Exception lors de la lecture des users "+e);
	        }
	        try{
	        	System.out.println("\nTest de suppression d'un participant");
	        	System.out.println("\n==================================");
	        	p1 = new ParticipantDB(61,25);
	        	p1.delete();
	        	System.out.println("Okay, suppression effectuée");
	        	
	        }
	        catch(Exception e){
	        	System.err.println("Erreur lors de la suppression");
	        }
	        
	        try{
	        	System.out.println("\nTest de suppression d'un participant non fonctionnelle");
	        	System.out.println("\n===================================================");
	        	p1 = new ParticipantDB(65,25);
	        	p1.delete();
	        	System.err.println("Erreur lors de la suppression");
	        	
	        }
	        catch(Exception e){
	        	System.out.println("Okay, exception logique");
	        }
	        
		
	}
}
