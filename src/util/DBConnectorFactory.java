package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorFactory {

	private static DBConnectorFactory _instance;

	private String DB_DRIVER;
	private String DB_CONNECTION;
	private String DB_USER;
	private String DB_PASSWORD;

	private DBConnectorFactory() {
		DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		DB_CONNECTION = "jdbc:mysql://localhost:3306/commerce_and_music?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		DB_USER = "root";
		DB_PASSWORD = "Tavolaperiodica1.";
	}

	public static DBConnectorFactory getInstance() {
		if (_instance == null)
			_instance = new DBConnectorFactory();

		return _instance;
	}

	public Connection makeDBConnection() throws SQLException {
		Connection dbConnection = null;

		try {
			Class.forName(DB_DRIVER);
			System.out.println("MySQL Connector Driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR :: MySQL Connector not found");
			throw new SQLException(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			System.out.println("SQL Connection to database established !");
		} catch (SQLException e) {
			System.out.println("Connection to commerce_and_music database failed");
			throw new SQLException(e.getErrorCode() + " :: " + e.getMessage());
		}

		return dbConnection;
	}
}
