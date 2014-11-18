package be.condorcet.projetgroupe.modele;

public class Groupe {
	/**
	 * Identifiant unique du groupe
	 */
	protected int idGroupe;
	/**
	 * Nom du groupe
	 */
	protected String nomGroupe;
	/**
	 * Mot de passe du groupe
	 */
	protected String mdpGroupe;
	/**
	 * Identifiant du sport
	 */
	protected int idSport;
	/**
	 * Identifiant de l'administareur
	 */
	protected int admin;
	/**
	 * Nombre maximum d'utilisateur
	 */
	protected int maxUser;
	/**
	 * Nombre d'utilisateur dans le groupe
	 */
	protected int nbrUser;
	/**
	 * Constructeur par défaut
	 */
	public Groupe() {}
	/**
	 * Constructeur paramétré
	 * @param idGroupe
	 * @param nomGroupe
	 * @param mdpGroupe
	 * @param idSport
	 * @param admin
	 * @param maxUser
	 * @param nbrUser
	 */
	public Groupe(int idGroupe, String nomGroupe, String mdpGroupe, int idSport, int admin, int maxUser, int nbrUser){
		this.idGroupe = idGroupe;
		this.nomGroupe = nomGroupe;
		this.mdpGroupe = mdpGroupe;
		this.idSport = idSport;
		this.admin = admin;
		this.maxUser = maxUser;
		this.nbrUser = nbrUser;
	}
	/**
	 * Getter IdGroupe
	 * @return
	 */
	public int getIdGroupe() {
		return idGroupe;
	}
	/**
	 * Setter IdGroupe
	 * @param idGroupe
	 */
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * Getter NomGroupe
	 * @return
	 */
	public String getNomGroupe() {
		return nomGroupe;
	}
	/**
	 * Setter NomGroupe
	 * @param nomGroupe
	 */
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	/**
	 * Getter MdpGroupe
	 * @return
	 */
	public String getMdpGroupe() {
		return mdpGroupe;
	}
	/**
	 * Setter MdpGroupe
	 * @param mdpGroupe
	 */
	public void setMdpGroupe(String mdpGroupe) {
		this.mdpGroupe = mdpGroupe;
	}
	/**
	 * Getter IdSport
	 * @return
	 */
	public int getIdSport() {
		return idSport;
	}
	/**
	 * Setter IdSport
	 * @param idSport
	 */
	public void setIdSport(int idSport) {
		this.idSport = idSport;
	}
	/**
	 * Getter Admin
	 * @return
	 */
	public int getAdmin() {
		return admin;
	}
	/**
	 * Setter Admin
	 * @param admin
	 */
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	/**
	 * Getter MaxUser
	 * @return
	 */
	public int getMaxUser() {
		return maxUser;
	}
	/**
	 * Setter MaxUser
	 * @param maxUser
	 */
	public void setMaxUser(int maxUser) {
		this.maxUser = maxUser;
	}
	/**
	 * Getter NbrUser
	 * @return
	 */
	public int getNbrUser() {
		return nbrUser;
	}
	/**
	 * Setter NbrUser
	 * @param nbrUser
	 */
	public void setNbrUser(int nbrUser) {
		this.nbrUser = nbrUser;
	}
	/**
	 * Méthode toString()
	 * @return informations completes d'un groupe
	 */
	@Override
	public String toString() {
		return "Groupe [idGroupe=" + idGroupe + ", nomGroupe=" + nomGroupe
				+ ", mdpGroupe=" + mdpGroupe + ", idSport=" + idSport
				+ ", admin=" + admin + ", maxUser=" + maxUser + ", nbrUser="
				+ nbrUser + "]";
	}	
}
