package fr.doranco.salaires.entity;

public class Salaire {
	private int id;
	private Integer annee;
	private String mois;
	private Float salaire;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public Float getSalaire() {
		return salaire;
	}

	public void setSalaire(Float salaire) {
		this.salaire = salaire;
	}

	public Salaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salaire(Integer annee, String mois, Float salaire) {
		super();
		this.annee = annee;
		this.mois = mois;
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		return "Salaire [id=" + id + ", annee=" + annee + ", mois=" + mois + ", salaire=" + salaire + "]";
	}

}
