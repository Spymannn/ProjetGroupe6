package be.condorcet.projetgroupe.modele;

import java.sql.Connection;
import java.util.ArrayList;

public class TestPreferenceDB {
	
	public static void main(String[] args){
		DBConnection dbc =new DBConnection();
        Connection con = dbc.getConnection(); 
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        else
        	System.out.println("Connexion r�ussie");
        PreferenceDB.setConnection(con);
        PreferenceDB p1;
        
        
        try{
            System.out.println("\nTest de lecture d'une pr�f�rence fonctionnelle");
            System.out.println("==============================================");
            p1 = new PreferenceDB(61,4);
            p1.read();	            
            
            System.out.println("p1 : "+p1);
            System.out.println("Okay, lecture ok");
        }
        catch(Exception e){
            System.err.println("MAUVAIS!!! Exception lors de la cr�ation d'un sport");
        }
        
        try{
            System.out.println("\nTest de lecture d'une pr�f�rence non fonctionnelle");
            System.out.println("=================================================");
            p1 = new PreferenceDB(80,4);
            p1.read();	            
            
            System.out.println("p1 : "+p1);
            System.err.println("Erreur, cette pr�f�rence n'existe pas");
        }
        catch(Exception e){
            System.out.println("Okay, exception normale, la pr�f�rence n'existe pas");
        }
        
		
       try{
            System.out.println("\nTest d'afficher tous les users d'un sport");
            System.out.println("==================================");
            p1 = new PreferenceDB("4");
            ArrayList<PreferenceDB> tab = p1.listeUsers();
            for(Preference t : tab){
                System.out.println(t);
            }
            System.out.println("okay tous les users affich�s ");
        }
        catch(Exception e){
            
            System.err.println("MAUVAIS!!! Exception lors de la lecture des users "+e);
        }
       
       try{
           System.out.println("\nTest d'afficher tous les sports d'un user");
           System.out.println("=====================================");
           p1 = new PreferenceDB(42);
           ArrayList<PreferenceDB> tab = p1.listeSports();
           for(Preference t : tab){
               System.out.println(t);
           }
           System.out.println("okay tous les sports affich�s ");
       }
       catch(Exception e){
           
           System.err.println("MAUVAIS!!! Exception lors de la lecture des users "+e);
       }
		
	}

}
