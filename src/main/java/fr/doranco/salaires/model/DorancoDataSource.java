package fr.doranco.salaires.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class DorancoDataSource {
	public static Connection getConnexion() throws Exception {

		InputStream input = new FileInputStream("src/main/resources/application.properties");
		Properties prop = new Properties();
		prop.load(input);
		Connection connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("login"),
				prop.getProperty("password"));
		return connection;

	}

	private DorancoDataSource() {

	}

}
