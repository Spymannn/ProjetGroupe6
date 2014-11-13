package be.condorcet.projetgroupe.modele;

public class Preference {
	/**
	 * identifiant de l'utilisateur
	 */
	protected int idUser;
	/**
	 * Identifiant du sport
	 */
	protected int idSport;
	/**
	 * Constructeur par défaut
	 */
	public Preference(){
		
	}
	/**
	 * Conctructeur paramétré
	 * @param idUser
	 * @param idSport
	 */
	public Preference(int idUser,int idSport){
		this.idUser = idUser;
		this.idSport = idSport;
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
	 * Getter idSport
	 * @return
	 */
	public int getIdSport() {
		return idSport;
	}
	/**
	 * Setter idSport
	 * @param idSport
	 */
	public void setIdSport(int idSport) {
		this.idSport= idSport;
	}
	/**
	 * Méthode toString de Preference
	 */
	@Override
	public String toString() {
		return "Preference [idUser=" + idUser + ", idSport=" + idSport + "]";
	}
	
	
}
