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
                        GroupeDB gp2;
			/**
			 * Test ajout groupe
			 */
			try {
				System.out.println("\nTest d'ajout d'un groupe");
				System.out.println("===================================");                               
				gp1 = new GroupeDB("Footic","",42,2,20);
                                System.out.println("passe par ici");
				gp1.create();
				
				System.out.println("GP1 : "+gp1);
				System.out.println("Groupe creee");
			}
			catch(Exception e){
				System.err.println("Erreur de creation d un groupe "+e);
			}
                /**
                 * Test d'un doublon
                 */
                try {
                    System.out.println("\nTest d'ajout d'un groupe en doublon");
                    System.out.println("=======================================");
                    gp1 = new GroupeDB("Footic","",42,2,20);
                    gp1.create();
                    System.out.println("Doublon reussi donc prob");
                }
                catch(Exception e){
                    System.out.println("Doublon raté donc pas de prob"+e);
                }
                /**
                 * Test de lecture d'un groupe qui existe
                 */
                try {
                    System.out.println("\nTest de lecture d'un groupe qui existe");
                    System.out.println("=========================================");
                    gp1 = new GroupeDB("Footic");
                    gp1.read();
                    System.out.println("GP1 : "+gp1);
                    System.out.println("Lecture reussie");
                }
                catch(Exception e){
                    System.out.println("Lecture ratée");
                }
                /**
                 * Test de lecture d'un groupe qui n'existe pas
                 */
                try {
                    System.out.println("\nTest de lecture d'un groupe qui n'existe pas ");
                    System.out.println("===================================================");
                    gp1 = new GroupeDB("FootiNExistePas");
                    gp1.read();
                    System.out.println("GP1 : "+gp1);
                    System.out.println("Lecture reussie donc prob");
                }
                catch(Exception e){
                    System.out.println("Lecture ratée donc pas de prob");
                }
                /**
                 * Test d'afficher tous les groupes
                 */
                try{
                    System.out.println("\nTest d'affichage de tous les groupes");
                    System.out.println("===========================================");
                    ArrayList<GroupeDB> tab=GroupeDB.afficheTousGroupe();
                    for(GroupeDB t: tab){
                        System.out.println(t);
                    }
                }
                catch(Exception e){
                    System.out.println("Probleme d'affichage de tous les groupes");
                }
                /**
                 * Test d'une mise a jour d'un nom de groupe
                 */
                try {
                    System.out.println("\nTest d'une mise a jour d'un nom de groupe");
                    System.out.println("=============================================");
                    gp1 = new GroupeDB("Footic");
                    gp1.read();
                    System.out.println("GP1 : "+gp1);
                    gp2 = new GroupeDB(60,"Footac");
                    gp2.updateNom();
                    gp2.read();
                    System.out.println("GP2 : "+gp2);
                    System.out.println("Mise a jour reussie");
                }
                catch(Exception e){
                    System.out.println("Erreur de mise a jour "+e);
                }
                /**
                 * Test d'une mise a jour de maxUser
                 */
                try{
                    System.out.println("\nTest d'une mise a jour de maxUser");
                    System.out.println("========================================");
                    gp1 = new GroupeDB("Footac");
                    gp1.read();
                    System.out.println("GP1 : "+gp1);                            
                    gp2 = new GroupeDB(60,35);
                    gp2.updateMaxUser();
                    gp2.readId();
                    System.out.println("GP2 : "+gp2);
                    System.out.println("Mise a jour reussie");
                }
                catch(Exception e){
                    System.out.println("Erreur de mise a jour "+e);
                }
                /**
                 * Test d'une mise a jour du mot de passe
                 */
                try{
                    System.out.println("\nTest d'une mise a jour du mot de passe");
                    System.out.println("=============================================");
                    gp1 = new GroupeDB(60);
                    gp1.readId();
                    System.out.println("GP1 : "+gp1);
                    gp2 = new GroupeDB("passwordfooti",60);
                    gp2.updatePassword();
                    gp2.readId();
                    System.out.println("GP2 : "+gp2);
                    System.out.println("Mise a jour reussie");
                }
                catch(Exception e){
                    System.out.println("Erreur de mise a jour "+e);
                }
                /**
                 * Test d'une suppression d'un groupe qui existe
                 */
                try{
                    System.out.println("\nTest d'une suppresion d'un groupe qui existe");
                    System.out.println("==================================================");
                    gp1 = new GroupeDB("Footac");
                    gp1.read();
                    gp1.delete();
                    System.out.println("Suppression reussie");
                }
                catch(Exception e){
                    System.out.println("Erreur de suppression : "+e);
                }
                /**
                 * Test d'une suppression d'un groupe qui n'existe pas
                 */
                try{
                    System.out.println("\nTest d'une suppression d'un groupe qui n'existe pas");
                    System.out.println("=========================================================");
                    gp1 = new GroupeDB("Footac");
                    gp1.read();
                    gp1.delete();
                    System.out.println("Suppression reussie donc prob");
                }
                catch(Exception e){
                    System.out.println("Suppression ratée donc pas de prob");
                }
                
                try{
                    System.out.println("\nTest d'une suppresion d'un groupe qui existe a des users");
                    System.out.println("==================================================");
                    gp1 = new GroupeDB("fifaStreet");
                    gp1.read();
                    gp1.delete();
                    System.out.println("Suppression reussie");
                }
                catch(Exception e){
                    System.out.println("Erreur de suppression : "+e);
                }
		}
	}

}
