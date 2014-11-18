package be.condorcet.projetgroupe.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import android.util.Log;

public class UtilisateurDB extends Utilisateur implements CRUD{
	/**
	  * connexion à la base de données partagée entre toutes les instances(statique)
	  */
	protected static Connection dbConnect = null;
	/**
	 * Constructeur par défaut
	 */
	public UtilisateurDB(){
		
	}
	/**
	 * Constructeur paramétré
	 * @param idSport
	 * @param nomSport
	 */
	public UtilisateurDB(int idUser, String nomUser, String prenomUser,
			String mdp, String pseudoUser, String email, Date dateNaissance,
			String gender, String sportFavoris1, String sportFavoris2,
			String sportFavoris3){
		super(idUser,nomUser, prenomUser, mdp, pseudoUser, email, dateNaissance, gender, sportFavoris1, sportFavoris2, sportFavoris3);
		
	}
	/**
	   * méthode statique permettant de partager la connexion entre toutes les instances de
	   * UtilisateurDB
	   * @param nouvdbConnect connexion à la base de données
	   */
	public static void setConnection(Connection nouvdbConnect){
		dbConnect = nouvdbConnect;
	}
	/**
	 * Création d'un nouvel utilisateur
	 */
	@Override
	public void create() throws Exception {
		CallableStatement cstmt=null;
		
	       try{
		     String req = "call createSport(?,?,?,?,?,?,?,?,?,?,?)";
		     cstmt = dbConnect.prepareCall(req);
	         cstmt.setString(nomUser, prenomUser, mdp, pseudoUser, email, (java.sql.Date)dateNaissance, gender, sportFavoris1, sportFavoris2, sportFavoris3);
		     cstmt.executeUpdate();
	       }
	       catch(Exception e){
	    	   throw new Exception("Erreur de création "+e.getMessage());
	       }
	       finally{//effectué dans tous les cas

	    	   try{
	    		   cstmt.close();
	            }
	    	   catch(Exception e){}
	        }
	}
	/**
	 * Lecture d'un utilisateur par rapport à son nom 
	 * permet de récupérer son id
	 */
	@Override
	public void read() throws Exception {
		String req = "select * from utilisateur where nomUser  = ?"; 
       PreparedStatement  pstmt=null;
       try{
       	pstmt=dbConnect.prepareStatement(req);
       	pstmt.setString(1,nomUser);
    	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
       	
       	    
        if(rs.next()){
	     	this.idUser=rs.getInt("idUser");
	     	//this.nomUser = nomUser;
	     	}
	      else { 
	             throw new Exception("Code inconnu");
	      }

           }
	catch(Exception e){
		 Log.d("connexion","erreur"+e);   
               throw new Exception("Erreur de lecture "+e.getMessage());
            }
       finally{//effectué dans tous les cas 
           try{
             pstmt.close();
           }
           catch (Exception e){}
       }
		
	}
	/**
	 * Lecture de tous les utilisateurs
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<UtilisateurDB> afficheTousUtilisateurs()throws Exception{
	    ArrayList<UtilisateurDB> utilisateur=new ArrayList<UtilisateurDB>();
	    String req = "select * from utilisateur"; 
       PreparedStatement  pstmt=null;
	    try{
	    	pstmt=dbConnect.prepareStatement(req);
    	    ResultSet rs=(ResultSet)pstmt.executeQuery();
    	    boolean ok = false;
           while(rs.next()){
           	ok = true;
           	int idUser = rs.getInt("idUser");
           	String nomUser = rs.getString("nomUser");
           	String prenomUser = rs.getString("prenomUser");
           	
           	utilisateur.add(new UtilisateurDB(idUser,nomUser, prenomUser));
	      }
	   
             if(!ok)throw new Exception("nom inconnu");
             else return utilisateur;
	     }
	     catch(Exception e){
		
               throw new Exception("Erreur de lecture "+e.getMessage());
            }
           finally{//effectué dans tous les cas 
           try{
             pstmt.close();
           }
           catch (Exception e){}
       }
    }
	/**
	 * Update du nom d'un utilisateur
	 */
	@Override
	public void update() throws Exception {
		CallableStatement cstmt=null;

	    try{
		     String req = "call updatesportnom(?,?)";
		     cstmt=dbConnect.prepareCall(req);
		     PreparedStatement pstm = dbConnect.prepareStatement(req);
		     cstmt.setInt(1,idUser);
		     cstmt.setString(2,nomUser);
		     cstmt.executeUpdate();
	            
	    }

		  catch(Exception e){
		  	
	                throw new Exception("Erreur de mise à jour "+e.getMessage());
	             }
	          finally{//effectué dans tous les cas 
	            try{
	              cstmt.close();
	            }
	            catch (Exception e){}
	        }
		
	}
	
	/**
	 * Delete d'un utilisateur via son id
	 */
	@Override
	public void delete() throws Exception {
		 CallableStatement cstmt =null;
		   try{
		     String req = "call deleteUser(?)";
		     cstmt = dbConnect.prepareCall(req);
		     cstmt.setInt(1,idUser);
		     cstmt.executeUpdate();
		     	     
		     }	
		   catch (Exception e){
		     	
	                throw new Exception("Erreur d'effacement "+e.getMessage());
	             }
	           finally{//effectué dans tous les cas 
	            try{
	              cstmt.close();
	            }
	            catch (Exception e){}
	          }
		
	}

}

