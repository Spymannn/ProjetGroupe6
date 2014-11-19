package be.condorcet.projetgroupe.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import android.util.Log;

public class ParticipantDB extends Participant implements CRUD{
	/**
	  * connexion à la base de données partagée entre toutes les instances(statique)
	  */
	protected static Connection dbConnect = null;
	/**
	 * Constructeur par défaut
	 */
	public ParticipantDB(){
		
	}
	/**
	 * Constructeur paramétré
	 * @param idUser
	 * @param idGroupe
	 */
	public ParticipantDB(int idUser, int idGroupe){
		super(idUser,idGroupe);
	}
	/**
	 * Constructeur de recherche de groupe pour un User
	 * @param idUser
	 */
	public ParticipantDB(int idUser){
		super(idUser,0);
	}
	/**
	 * Constructeur de recherche d'User pour un groupe
	 * @param idGroupe
	 */
	public ParticipantDB(String idGroupe){
		super(0,Integer.parseInt(idGroupe));
	}
	
	/**
	   * méthode statique permettant de partager la connexion entre toutes les instances de
	   * ParticipantDB
	   * @param nouvdbConnect connexion à la base de données
	   */
	public static void setConnection(Connection nouvdbConnect){
		dbConnect = nouvdbConnect;
	}
	
	/**
	 * Création d'un participant
	 */
	@Override
	public void create() throws Exception {
		CallableStatement cstmt=null;
		
	       try{
		     String req = "call createParticipant(?,?)";
		     cstmt = dbConnect.prepareCall(req);
	         cstmt.setInt(1,idUser);
	         cstmt.setInt(2, idGroupe);
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

	@Override
	public void read() throws Exception {
		String req = "select * from participant where isUser  = ? and idGroupe = ?"; 
        PreparedStatement  pstmt=null;
        try{
        	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setInt(1,idUser);
        	pstmt.setInt(2, idGroupe);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
     	  
         if(rs.next()){
	     	this.idGroupe=rs.getInt("idGroupe");
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
	
	public ArrayList<ParticipantDB> listeGroupe() throws Exception{
		
		ArrayList<ParticipantDB> listeGroupes=new ArrayList<ParticipantDB>();
		
	    String req = "select * from participant where idUser = ?"; 
        PreparedStatement  pstmt=null;
	    try{
	    	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setInt(1,idUser);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
     	    boolean ok = false;
            while(rs.next()){
            	ok = true;
            	int idGroupe = rs.getInt("idGroupe");
            	listeGroupes.add(new ParticipantDB(idUser,idGroupe));
	      }
	   
              if(!ok)throw new Exception("nom inconnu");
              else return listeGroupes;
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
	
public ArrayList<ParticipantDB> listeUser() throws Exception{
		
		ArrayList<ParticipantDB> listeUsers=new ArrayList<ParticipantDB>();
		
	    String req = "select * from participant where idGroupe = ?"; 
        PreparedStatement  pstmt=null;
	    try{
	    	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setInt(1,idGroupe);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
     	    boolean ok = false;
            while(rs.next()){
            	ok = true;
            	int idUser = rs.getInt("idUser");
            	listeUsers.add(new ParticipantDB(idUser,idGroupe));
	      }
	   
              if(!ok)throw new Exception("nom inconnu");
              else return listeUsers;
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

	@Override
	public void update() throws Exception {
		// N'est pas développée car n'a pas d'intérêt
		
	}

	@Override
	public void delete() throws Exception {
		CallableStatement cstmt =null;
		   try{
		     String req = "call deleteParticipant(?,?)";
		     cstmt = dbConnect.prepareCall(req);
		     cstmt.setInt(1,idUser);
		     cstmt.setInt(2, idGroupe);
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
