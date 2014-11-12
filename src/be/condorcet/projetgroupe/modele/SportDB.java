package be.condorcet.projetgroupe.modele;

import java.sql.*;
import java.util.*;


import android.util.Log;

public class SportDB extends Sport implements CRUD{
	
	protected static Connection dbConnect = null;
	
	public SportDB(){
		
	}
	
	
	public SportDB(int idSport,String nomSport){
		super(idSport,nomSport);
		
	}
	
	public SportDB(String nomSport){
		super(0,nomSport);
	}
	
	public SportDB(int idSport){
		super(idSport,"");
	}
	
	public static void setConnection(Connection nouvdbConnect){
		dbConnect = nouvdbConnect;
	}
	
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

	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
