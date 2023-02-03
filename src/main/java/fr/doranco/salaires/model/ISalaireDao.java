package fr.doranco.salaires.model;

import java.util.List;
import java.util.Map;

import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.entity.User;

public interface ISalaireDao {

	Map<String, Float> getSalaireMinMax() throws Exception;

	Map<Integer, Float> getSalaireMoyenParAnnee() throws Exception;
	List<User> getUsersAvecSalaireBas(float seuilSalaire) throws Exception;

}
