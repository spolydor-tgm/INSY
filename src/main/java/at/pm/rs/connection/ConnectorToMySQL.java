package at.pm.rs.connection;

import java.sql.*;
import java.util.ArrayList;

public class ConnectorToMySQL implements ConnectorTo{

	private ConnectionArguments data;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private DatabaseMetaData md;
	private ArrayList<SetOfData> setOfData = new ArrayList<>();
	private ArrayList<String> tablenames = new ArrayList<>();
	private final String query = "SELECT * from ";
	private int columns;
	private SetOfData setOfTableData;

	
	//public ConnectorToMySQL(ConnectionArguments connectionArguments) {
	public ConnectorToMySQL() {
		// data = connectionArguments;
		this.connect();

		try {
			md = conn.getMetaData();

		} catch (SQLException e) {
			System.err.println("Failed to connect to the DBMS!!!");
			System.err.println("Wrong username/password/dmbs-Adress");
			System.exit(1);
		}
	}
	
	@Override
	public void connect() {
		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung mit dem DBMS herstellen
			//String url = "jdbc:mysql://" + data.getHostname() + "/" + data.getDBName();
			//conn = DriverManager.getConnection(url, data.getUsername(), data.getPwd());

			// Wieder loeschen
			conn = DriverManager.getConnection("jdbc:mysql://localhost/A05", "root", "polydor");

			st = conn.createStatement(); // auslagern !!!!

		} catch (ClassNotFoundException e) {
			System.out.println("Unnable to load the class");
			System.exit(0);
		} catch (SQLException e) {
			System.err.println("Unable to connect to the Database Management System");
			System.exit(0);
		}
	}

	private void readPk(String tablename) throws SQLException {
		rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
		rs = md.getPrimaryKeys(null, null, tablename);
		while(rs.next()) {
			int x = 0;
			while (!(rs.getString(4).equals(setOfTableData.getName()[x])))
				x++;
			setOfTableData.setPk(true, x);
		}
	}

	private void readFk(String tablename) throws SQLException {
		rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
		rs = md.getExportedKeys(null, null, tablename);
		while(rs.next()) {
			int x = 0;
			while (!(rs.getString(4).equals(setOfTableData.getName()[x])))
				x++;
			setOfTableData.setFk(rs.getString(4), x);
		}
	}

	private void readExtraAttributes() throws SQLException {
		for (int i = 1; i <= columns; i++) {
			setOfTableData.setName(rsmd.getColumnName(i), i-1);
			setOfTableData.setAutoincrement(rsmd.isAutoIncrement(i), i-1); // true wenn ja und falss wenn nicht
			if (rsmd.isNullable(i) == 0) // returns 1, if there is no null-value allowed
				setOfTableData.setNotNull(true, i-1); //
			else
				setOfTableData.setNotNull(false, i-1);
			setOfTableData.setType(rsmd.getColumnTypeName(i), i-1);
		}
	}

	private void readAllTablenames() throws SQLException {
		rs = md.getTables(null, null, "%", null); // Get all Tables
		while (rs.next())
			tablenames.add(rs.getString(3));
		rs.close();
		rs = null; // Cleaning the resultSet rs. The stored informations wouldn't be needed anymore.
	}

	public ArrayList<SetOfData> readAllFromAllTables() throws SQLException {
		this.readAllTablenames();
		PreparedStatement st;
		for (int x = 0; x < tablenames.size(); x++) {
			st = conn.prepareStatement(query + tablenames.get(x) + ";");
			rs = st.executeQuery();
			rsmd = st.getMetaData();
			columns = rsmd.getColumnCount();
			setOfTableData = new SetOfData(columns);
			setOfTableData.setTableName(tablenames.get(x));

			this.readExtraAttributes();
			this.readPk(tablenames.get(x));
			this.readFk(tablenames.get(x));

			setOfData.add(setOfTableData);
		}
		return setOfData;
	}

	public ResultSet getResultSet() {
		return rs;
	}

	public void closeConnections() throws SQLException {
		rs.close();
		conn.close();
	}

	public static void main(String[] args) {
		//ArgumentParser ap = new ArgumentParser();
		//new ConnectorToMySQL(ap.parseArguments(args));
		ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL();
		try {
			connectorToMySQL.readAllTablenames();
			ArrayList<SetOfData> test = connectorToMySQL.readAllFromAllTables();
			System.out.println(test.size());
			for (SetOfData data : test)
				System.out.println(data.toString());
			connectorToMySQL.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
