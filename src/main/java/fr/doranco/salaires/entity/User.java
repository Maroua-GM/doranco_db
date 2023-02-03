package fr.doranco.salaires.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.salaires.model.IUserDao;

public class User {

	private int id;
	private String nom;
	private String prenom;
	private String genre;

	private String telephone;
	private LocalDate dataNaissance;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	private String titre;
	private float salaireDeBase;
	private String email;
	private String status;
	private List<Adresse> adresses;

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public User() {
		adresses = new ArrayList<Adresse>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public User(String nom, String prenom, String genre, String telephone, LocalDate dataNaissance,
			LocalDate dateEntree, LocalDate dateSortie, String titre, float salaireDeBase, String email,
			String status) {

		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.telephone = telephone;
		this.dataNaissance = dataNaissance;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.titre = titre;
		this.salaireDeBase = salaireDeBase;

		this.email = email;
		this.status = status;
		adresses = new ArrayList<Adresse>();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getDataNaissance() {
		return dataNaissance;
	}

	public void setDataNaissance(LocalDate dataNaissance) {
		this.dataNaissance = dataNaissance;
	}

	public LocalDate getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}

	public LocalDate getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public float getSalaireDeBase() {
		return salaireDeBase;
	}

	public void setSalaireDeBase(float salaireDeBase) {
		this.salaireDeBase = salaireDeBase;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", genre=" + genre + ", telephone=" + telephone + ", dataNaissance=" + dataNaissance
				+ ", dateEntree=" + dateEntree + ", dateSortie=" + dateSortie + ", titre=" + titre + ", salaireDeBase="
				+ salaireDeBase + ", email=" + email + ", status=" + status + "]";
	}

}
