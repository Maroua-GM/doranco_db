package fr.doranco.salaires.enums;

public enum Ville {
	PARIS("Paris"), LYON("Lyon"), MARSEILLE("Marseille"), AIX_EN_PROVENCE("aix- en-Provence");

	private String ville;

	public String getValue() {
		return ville;
	}

	private Ville(String ville) {
		this.ville = ville;
	}

}
