package fr.doranco.salaires.enums;

public enum Mois {
	JANVIER("JANVIER"), FEVRIER("FEVRIER"), MARS("MARS"), AVRIL("AVRIL"), MAI("MAI"), JUIN("JUIN"), JUILLET("JUILLET"),
	AOUT("AOUT"), SEPTEMBRE("SEPTEMBRE"), OCTOBRE("OCTOBRE"), NOVEMBRE("NOVEMBRE"), DECEMBRE("DECEMBRE");

	private String mois;

	public String getValue() {
		return mois;
	}

	private Mois(String mois) {
		this.mois = mois;
	}

}
