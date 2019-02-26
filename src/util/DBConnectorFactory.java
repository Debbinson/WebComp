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
		DB_CONNECTION = "jdbc:mysql://rockstar.cwswjgvrnmwi.eu-west-2.rds.amazonaws.com:3306/rockstardatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		DB_USER = "rockstaruser";
		DB_PASSWORD = "WebComputing2019.";
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
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			throw new SQLException(e.getErrorCode() + " :: " + e.getMessage());
		}

		return dbConnection;
	}
}
