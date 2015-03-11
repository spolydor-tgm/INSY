package at.pm.rs.connection;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */
public class ConnectorToMySQL implements ConnectorTo {

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
	 * Creates a new Connector for a MySQL DBMS
	 * 
	 * @param connectionArguments which has all informations for the connection to the dbms
	 * @throws java.sql.SQLException if the connection is not possible
	 * @throws java.lang.ClassNotFoundException if the jdbc driver will not be found
	 */
	public ConnectorToMySQL(ConnectionArguments connectionArguments) throws SQLException, ClassNotFoundException {
		data = connectionArguments;

		this.connect();
		md = conn.getMetaData();
	}

	/**
	 * Establishes a connection to the DBMS
	 * @throws java.sql.SQLException if the connection is not possible
	 * @throws java.lang.ClassNotFoundException if the jdbc driver will not be found
	 */
	public void connect() throws SQLException, ClassNotFoundException {
		// Treiber laden
		Class.forName("com.mysql.jdbc.Driver");

		// Verbindung mit dem DBMS herstellen
		String url = "jdbc:mysql://" + data.getHostname() + "/" + data.getDBName();
		conn = DriverManager.getConnection(url, data.getUsername(), data.getPwd());

		st = conn.createStatement();
	}

	/**
	 * Reads all the PK's from the given Table out and saves them into a
	 * SetOfData
	 * 
	 * @param tablename from which the Primary Keys, will be read out
	 * @throws java.sql.SQLException if there is a problem during reading the primary keys out
	 */
	private void readPk(String tablename) throws SQLException {
		//rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
		rs = md.getPrimaryKeys(null, null, tablename);
		while (rs.next()) {
			int x = 0;
			while (!(rs.getString(4).equals(tableDatas.get(tableInProcessNumber).getSetOfData().get(x).getName())))
				x++;
			tableDatas.get(tableInProcessNumber).getSetOfData().get(x).setPk(true);
		}
	}

	/**
	 * Reads all the FK's from the given Table out and saves them into a
	 * SetOfData
	 *
	 * @throws java.sql.SQLException if there is a problem during reading the foreign keys out
	 */
	private void readFk() throws SQLException {
		for (int xx = 0; xx < tablenames.size(); xx++) {
//			rs = md.getTables(null, null, tablenames.get(xx), new String[]{"TABLE"}); //TODO despite it was commented out it worked as well as with the line in it... I just wondered wh this one is right there
//			rs = md.getExportedKeys(null, null, tablenames.get(xx));
			rs = md.getImportedKeys(null, null, tablenames.get(xx));
			while (rs.next()) {
				for (int y = 0; y < tableDatas.get(xx).getSetOfData().size(); y++) { // keine Ahnung warum xx -1 bei unique funktionierts ohne ...
					if (tableDatas.get(xx).getSetOfData().get(y).getName().equals(rs.getString("FKCOLUMN_NAME"))) {
						tableDatas.get(xx).getSetOfData().get(y).setFk(new ForeignKey(rs.getString("PKTABLE_NAME"), rs.getString("PKCOLUMN_NAME")));
						break;
					}
				}
			}
		}
	}

	/**
	 * Reads all Extra Attributes (isNullabe, isAutoincrement, Columnname, Type)
	 * and saves them into a SetOfData
	 * 
	 * @throws java.sql.SQLException if there is a problem during reading the extra Attributes out
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
	 * Reads All Tablenames from the connected DB and saves them into an
	 * String-ArrayList
	 * 
	 * @throws java.sql.SQLException if there is a problem during reading all the tablenames out
	 */
	private void readAllTablenames() throws SQLException {
		rs = md.getTables(null, null, "%", null); // Get all Tables
		while (rs.next())
			tablenames.add(rs.getString(3));
		rs.close();
		rs = null; // Cleaning the resultSet rs. The stored informations wouldn't be needed anymore.
	}

	/**
	 * Reads All from All Tables
	 * 
	 * @return TableOfData which stores all Informations from all tables
	 * @throws java.sql.SQLException if there is a problem during reading all Informations out of a databse
	 */
	public ArrayList<TableData> readAllFromAllTables() throws SQLException {
		this.readAllTablenames();

		PreparedStatement st;
		for (tableInProcessNumber = 0; tableInProcessNumber < tablenames.size(); tableInProcessNumber++) {
			st = conn.prepareStatement(query + tablenames.get(tableInProcessNumber) + ";");
			rs = st.executeQuery();
			rsmd = st.getMetaData();
			columns = rsmd.getColumnCount();
			setOfTableData = new SetOfData(); // Create new SetOfData
			tableDatas.add(new TableData(tablenames.get(tableInProcessNumber))); // Set tablename

			this.readExtraAttributes();
			this.readPk(tablenames.get(tableInProcessNumber));
			// this.readFk(tablenames.get(tableInProcessNumber));
		}
		this.readFk();
		this.readUnique();

		return tableDatas;
	}

	/**
	 *
	 * @throws java.sql.SQLException if there is a problem during reading the unique state out
	 */
	private void readUnique() throws SQLException{
		for (int xx = 0; xx < tablenames.size(); xx++) {
			ResultSet rss = md.getIndexInfo(null, null, tablenames.get(xx), true, true);
			while (rss.next()) {
				for (int y = 0; y < tableDatas.get(xx).getSetOfData().size(); y++) {
					if (tableDatas.get(xx).getSetOfData().get(y).getName().equals(rss.getString("COLUMN_NAME"))) {
						tableDatas.get(xx).getSetOfData().get(y).setUnique(true);
						break;
					}
				}
			}
		}
	}

	/**
	 * Closes all Connections (Resultset and Connection)
	 * 
	 * @throws java.sql.SQLException if there is a problem for closing the connection
	 */
	public void closeConnections() throws SQLException {
		rs.close();
		conn.close();
	}
}
