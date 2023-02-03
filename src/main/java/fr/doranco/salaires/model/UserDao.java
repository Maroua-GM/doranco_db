package fr.doranco.salaires.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.doranco.salaires.entity.Adresse;
import fr.doranco.salaires.entity.Salaire;
import fr.doranco.salaires.entity.User;
import fr.doranco.salaires.enums.Mois;

public class UserDao implements IUserDao {

	@Override
	public User getUserById(int id) throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		if (id <= 0) {
			throw new IllegalArgumentException("l'id du user a rechercher doit etre > 0");
		}
		Connection conn = null;
		try {

			conn = DorancoDataSource.getConnexion();
			String requete = "SELECT * FROM user WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(requete);
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			User user = null;
			if (rs != null && rs.next()) {
				user = new User();
				user.setId(id);
				user.setTelephone(rs.getString("telephone"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setGenre(rs.getString("genre"));
				user.setDataNaissance(LocalDate.parse(rs.getString("date_naissance"), formatter));
				user.setDateEntree(LocalDate.parse(rs.getString("date_entree"), formatter));
				// TODO VERIFIER LE RS EST DIFFERENT DE NULL
				user.setDateSortie(LocalDate.parse(rs.getString("date_sortie"), formatter));
				user.setTitre(rs.getString("titre"));
				user.setSalaireDeBase(rs.getFloat("salaire_base"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getString("status"));

			}
			// recuperer les adresses de l'utilisateur
			AdresseDao DaoAdresse = new AdresseDao();
			List<Adresse> liste = DaoAdresse.getAdresses(id);
			for (Adresse adresse : liste) {
				user.getAdresses().add(adresse);
			}
			return user;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

		}

	}

	@Override
	public User addUser(User user) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		if (user == null) {
			throw new NullPointerException("le user à creer ne doit pas etre NULL");
		}
		// TODO verifier uniquement les paramaetres qui sont obligatoires
		if (user.getGenre() == null || user.getGenre().trim().isEmpty() || user.getNom() == null
				|| user.getNom().trim().isEmpty() || user.getPrenom() == null || user.getPrenom().trim().isEmpty()
				|| user.getDataNaissance() == null) {
			throw new IllegalArgumentException("un ou plusieurs parametres sont manquants");
		}
		Connection conn = null;
		try {
			conn = DorancoDataSource.getConnexion();
			String requete = "INSERT INTO `user` (`nom`, `prenom`, `telephone`, `genre`, `date_naissance`, `date_entree`, `date_sortie`, `titre`, `salaire_base`, `email`, `status`)   VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getNom());
			ps.setString(2, user.getPrenom());
			ps.setString(3, user.getTelephone());
			ps.setString(4, user.getGenre());
			ps.setString(5, user.getDataNaissance().format(formatter));
			ps.setString(6, user.getDateEntree().format(formatter));
			ps.setString(7, user.getDateSortie().format(formatter));
			ps.setString(8, user.getTitre());
			ps.setFloat(9, user.getSalaireDeBase());
			ps.setString(10, user.getEmail());
			ps.setString(11, user.getStatus());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				user.setId(rs.getInt(1));
			}
			List<Adresse> adresses = user.getAdresses();
			if (adresses != null && !adresses.isEmpty()) {
				AdresseDao DaoAdresse = new AdresseDao();
				for (Adresse adresse : adresses) {
					DaoAdresse.addAdresse(adresse, user.getId());
				}
			}

			return user;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUsers() throws Exception {
		AdresseDao DaoAdresse = new AdresseDao();
		List<Adresse> listeAdresses = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		List<User> liste = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DorancoDataSource.getConnexion();
			String requete = "SELECT * FROM user";
			ps = conn.prepareStatement(requete);
			rs = ps.executeQuery();
			if (rs != null) {
				User user = null;
				while (rs.next()) {
					user = new User();
					listeAdresses = new ArrayList<Adresse>();
					user.setId(rs.getInt("id"));
					user.setTelephone(rs.getString("telephone"));
					user.setNom(rs.getString("nom"));
					user.setPrenom(rs.getString("prenom"));
					user.setGenre(rs.getString("genre"));
					if (rs.getString("date_naissance") != null) {
						user.setDataNaissance(LocalDate.parse(rs.getString("date_naissance"), formatter));
					}
					if (rs.getString("date_entree") != null) {
						user.setDateEntree(LocalDate.parse(rs.getString("date_entree"), formatter));
					}
					if (rs.getString("date_sortie") != null) {
						user.setDateSortie(LocalDate.parse(rs.getString("date_sortie"), formatter));
					}
					user.setTitre(rs.getString("titre"));
					user.setSalaireDeBase(rs.getFloat("salaire_base"));
					user.setEmail(rs.getString("email"));
					user.setStatus(rs.getString("status"));
					// recuperer la liste des adresses d'un utilisateur
					listeAdresses = DaoAdresse.getAdresses(user.getId());
					for (Adresse adresse : listeAdresses) {
						user.getAdresses().add(adresse);
					}
					liste.add(user);
				}
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
		return liste;
	}

	@Override
	public float getSalaire(short annee, String mois, int idUser) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Short, Map<String, Float>> getSalaires(int idUser) throws Exception {
		Map<Short,Map<String,Float>> salaires=new HashMap<Short,Map<String,Float>>();
		Map<String,Float> salaire=null;
		if ( idUser < 0) {
			throw new IllegalArgumentException("on accepte pas une valeur négative");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DorancoDataSource.getConnexion();
			String requete = "SELECT * FROM SALAIRE WHERE user_id=?";
			ps = conn.prepareStatement(requete);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					salaire=new HashMap<String,Float>();
					salaire.put(Mois.JANVIER.getValue(), rs.getFloat(Mois.JANVIER.getValue()));
					salaire.put(Mois.FEVRIER.getValue(), rs.getFloat(Mois.FEVRIER.getValue()));
					salaire.put(Mois.MARS.getValue(), rs.getFloat(Mois.MARS.getValue()));
					salaire.put(Mois.AVRIL.getValue(), rs.getFloat(Mois.AVRIL.getValue()));
					salaire.put(Mois.MAI.getValue(), rs.getFloat(Mois.MAI.getValue()));
					salaire.put(Mois.JUIN.getValue(), rs.getFloat(Mois.JUIN.getValue()));
					salaire.put(Mois.JUILLET.getValue(), rs.getFloat(Mois.JUILLET.getValue()));
					salaire.put(Mois.AOUT.getValue(), rs.getFloat(Mois.AOUT.getValue()));
					salaire.put(Mois.SEPTEMBRE.getValue(), rs.getFloat(Mois.SEPTEMBRE.getValue()));
					salaire.put(Mois.OCTOBRE.getValue(), rs.getFloat(Mois.OCTOBRE.getValue()));
					salaire.put(Mois.NOVEMBRE.getValue(), rs.getFloat(Mois.NOVEMBRE.getValue()));
					salaire.put(Mois.DECEMBRE.getValue(), rs.getFloat(Mois.DECEMBRE.getValue()));
					salaires.put(rs.getShort("annee"), salaire);
				}
				
				return salaires;
			}
			
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
		return null;
	}

	@Override
	public int addSalaire(Salaire salaire, int idUser) throws Exception {
		if (salaire.getSalaire() < 0 || idUser < 0) {
			throw new IllegalArgumentException("on accepte pas une valeur négative");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psUpdateToinsert = null;
		try {
			conn = DorancoDataSource.getConnexion();
			String requete1 = "SELECT * FROM salaire WHERE (user_id=? AND annee=?)";
			ps = conn.prepareStatement(requete1);
			ps.setInt(1, idUser);
			ps.setInt(2, salaire.getAnnee());
			rs = ps.executeQuery();

			if (!rs.next()) {
				ps.close();
				rs.close();
				StringBuilder sb = new StringBuilder();
				sb.append("INSERT INTO `salaire` (`ANNEE`,");
				sb.append(salaire.getMois());
				sb.append(", `user_id`) VALUES (?, ?, ?)");
				ps = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, salaire.getAnnee());
				ps.setFloat(2, salaire.getSalaire());
				ps.setInt(3, idUser);
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				rs.next();
				return rs.getInt(1);

			} else {
				// verifier si le salaire qu'on va le rajouter existe deja
				// s'il existe je peux pas l'ecraser
				// s'il n'existe pas je le rajoute
				if (rs.getFloat(salaire.getMois()) != 0) {
					throw new Exception("Impossible d'ajouter le salaire pour le mois de " + salaire.getMois()
							+ " car il existe déja");
				}

				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE `salaire` SET ");
				sb.append(salaire.getMois());
				sb.append(" = ? ");
				sb.append(" WHERE `user_id` = ?");
				psUpdateToinsert = conn.prepareStatement(sb.toString());
				psUpdateToinsert.setFloat(1, salaire.getSalaire());
				psUpdateToinsert.setInt(2, idUser);
				psUpdateToinsert.executeUpdate();
				return rs.getInt(1);
				// return 0;
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}

	}

	@Override
	public float getSalaireAnnuel(Integer annee, int idUser) throws Exception {
		if (annee < 0 || idUser < 0) {
			throw new IllegalArgumentException("on accepte pas une valeur négative");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DorancoDataSource.getConnexion();
			String requete = " select JANVIER+FEVRIER+MARS+AVRIL+MAI+JUIN+JUILLET+AOUT+SEPTEMBRE+OCTOBRE+NOVEMBRE+DECEMBRE AS SalaireAnnuel from salaire where user_id=? and annee=?";
			ps = conn.prepareStatement(requete);
			ps.setInt(1, idUser);
			ps.setInt(2, annee);
			rs = ps.executeQuery();
			rs.next();
			return rs.getFloat(1);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	
	}

}
