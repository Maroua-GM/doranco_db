package fr.doranco.salaires.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.salaires.entity.Adresse;

public class AdresseDao implements IAdresseDao {

	@Override
	public List<Adresse> getAdresses(int idUser) throws Exception {
		List<Adresse> liste=new ArrayList<Adresse>();
		if (idUser <= 0) {
			throw new IllegalArgumentException("l'id du user a rechercher doit etre > 0");
		}
		Connection conn = null;
		try {
			conn = DorancoDataSource.getConnexion();
			String requete = "SELECT * FROM adresse WHERE employee_id=?";
			PreparedStatement ps = conn.prepareStatement(requete);
			ps.setInt(1, idUser);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			if(rs!=null) {
				Adresse adresse=null;
				while(rs.next()) {
					adresse=new Adresse();
					adresse.setId(rs.getInt("id"));
					adresse.setNumero(rs.getString("numero"));
					adresse.setRue(rs.getString("rue"));
					adresse.setVille(rs.getString("ville"));
					adresse.setCodePostal(rs.getString("code_postal"));
					liste.add(adresse);
				}
			}
			
			return liste;	
		}finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		
		
	}

	@Override
	public Adresse addAdresse(Adresse adresse, int idUser) throws Exception {
		if (adresse == null) {
			throw new NullPointerException("le user à creer ne doit pas etre NULL");
		}
		Connection conn = null;
		try {
			conn = DorancoDataSource.getConnexion();
			String requete="INSERT INTO `adresse` (`numero`, `rue`, `ville`, `code_postal`, `employee_id`) VALUES (? ,?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, adresse.getNumero());
			ps.setString(2, adresse.getRue());
			ps.setString(3, adresse.getVille());
			ps.setString(4, adresse.getCodePostal());
			ps.setInt(5, idUser);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				adresse.setId(rs.getInt(1));
			}
		}finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return adresse;
	}

}
