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
	 * @param idUser
	 * @param nomUser
	 * @param prenomUser
	 * @param mdp
	 * @param pseudoUser
	 * @param email
	 * @param dateNaissance
	 * @param gender
	 * @param sportFavoris1
	 * @param sportFavoris2
	 * @param sportFavoris3
	 */
	public UtilisateurDB(int idUser,String nomUser, String prenomUser,
			String mdp, String pseudoUser, String email, String dateNaissance,
			String gender, String sportFavoris1, String sportFavoris2,
			String sportFavoris3){
		super(idUser,nomUser, prenomUser, mdp, pseudoUser, email, dateNaissance, gender, sportFavoris1, sportFavoris2, sportFavoris3);
		
	}
	/**
	 * Constructeur de recherche de tous users
	 * @param idUser
	 * @param nomUser
	 * @param prenomUser
	 * @param mdp
	 * @param pseudoUser
	 * @param email
	 * @param dateNaissance
	 * @param gender
	 * @param sportFavoris1
	 * @param sportFavoris2
	 * @param sportFavoris3
	 */
	public UtilisateurDB(int idUser, String nomUser, String prenomUser,
			String mdp, String pseudoUser, String email, String dateNaissance,
			String gender){
		super(idUser,nomUser, prenomUser, mdp, pseudoUser, email, dateNaissance, gender, "","","");
		
	}
	/**
	 * Constructeur création Utilisateurs
	 * @param nomUser
	 * @param prenomUser
	 * @param mdp
	 * @param pseudoUser
	 * @param email
	 * @param dateNaissance
	 * @param gender
	 * @param sportFavoris1
	 * @param sportFavoris2
	 * @param sportFavoris3
	 */
	public UtilisateurDB(String nomUser, String prenomUser,
			String mdp, String pseudoUser, String email, String dateNaissance,
			String gender, String sportFavoris1, String sportFavoris2,
			String sportFavoris3){
		super(0,nomUser, prenomUser, mdp, pseudoUser, email, dateNaissance, gender, sportFavoris1, sportFavoris2, sportFavoris3);
		
	}
	/**
	 * Constructeur de read
	 * @param pseudoUser
	 */
	public UtilisateurDB(String pseudoUser){
		super(0,"", "", "", pseudoUser, "", "", "", "","", "");
	}
	/**
	 * Constructeur de delete
	 * @param idUser
	 */
	public UtilisateurDB(int idUser){
		super(idUser,"", "", "", "", "", "", "", "","", "");
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
		     String req = "call createUser(?,?,?,?,?,?,?,?,?,?)";
		     cstmt = dbConnect.prepareCall(req);
	         cstmt.setString(1,nomUser);
	         cstmt.setString(2,prenomUser);
		     cstmt.setString(3,mdp);
		     cstmt.setString(4,pseudoUser);
		     cstmt.setString(5,email);
		     cstmt.setString(6, dateNaissance);
		     cstmt.setString(7,gender);
		     cstmt.setString(8,sportFavoris1);
		     cstmt.setString(9,sportFavoris2);
		     cstmt.setString(10,sportFavoris3);
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
		String req = "select * from utilisateur where pseudoUser  = ?"; 
       PreparedStatement  pstmt=null;
       try{
       	pstmt=dbConnect.prepareStatement(req);
       	pstmt.setString(1,pseudoUser);
    	ResultSet rs=(ResultSet)pstmt.executeQuery();	
       	
       	    
        if(rs.next()){
	     	this.idUser=rs.getInt("idUser");
	     	this.nomUser = rs.getString("nomUser");
	     	this.prenomUser = rs.getString("prenomUser");
	     	this.mdp = rs.getString("mdp");
	     	this.email = rs.getString("email");
	     	this.dateNaissance = rs.getString("dateNaissance");
	     	this.gender = rs.getString("gender");
	     	}
	      else { 
	             throw new Exception("Pseudo inconnu");
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
	    ArrayList<UtilisateurDB> utilisateurs=new ArrayList<UtilisateurDB>();
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
           	String mdp = rs.getString("mdp");
           	String pseudoUser = rs.getString("pseudoUser");
           	String email = rs.getString("email");
           	String dateNaissance = rs.getString("dateNaissance");
           	String gender = rs.getString("gender");
           	
           	
           	utilisateurs.add(new UtilisateurDB(idUser,nomUser, prenomUser,mdp,pseudoUser,email,dateNaissance,gender));
	      }
	   
             if(!ok)throw new Exception("nom inconnu");
             else return utilisateurs;
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
		     String req = "call updateuserpassword(?,?)";
		     cstmt=dbConnect.prepareCall(req);
		     //PreparedStatement pstm = dbConnect.prepareStatement(req);
		     cstmt.setInt(1,idUser);
		     cstmt.setString(2,mdp);
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

