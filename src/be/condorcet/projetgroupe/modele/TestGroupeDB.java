package be.condorcet.projetgroupe.modele;

import java.sql.*;
import java.util.*;

public class TestGroupeDB {

	public static void main(String[] args) {
		DBConnection dbc = new DBConnection();
		Connection con = dbc.getConnection();
		if(con==null) {
			System.out.println("Connexion impossible");
			System.exit(0);
		}
		else {
			System.out.println("Connexion reussi");
			GroupeDB.setConnection(con);
			GroupeDB gp1;
			/**
			 * Test ajout groupe
			 */
			try {
				System.out.println("\nTest d'ajout d'un groupe");
				System.out.println("===================================");
				gp1 = new GroupeDB("Footi","sport",61,20,2);
				gp1.create();
				
				System.out.println("GP1 : "+gp1);
				System.out.println("Groupe creee");
			}
			catch(Exception e){
				System.err.println("Erreur de creation d un groupe");
			}
		}
	}

}
