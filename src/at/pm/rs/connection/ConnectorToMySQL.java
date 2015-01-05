package at.pm.rs.connection;

import at.pm.rs.utils.ArgumentParser;

import java.sql.*;

public class ConnectorToMySQL implements ConnectorTo{

	private ConnectionArguments data;
	private Connection conn;
	private Statement st;
	
	public ConnectorToMySQL(ConnectionArguments connectionArguments) {
		data = connectionArguments;
		this.connect();

		DatabaseMetaData md = null;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);

			// ResultSetMetaData rsmd = rs.getMetaData();

			// int columnsNumber = rsmd.getColumnCount();
			// System.out.println(columnsNumber);
			while (rs.next()) {
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void connect() {
		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung mit dem DBMS herstellen
			String url = "jdbc:mysql://" + data.getHostname() + "/" + data.getDBName();
			conn = DriverManager.getConnection(url, data.getUsername(), data.getPwd());

			st = conn.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ArgumentParser ap = new ArgumentParser();
		new ConnectorToMySQL(ap.parseArguments());
	}
}
