package connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class Connection {

	private java.sql.Connection conn;

	private ResultSet resultSet;

	public Connection(String[] arguments) {

	}

	public ResultSet getResultSet() {
		return null;
	}

	public void connect(String hostname, String DBname, String username, String pwd) {
		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung mit dem DBMS herstellen
			String url = "jdbc:mysql://" + hostname + "/" + DBname;
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}

}
