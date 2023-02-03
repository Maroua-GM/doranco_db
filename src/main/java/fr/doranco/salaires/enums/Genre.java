package fr.doranco.salaires.enums;

public enum Genre {
MASCULIN("H"),
FEMININ("F");

private String Genre;

private Genre(String genre) {
	Genre = genre;
}

public String getValue() {
	return Genre;
}
	
}
