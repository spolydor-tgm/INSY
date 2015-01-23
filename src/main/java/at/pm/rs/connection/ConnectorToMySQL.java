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
	private ArrayList<SetOfData> setOfData;
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
			ResultSet rs = md.getTables(null, null, "%", null); // Get all Tables





			// ResultSetMetaData rsmd = rs.getMetaData();

			// int columnsNumber = rsmd.getColumnCount();
			// System.out.println(columnsNumber);

			while (rs.next()) {
				System.out.println(rs.getString(3));
			}

			ArrayList columnNames = new ArrayList();
			ArrayList data = new ArrayList();


			PreparedStatement st = conn.prepareStatement("Select * from test;");
			ResultSet rss = st.executeQuery();
			ResultSetMetaData rsmd = st.getMetaData();
			int columns = rsmd.getColumnCount();


			//  Get column names
			for (int i = 1; i <= columns; i++) {
				columnNames.add(rsmd.getColumnName(i));
				System.out.println(columnNames.get(i - 1) + ": " + rsmd.getColumnTypeName(i));
				System.out.println(rsmd.isAutoIncrement(i)); // true wenn ja und falls wenn nicht
				System.out.println(rsmd.isNullable(i)); // Gibt 1 zurueck, wenn keine Nullwerte erlaubt sind
			}
			//  Get row data
			/*
			while (rss.next()) {
				ArrayList row = new ArrayList(columns);
				for (int i = 1; i <= columns; i++) {
					row.add( rss.getObject(i) );
				}
				data.add( row );
			}
			*/

			/*
			ArrayList colNames = new ArrayList();
			ResultSet pkfk = md.getPrimaryKeys(null, null, "test");
			ResultSetMetaData rssmd = pkfk.getMetaData();
			for (int i = 1; i <= rssmd.getColumnCount(); i++) {
				colNames.add(rssmd.getColumnName(i));
				System.out.println(colNames.get(i-1));
			}
			*/
			DatabaseMetaData meta=conn.getMetaData();
			rs= meta.getTables(null, null, "test", new String[]{"TABLE"});
			rs=meta.getPrimaryKeys(null, null, "test");
			while(rs.next())
				System.out.println("Primary Key :"+rs.getString(4));

			rs= meta.getTables(null, null, "test2", new String[]{"TABLE"});
			rs=meta.getExportedKeys(null, null, "test2");
			while(rs.next())
				System.out.println("Foreign Key :" + rs.getString(4));

			rs= meta.getTables(null, null, "table3", new String[]{"TABLE"});
			rs=meta.getPrimaryKeys(null, null, "table3");
			while(rs.next())
				System.out.println("Primary Key :" + rs.getString(4));

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
			while (!(rs.getString(4).equals(setOfTableData.getName().get(x))))
				x++;
			setOfTableData.setPk(true, x);
		}
	}

	private void readFk(String tablename) throws SQLException {
		rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
		rs = md.getExportedKeys(null, null, tablename);
		while(rs.next()) {
			int x = 0;
			while (!(rs.getString(4).equals(setOfTableData.getName().get(x))))
				x++;
			setOfTableData.setFk(rs.getString(4), x);
		}
	}

	private void readExtraAttributes() throws SQLException {
		for (int i = 1; i <= columns; i++) {
			setOfTableData.setName(rsmd.getColumnName(i));
			setOfTableData.setAutoincremet(rsmd.isAutoIncrement(i)); // true wenn ja und falss wenn nicht
			if (rsmd.isNullable(i) == 1) // returns 1, if there is no null-value allowed
				setOfTableData.setNotNull(true); //
			else
				setOfTableData.setNotNull(false);
			setOfTableData.setType(rsmd.getColumnTypeName(i));
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
			setOfTableData = new SetOfData();
			st = conn.prepareStatement(query + tablenames.get(x) + ";");
			rs = st.executeQuery();
			rsmd = st.getMetaData();
			columns = rsmd.getColumnCount();

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


	}
}
