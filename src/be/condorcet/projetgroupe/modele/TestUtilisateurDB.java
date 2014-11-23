package be.condorcet.projetgroupe.modele;

import java.sql.Connection;
import java.util.ArrayList;

public class TestUtilisateurDB {

	public static void main(String[] args){
		
		DBConnection dbc =new DBConnection();
        Connection con = dbc.getConnection(); 
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        else
        	System.out.println("Connexion réussie");
        UtilisateurDB.setConnection(con);
        UtilisateurDB user1;

        
        try{
            System.out.println("\nTest d'ajout d'un user fonctionnel");
            System.out.println("=========================================");
            user1 = new UtilisateurDB("James","LeBron","test","KingJames","james@cavs.com","09/09/1990","male","basketball","foot","baseball");
            user1.create();
            
            System.out.println("User1 : "+user1);
            System.out.println("Okay, utilisateur créée");
        }
        catch(Exception e){
            System.err.println("MAUVAIS!!! Exception lors de la création d'un user "+e);
        }
        
        try{
            System.out.println("\nTest d'ajout d'un user doublon avec pseudo");
            System.out.println("=========================================");
            user1 = new UtilisateurDB("James","LeBrn","test","KingJames","james@test","09/09/1990","male","basketball","foot","baseball");
            user1.create();
            
            System.out.println("User1 : "+user1);
            System.err.println("MAUVAIS !!! impossible car doublon");
        }
        catch(Exception e){
            System.out.println("Okay exception normal!!! doublon dans pseudo "+e);
        }
        
        try{
            System.out.println("\nTest d'ajout d'un user doublon avec adresse mail");
            System.out.println("==============================================");
            user1 = new UtilisateurDB("James","LeB","test","KingJamesIII","james@cavs.com","09/09/1990","male","basketball","foot","baseball");
            user1.create();
            
            System.out.println("User1 : "+user1);
            System.err.println("MAUVAIS !!! impossible car doublon adresse mail");
        }
        catch(Exception e){
            System.out.println("Okay exception normal!!! doublon dans adresse mail "+e);
        }
        
        try{
            System.out.println("\nTest de lecture d'un utilisateur qui fonctionne");
            System.out.println("=========================================");
            user1 = new UtilisateurDB("KingJames");
            user1.read();	            
            
            System.out.println("User1 : "+user1);
            System.out.println("Okay, utilisateur lu");
        }
        catch(Exception e){
            System.err.println("MAUVAIS!!! Exception lors de la lecture d'un utilisateur");
        }
        
        try{
            System.out.println("\nTest de lecture d'un utilisateur introuvable");
            System.out.println("=========================================");
            user1 = new UtilisateurDB("KingJamSSS");
            user1.read();	            
            
            System.out.println("User1 : "+user1);
            System.err.println("ERREUR, utilisateur pas dans la base de données");
        }
        catch(Exception e){
            System.out.println("Okay, exception logique, l'utilisateur n'existe pas");
        }
        
        try{
            System.out.println("\nTest d'afficher tous les user");
            System.out.println("==================================");
            ArrayList<UtilisateurDB> tab = UtilisateurDB.afficheTousUtilisateurs();
            for(UtilisateurDB t : tab){
                System.out.println(t);
            }
            System.out.println("okay tous les utilisateurs affichés ");
        }
        catch(Exception e){
            
            System.err.println("MAUVAIS!!! Exception lors de la lecture des utilisateurs "+e);
        }
        
        try{
            System.out.println("\nTest de mise à jours d'un password");
            System.out.println("=========================================");
            user1 = new UtilisateurDB("KingJames");
            user1.read();	
            System.out.println("User1 : "+user1);
            user1.setMdp("nouveauMDP");
            user1.update();
            user1.read();	
            System.out.println("User1 : "+user1);
            System.out.println("Okay, mdp bien modifié");
        }
        catch(Exception e){
            System.err.println("MAUVAIS!!! Exception lors de l'update du mdp");
        }
        
        try{
            System.out.println("\nTest de mise à jours d'un password d'un user inconnu");
            System.out.println("==================================================");
            user1 = new UtilisateurDB("KingJamesSSSSS");
            user1.read();	
            System.out.println("User1 : "+user1);
            user1.setMdp("nouveauMDP");
            user1.update();
            user1.read();	
            System.out.println("User1 : "+user1);
            System.err.println("ERREUR, user inconnu");
        }
        catch(Exception e){
            System.out.println("Exception normale et logique, utilisateur inconnu");
        }
        
        try{
        	System.out.println("\nTest de suppression d'un user");
        	System.out.println("\n==============================");
        	user1 = new UtilisateurDB("KingJames");
        	user1.read();
        	user1.delete();
        	
        	System.out.println("Okay, suppression effectuée");
        	
        }
        catch(Exception e){
        	System.err.println("Erreur lors de la suppression");
        }
        
        try{
        	System.out.println("\nTest de suppression d'un user inconnu");
        	System.out.println("\n==============================");
        	user1 = new UtilisateurDB("KingJames");
        	user1.read();
        	user1.delete();
        	
        	System.err.println("erreur, l'utilisateur n'existe pas");
        	
        }
        catch(Exception e){
        	System.out.println("Okay, utilisateur n'existe pas");
        }
	}
}
