package fr.doranco.salaires.model;

import java.util.List;

import fr.doranco.salaires.entity.Adresse;

public interface IAdresseDao {
	
	List<Adresse> getAdresses(int idUser) throws Exception;
	Adresse addAdresse(Adresse adresse,int idUser) throws Exception;
	

}
