package be.condorcet.projetgroupe.modele;

import java.sql.*;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class SportDB extends Sport implements CRUD,Parcelable{
	/**
	  * connexion à la base de données partagée entre toutes les instances(statique)
	  */
	protected static Connection dbConnect = null;
	/**
	 * Constructeur par défaut
	 */
	public SportDB(){
		
	}
	
	/**
	 * Constructeur paramétré
	 * @param idSport
	 * @param nomSport
	 */
	public SportDB(int idSport,String nomSport){
		super(idSport,nomSport);
		
	}
	/**
	 * Constructeur de création d'un sport et la recherche
	 * @param nomSport
	 */
	public SportDB(String nomSport){
		super(0,nomSport);
	}
	/**
	 * Constructeur qui permet la suppression
	 * @param idSport
	 */
	public SportDB(int idSport){
		super(idSport,"");
	}
	/**
	   * méthode statique permettant de partager la connexion entre toutes les instances de
	   * SportDB
	   * @param nouvdbConnect connexion à la base de données
	   */
	public static void setConnection(Connection nouvdbConnect){
		dbConnect = nouvdbConnect;
	}
	/**
	 * Création d'un nouveau sport
	 */
	@Override
	public void create() throws Exception {
		CallableStatement cstmt=null;
		
	       try{
		     String req = "call createSport(?)";
		     cstmt = dbConnect.prepareCall(req);
	         cstmt.setString(1,nomSport);
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
	 * Lecture d'un sport par rapport à son nom 
	 * permet de récupérer son id
	 */
	@Override
	public void read() throws Exception {
		String req = "select * from sport where nomSport  = ?"; 
        PreparedStatement  pstmt=null;
        try{
        	pstmt=dbConnect.prepareStatement(req);
        	pstmt.setString(1,nomSport);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();	
        	
        	    
         if(rs.next()){
	     	this.idSport=rs.getInt("idSport");
	     	//this.nomSport = nomSport;
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
	 * Lecture de tous les sports
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<SportDB> afficheTousSport()throws Exception{
	    ArrayList<SportDB> sports=new ArrayList<SportDB>();
	    String req = "select * from sport"; 
        PreparedStatement  pstmt=null;
	    try{
	    	pstmt=dbConnect.prepareStatement(req);
     	    ResultSet rs=(ResultSet)pstmt.executeQuery();
     	    boolean ok = false;
            while(rs.next()){
            	ok = true;
            	int idSport = rs.getInt("idSport");
            	String nomSport = rs.getString("nomSport");
            	sports.add(new SportDB(idSport,nomSport));
	      }
	   
              if(!ok)throw new Exception("nom inconnu");
              else return sports;
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
	 * Update du nom d'un sport
	 */
	@Override
	public void update() throws Exception {
		CallableStatement cstmt=null;

	    try{
		     String req = "call updatesportnom(?,?)";
		     cstmt=dbConnect.prepareCall(req);
		     //PreparedStatement pstm = dbConnect.prepareStatement(req);
		     cstmt.setInt(1,idSport);
		     cstmt.setString(2,nomSport);
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
	 * Delete d'un sport via son id
	 */
	@Override
	public void delete() throws Exception {
		 CallableStatement cstmt =null;
		   try{
		     String req = "call deletesport(?)";
		     cstmt = dbConnect.prepareCall(req);
		     cstmt.setInt(1,idSport);
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
