package be.condorcet.projetgroupe.modele;

public class Participant {
	/**
	 * identifiant de l'utilisateur
	 */
	protected int idUser;
	/**
	 * Identifiant du groupe
	 */
	protected int idGroupe;
	/**
	 * Constructeur par défaut
	 */
	public Participant(){
		
	}
	/**
	 * Conctructeur paramétré
	 * @param idUser
	 * @param idGroupe
	 */
	public Participant(int idUser,int idGroupe){
		this.idUser = idUser;
		this.idGroupe = idGroupe;
	}
	/**
	 * Getter idUser
	 * @return
	 */
	public int getIdUser() {
		return idUser;
	}
	/**
	 * Setter idUser
	 * @param idUser
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	/**
	 * Getter idGroupe
	 * @return
	 */
	public int getIdGroupe() {
		return idGroupe;
	}
	/**
	 * Setter idGroupe
	 * @param idGroupe
	 */
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * Méthode toString de Participant
	 */
	@Override
	public String toString() {
		return "Participant [idUser=" + idUser + ", idGroupe=" + idGroupe + "]";
	}
	
	
}
