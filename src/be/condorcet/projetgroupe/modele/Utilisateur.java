package be.condorcet.projetgroupe.modele;

import java.sql.Date;

public class Utilisateur {
	/**
	 * identifiant unique de l'utilisateur
	 */
	protected int idUser;
	/**
	 * nom de l'utilisateur
	 */
	protected String nomUser;
	/**
	 * prenom de l'utilisateur
	 */
	protected String prenomUser;
	/**
	 * mot de passe 
	 */
	protected String mdp;
	/**
	 * pseudo de l'utilisateur
	 */
	protected String pseudoUser;
	/**
	 * email de l'utilisateur
	 */
	protected String email;
	/**
	 * date de naissance de l'utilisateur
	 */
	protected String dateNaissance;
	/**
	 * genre de l'utilisateur
	 */
	protected String gender;
	/**
	 * sport favoris 1
	 */
	protected String sportFavoris1;
	/**
	 * sport favoris 2
	 */
	protected String sportFavoris2;
	/**
	 * sport favoris 3
	 */
	protected String sportFavoris3;
	/**
	 * constructeur par défaut
	 */
	public Utilisateur(){
		
	}
	/**
	 * constructeur paramétré
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
	public Utilisateur(int idUser, String nomUser, String prenomUser,
			String mdp, String pseudoUser, String email, String dateNaissance,
			String gender, String sportFavoris1, String sportFavoris2,
			String sportFavoris3) {
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.mdp = mdp;
		this.pseudoUser = pseudoUser;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.gender = gender;
		this.sportFavoris1 = sportFavoris1;
		this.sportFavoris2 = sportFavoris2;
		this.sportFavoris3 = sportFavoris3;
	}
	
	
	
	/**
	 * getter idUser
	 * @return
	 */
	public int getIdUser() {
		return idUser;
	}
	/**
	 * setter idUser
	 * @param idUser
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	/**
	 * getter nomUser
	 * @return
	 */
	public String getNomUser() {
		return nomUser;
	}
	/**
	 * setter nomUser
	 * @param nomUser
	 */
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	/**
	 * getter prenomUser
	 * @return
	 */
	public String getPrenomUser() {
		return prenomUser;
	}
	/**
	 * setter prenomUser
	 * @param prenomUser
	 */
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	/**
	 * getter mdp
	 * @return
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * setter mdp
	 * @param mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * getter pseudoUser
	 * @return
	 */
	public String getPseudoUser() {
		return pseudoUser;
	}
	/**
	 * setter pseudoUser
	 * @param pseudoUser
	 */
	public void setPseudoUser(String pseudoUser) {
		this.pseudoUser = pseudoUser;
	}
	/**
	 * getter email
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getter dateNaissance
	 * @return
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * setter dateNaissance
	 * @param dateNaissance
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	/**
	 * getter gender
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * setter gender
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * getter sportFavoris1
	 * @return
	 */
	public String getSportFavoris1() {
		return sportFavoris1;
	}
	/**
	 * setter sportFavoris1
	 * @param sportFavoris1
	 */
	public void setSportFavoris1(String sportFavoris1) {
		this.sportFavoris1 = sportFavoris1;
	}
	/**
	 * getter sportFavoris2
	 * @return
	 */
	public String getSportFavoris2() {
		return sportFavoris2;
	}
	/**
	 * setter sportFavoris2
	 * @param sportFavoris2
	 */
	public void setSportFavoris2(String sportFavoris2) {
		this.sportFavoris2 = sportFavoris2;
	}
	/**
	 * getter sportFavoris3
	 * @return
	 */
	public String getSportFavoris3() {
		return sportFavoris3;
	}
	/**
	 * setter sportFavoris3
	 * @param sportFavoris3
	 */
	public void setSportFavoris3(String sportFavoris3) {
		this.sportFavoris3 = sportFavoris3;
	}
	/**
	 * méthode toString()
	 */
	@Override
	public String toString() {
		return "Utilisateur [idUser=" + idUser + ", nomUser=" + nomUser
				+ ", prenomUser=" + prenomUser + ", mdp=" + mdp
				+ ", pseudoUser=" + pseudoUser + ", email=" + email
				+ ", dateNaissance=" + dateNaissance + ", gender=" + gender
				+ ", sportFavoris1=" + sportFavoris1 + ", sportFavoris2="
				+ sportFavoris2 + ", sportFavoris3=" + sportFavoris3 + "]";
	}
	
	
	
	
	
	
}
