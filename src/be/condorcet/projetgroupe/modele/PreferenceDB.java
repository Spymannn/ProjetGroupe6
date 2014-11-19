package be.condorcet.projetgroupe.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import android.util.Log;

public class PreferenceDB extends Preference implements CRUD{
	/**
	  * connexion à la base de données partagée entre toutes les instances(statique)
	  */
	protected static Connection dbConnect = null;
	/**
	 * Constructeur par défaut
	 */
	public PreferenceDB(){
		
	}
	
	public PreferenceDB(int idUser,int idSport){
		super(idUser,idSport);
	}
	
	public PreferenceDB(int idUser){
		super(idUser,0);
	}
	
	public PreferenceDB(String idSport){
		super(0,Integer.parseInt(idSport));
	}
	
	
	
	/**
	   * méthode statique permettant de partager la connexion entre toutes les instances de
	   * PreferenceDB
	   * @param nouvdbConnect connexion à la base de données
	   */
	public static void setConnection(Connection nouvdbConnect){
		dbConnect = nouvdbConnect;
	}

	@Override
	public void create() throws Exception {
		/**
		 * Pas de création pour les préférences
		 * car les préférences se mettent
		 * directement lors de la création 
		 * d'un utilisateur
		 */		
	}

	@Override
	public void read() throws Exception {
		String req = "select * from preference where idUser  = ? and idSport = ?"; 
        PreparedStatement  pstmt=null;
        try{
        	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setInt(1,idUser);
        	pstmt.setInt(2, idSport);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
     	  
         if(rs.next()){
	     	this.idSport=rs.getInt("idSport");
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
	

	public ArrayList<PreferenceDB> listeSports() throws Exception{
		
		ArrayList<PreferenceDB> listeSports=new ArrayList<PreferenceDB>();
		
	    String req = "select * from preference where idUser = ?"; 
        PreparedStatement  pstmt=null;
	    try{
	    	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setInt(1,idUser);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
     	    boolean ok = false;
            while(rs.next()){
            	ok = true;
            	int idSport = rs.getInt("idSport");
            	listeSports.add(new PreferenceDB(idUser,idSport));
	      }
	   
              if(!ok)throw new Exception("nom inconnu");
              else return listeSports;
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
	
	
public ArrayList<PreferenceDB> listeUsers() throws Exception{
		
		ArrayList<PreferenceDB> listeUsers=new ArrayList<PreferenceDB>();
		
	    String req = "select * from preference where idSport = ?"; 
        PreparedStatement  pstmt=null;
	    try{
	    	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setInt(1,idSport);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
     	    boolean ok = false;
            while(rs.next()){
            	ok = true;
            	int idUser = rs.getInt("idUser");
            	listeUsers.add(new PreferenceDB(idUser,idSport));
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
		/**
		 * Pas développée, une préférence se supprime si un utilisateur se supprime
		 * ou si un sport se supprime
		 */
		
	}

}
