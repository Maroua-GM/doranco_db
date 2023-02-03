package fr.doranco.salaires.main;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;

import fr.doranco.salaires.entity.Adresse;
import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.entity.User;
import fr.doranco.salaires.enums.Genre;
import fr.doranco.salaires.enums.Mois;
import fr.doranco.salaires.enums.Status;
import fr.doranco.salaires.enums.Ville;
import fr.doranco.salaires.model.AdresseDao;
import fr.doranco.salaires.model.DorancoDataSource;
import fr.doranco.salaires.model.UserDao;

public class Main {

	public static void main(String[] args) throws Exception {
		UserDao d = new UserDao();
//		AdresseDao a = new AdresseDao();
//		User user = new User("Victor", "Hugo", Genre.MASCULIN.getValue(), "011111111", LocalDate.now(), LocalDate.now(),
//				LocalDate.now(), "manager", 3000, "hugo@gmail.com", Status.MANAGER.getValue());
//		Adresse a1 = new Adresse("14", "paris", Ville.PARIS.getValue(), "78955");
//		Adresse a2 = new Adresse("88", "lyon", Ville.LYON.getValue(), "70000");
//		user.getAdresses().add(a1);
//		user.getAdresses().add(a2);
//		User resultalt = d.addUser(user);
//		System.out.println(resultalt);
//		System.out.println(a.getAdresses(resultalt.getId()));
//		System.out.println("---------------------------Users---------------------------------");
//		for (User user : d.getUsers()) {
//			System.out.println(user);
//			for (Adresse a : user.getAdresses()) {
//				
//				System.out.println(" =>"+a);
//			}
//			
//		}
//		Salaire salaire= new Salaire(2020,Mois.MAI.getValue(),1234f);
//		System.out.println(d.addSalaire(salaire, 12));
//		
	//	System.out.println("le salaire annuel de l'annee 2020 de l'utilisateur 12 est: "+d.getSalaireAnnuel(2020, 12));
		Map<Short,Map<String,Float>> salaires=d.getSalaires(12);
		for (Map.Entry<Short, Map<String,Float>> entry : salaires.entrySet()) {
			Short key = entry.getKey();
			System.out.println("-----------------------"+key+"------------------------");
			Map<String,Float> val = entry.getValue();
			System.out.println("salaires: "+val);
			
		}
	}

}
