package be.condorcet.projetgroupe.modele;

import java.sql.*;
import java.util.*;

public class GroupeDB extends Groupe implements CRUD {
	
	protected static Connection dbConnect = null;
	
	protected GroupeDB() {
		
	}
	
	public GroupeDB(int idGroupe, String nomGroupe, String mdpGroupe, int idSport, int admin, int maxUser, int nbrUser){
		super(idGroupe,nomGroupe,mdpGroupe,idSport,admin,maxUser,nbrUser);
	}
	
	@Override
	public void delete() throws Exception {
		try {
			
		}
		catch (Exception e){
			throw new Exception("Erreur");
		}
	}
	
	public void create() throws Exception {
		
	}
	
	public void read() throws Exception {
		
	}
	
	public void update() throws Exception {
		
	}
}
