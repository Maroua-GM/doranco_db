package fr.doranco.salaires.enums;

public enum Status {
	DEVELOPPEUR("Développeur"), MANAGER("Manager"), VENDEUR("Vendeur"), TECHNICIEN_A_RISQUE("Technicien à risque");

	public String getValue() {
		return status;
	}

	private String status;

	private Status(String status) {
		this.status = status;
	}

}
