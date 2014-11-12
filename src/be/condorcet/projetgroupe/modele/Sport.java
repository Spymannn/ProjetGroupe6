package be.condorcet.projetgroupe.modele;

public class Sport {
	/**
	 * Identifiant unique du sport
	 */
	protected int idSport;
	/**
	 * Nom du sport
	 */
	protected String nomSport;
	/**
	 * Constructeur par défaut
	 */
	public Sport(){}
	
	/**
	 * Constructeur paramétré
	 * @param idSport
	 * @param nomSport
	 */
	public Sport(int idSport,String nomSport){
		this.idSport = idSport;
		this.nomSport = nomSport;
	}
	
	/**
	 * Constructeur paramétré du nomSport pour les recherches par nom
	 * @param nomSport
	 */
	public Sport(String nomSport){
		this.nomSport = nomSport;
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
		this.idSport = idSport;
	}
	/**
	 * Getter nomSport
	 * @return
	 */
	public String getNomSport() {
		return nomSport;
	}
	/**
	 * Setter nomSport
	 * @param nomSport
	 */
	public void setNomSport(String nomSport) {
		this.nomSport = nomSport;
	}
	/**
	 * Méthode toString()
	 * @return informations completes d'un sport
	 */
	@Override
	public String toString() {
		return "Sport [idSport=" + idSport + ", nomSport=" + nomSport + "]";
	}
	
}
