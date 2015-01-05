package at.pm.rs.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorToMySQL implements ConnectorTo{

	private ConnectionArguments data;
	private Connection conn;
	private Statement st;
	
	public ConnectorToMySQL() {
		this.connect();

		DatabaseMetaData md = null;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
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
			String url = "jdbc:mysql://localhost";
			conn = DriverManager.getConnection(url, "insy5", "1234");
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM A05.test");
			if(res.next()){
				System.out.println(res.getInt(3));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ConnectorToMySQL();
	}
}
