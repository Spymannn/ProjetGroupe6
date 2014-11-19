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
	
	public GroupeDB(String nomGroupe, String mdpGroupe, int idSport, int admin, int maxUser){
		super(0,nomGroupe,mdpGroupe,idSport,admin,maxUser,0);
	}
	
	public GroupeDB(int idGroupe) {
		super(idGroupe,"","",0,0,0,0);
	}
	
	public GroupeDB(String nomGroupe) {
		super(0,nomGroupe,"",0,0,0,0);
	}
	
	public static void setConnection(Connection nouvdbConnect){
		dbConnect = nouvdbConnect;
	}
	
	@Override
	public void create() throws Exception {
		CallableStatement cstmt=null;
		try {
			String req = "call createGroupe(?,?,?,?,?)";
			cstmt = dbConnect.prepareCall(req);
			cstmt.setString(1, nomGroupe);
			cstmt.setString(2, mdpGroupe);
			cstmt.setInt(3, admin);
			cstmt.setInt(4, maxUser);
			cstmt.setInt(5, idSport);
			cstmt.executeUpdate();
		}
		catch (Exception e){
			throw new Exception("Erreur de création"+e.getMessage());
		}
		finally {
			try {
				cstmt.close();
			}
			catch(Exception e) {}
		}
	}
	
	public void delete() throws Exception {
		CallableStatement cstmt = null;
		try {
			String req = "call deleteGroupe(?)";
			cstmt = dbConnect.prepareCall(req);
			cstmt.setInt(1, idGroupe);
			cstmt.executeUpdate();
		}
		catch (Exception e){
			throw new Exception("Erreur de suppression"+e.getMessage());
		}
		finally {
			try {
				cstmt.close();
			}
			catch (Exception e) {}
		}
	}
	
	public void read() throws Exception {
		String req = "select * from groupe where nomGroupe = ?";
		PreparedStatement pstmt=null;
		try {
			pstmt=dbConnect.prepareStatement(req);
			pstmt.setString(1, nomGroupe);
			ResultSet rs = (ResultSet)pstmt.executeQuery();
			
			if(rs.next()) {
				this.idGroupe=rs.getInt("idGroupe");
				this.mdpGroupe=rs.getString("mdpGroupe");
				this.idSport=rs.getInt("idSport");
				this.admin=rs.getInt("admin");
				this.maxUser=rs.getInt("maxUser");
				this.nbrUser=rs.getInt("nbrUser");
			}
			else {
				throw new Exception ("Code Inconnu");
			}
		}
		catch(Exception e) {
			throw new Exception("Erreur de lecture "+e.getMessage());
		}
		finally {
			try {
				pstmt.close();
			}
			catch (Exception e) {}
		}
	}
	
	public static ArrayList<GroupeDB> afficheTousGroupe() throws Exception {
		ArrayList<GroupeDB> groupes=new ArrayList<GroupeDB>();
		String req = "select * from groupe";
		PreparedStatement pstmt=null;
		try {
			pstmt=dbConnect.prepareStatement(req);
			ResultSet rs=(ResultSet)pstmt.executeQuery();
			boolean ok = false;
			while(rs.next()) {
				ok = true;
				int idGroupe = rs.getInt("idGroupe");
				String nomGroupe = rs.getString("nomGroupe");
				String mdpGroupe = rs.getString("mdpGroupe");
				int idSport = rs.getInt("idSport");
				int admin = rs.getInt("admin");
				int maxUser = rs.getInt("maxUser");
				int nbrUser = rs.getInt("nbrUser");
				groupes.add(new GroupeDB(idGroupe,nomGroupe,mdpGroupe,idSport,admin,maxUser,nbrUser));
			}
			if(!ok)throw new Exception("nom inconnu");
			else return groupes;
		}
		catch (Exception e) {
			throw new Exception ("Erreur de lecture "+e.getMessage());
		}
		finally {
			try {
				pstmt.close();
			}
			catch (Exception e) {}
		}
	}
	
	public void updateNom() throws Exception {
		CallableStatement cstmt = null;
		try {
			String req = "call updateGroupeNom(?,?)";
			cstmt=dbConnect.prepareCall(req);
			PreparedStatement pstm = dbConnect.prepareStatement(req);
			cstmt.setInt(1, idGroupe);
			cstmt.setString(2, nomGroupe);
			cstmt.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception ("Erreur d update N "+e.getMessage());
		}
		finally {
			try {
				cstmt.close();
			}
			catch (Exception e) {}
		}
	}
	
	public void updateMaxUser() throws Exception {
		CallableStatement cstmt = null;
		try {
			String req = "call updateGroupeMaxUser(?,?)";
			cstmt=dbConnect.prepareCall(req);
			PreparedStatement pstm = dbConnect.prepareStatement(req);
			cstmt.setInt(1, idGroupe);
			cstmt.setInt(2, maxUser);
			cstmt.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception ("Erreur d'update M "+e.getMessage());
		}
		finally {
			try {
				cstmt.close();
			}
			catch (Exception e) {}
		}
	}
	
	public void updatePassword() throws Exception {
		CallableStatement cstmt = null;
		try {
			String req = "call updateGroupePassword (?,?)";
			cstmt=dbConnect.prepareCall(req);
			PreparedStatement pstm = dbConnect.prepareStatement(req);
			cstmt.setInt(1, idGroupe);
			cstmt.setString(2, mdpGroupe);
			cstmt.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception ("Erreur d'update P "+e.getMessage());
		}
		finally {
			try {
				cstmt.close();
			}
			catch (Exception e) {}
		}
	}

	@Override
	public void update() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
