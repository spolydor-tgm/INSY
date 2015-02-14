package at.pm.rs.connection;

import at.pm.rs.graphics.FileWriter;
import at.pm.rs.graphics.TextWriter;
import at.pm.rs.utils.ArgumentParser;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */
public class ConnectorToMySQL implements ConnectorTo{

	private ConnectionArguments data;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private DatabaseMetaData md;
	private ArrayList<TableData> tableDatas = new ArrayList<>(); // Saving the datas from the existing tables
	private ArrayList<String> tablenames = new ArrayList<>(); // Saving all tablenames
	private final String query = "SELECT * from "; // Selects all from a given table
	private int columns; // Columns of the table
	private int tableInProcessNumber; // The number of the table which is in progess
	private SetOfData setOfTableData; // SetOfData which stores the Information from a Table (only one attribute + extra information)

	/**
	 *
	 * @param connectionArguments
	 */
	public ConnectorToMySQL(ConnectionArguments connectionArguments) {
		data = connectionArguments;
		this.connect();

		try {
			md = conn.getMetaData();

		} catch (SQLException e) {
			System.err.println("Failed to connect to the DBMS!!!");
			System.err.println("Wrong username/password/dmbs-Adress");
			System.exit(1);
		}
	}

	/**
	 * Establish a connection to the DBMS
	 */
	@Override
	public void connect(){
		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung mit dem DBMS herstellen
			String url = "jdbc:mysql://" + data.getHostname() + "/" + data.getDBName();
			conn = DriverManager.getConnection(url, data.getUsername(), data.getPwd());

			st = conn.createStatement(); // auslagern !!!!

		} catch (ClassNotFoundException e) {
			System.out.println("Unnable to load the class");
			System.exit(1);
		} catch (SQLException e) {
			System.err.println("Unable to connect to the Database Management System");
			System.exit(1);
		}
	}

	/**
	 *
	 * @param tablename
	 * @throws SQLException
	 */
	private void readPk(String tablename) throws SQLException {
		rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
		rs = md.getPrimaryKeys(null, null, tablename);
		while(rs.next()) {
			int x = 0;
			while (!(rs.getString(4).equals(tableDatas.get(tableInProcessNumber).getSetOfData().get(x).getName())))
				x++;
			tableDatas.get(tableInProcessNumber).getSetOfData().get(x).setPk(true);
		}
	}

	/**
	 *
	 * @param tablename
	 * @throws SQLException
	 */
	private void readFk(String tablename) throws SQLException {
		rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
		rs = md.getExportedKeys(null, null, tablename);
		while(rs.next()) {
			int x = 0;
			while (!(rs.getString("FKCOLUMN_NAME").equals(tableDatas.get(tableInProcessNumber).getSetOfData().get(x).getName())))
				x++;
			tableDatas.get(tableInProcessNumber).getSetOfData().get(x).setFk(rs.getString("FKTABLE_NAME") + "." + rs.getString("FKCOLUMN_NAME"));
		}
	}

	/**
	 *
	 * @throws SQLException
	 */
	private void readExtraAttributes() throws SQLException {
		for (int i = 1; i <= columns; i++) {
			setOfTableData = new SetOfData();
			setOfTableData.setName(rsmd.getColumnName(i));
			setOfTableData.setAutoincrement(rsmd.isAutoIncrement(i)); // true wenn ja und false wenn nicht
			if (rsmd.isNullable(i) == 1) // returns 1, if there is no null-value allowed
				setOfTableData.setIsNullable(true);
			setOfTableData.setType(rsmd.getColumnTypeName(i));

			tableDatas.get(tableInProcessNumber).addSetOfData(setOfTableData);
		}
	}

	/**
	 *
	 * @throws SQLException
	 */
	private void readAllTablenames() throws SQLException {
		rs = md.getTables(null, null, "%", null); // Get all Tables
		while (rs.next())
			tablenames.add(rs.getString(3));
		rs.close();
		rs = null; // Cleaning the resultSet rs. The stored informations wouldn't be needed anymore.
	}

	/**
	 *
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<TableData> readAllFromAllTables() throws SQLException {
		this.readAllTablenames();

		PreparedStatement st;
		for (tableInProcessNumber = 0; tableInProcessNumber < tablenames.size(); tableInProcessNumber++) {
			st = conn.prepareStatement(query + tablenames.get(tableInProcessNumber) + ";");
			rs = st.executeQuery();
			rsmd = st.getMetaData();
			columns = rsmd.getColumnCount();
			setOfTableData = new SetOfData();
			tableDatas.add(new TableData(tablenames.get(tableInProcessNumber)));
			// setOfTableData.setTableName(tablenames.get(x));

			this.readExtraAttributes();
			this.readPk(tablenames.get(tableInProcessNumber));
			this.readFk(tablenames.get(tableInProcessNumber));

			// tableDatas.get(tableInProcessNumber).addSetOfData(setOfTableData);
			// setOfData.add(setOfTableData);
		}
		return tableDatas;
	}

	/**
	 *
	 * @throws SQLException
	 */
	public void closeConnections() throws SQLException {
		rs.close();
		conn.close();
	}

	public static void main(String[] args) {
		ArgumentParser ap = new ArgumentParser();
		//new ConnectorToMySQL(ap.parseArguments(args));
		ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL(ap.parseArguments(args));
		try {
			ArrayList<TableData> test = connectorToMySQL.readAllFromAllTables();
			for (TableData data : test) {
				System.out.print(data.getTableName());
				for (SetOfData setOfData : data.getSetOfData())
				System.out.print(setOfData.toString());

				System.out.println("" + '\n');
			}

			FileWriter w = new TextWriter();
			w.setOutputDir("src");
			TableData[] te = new TableData[test.size()];
			for (int x = 0; x < te.length; x++)
				te[x] = test.get(x);
			w.print(te);
			connectorToMySQL.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
