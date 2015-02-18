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
	private ArrayList<TableData> tableDatas = new ArrayList<>(); // Saving the
																	// datas
																	// from the
																	// existing
																	// tables
	private ArrayList<String> tablenames = new ArrayList<>(); // Saving all
																// tablenames
	private final String query = "SELECT * from "; // Selects all from a given
													// table
	private int columns; // Columns of the table
	private int tableInProcessNumber; // The number of the table which is in
										// progess
	private SetOfData setOfTableData; // SetOfData which stores the Information
										// from a Table (only one attribute +
										// extra information)

	/**
	 * Creates a new Connector for a MySQL DBMS
	 * 
	 * @param connectionArguments
	 */
	public ConnectorToMySQL(ConnectionArguments connectionArguments) throws SQLException, ClassNotFoundException {
		data = connectionArguments;

		this.connect();
		md = conn.getMetaData();
		/*
		 * System.err.println("Failed to connect to the DBMS!!!");
		 * System.err.println("Wrong username/password/dmbs-Adress");
		 * System.exit(1);
		 * 
		 * 
		 * System.out.println("Unnable to load the class");
		 */
	}

	/**
	 * Establishes a connection to the DBMS
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
	 * @param tablename
	 *            from which the Primary Keys, will be read out
	 * @throws SQLException
	 */
	private void readPk(String tablename) throws SQLException {
		rs = md.getTables(null, null, tablename, new String[]{"TABLE"});
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
	 * @param tablename
	 *            from which the Foreign Keys, will be read out
	 * @throws SQLException
	 */
	private void readFk(String tablename) throws SQLException {
		for (int xx = 0; xx < tablenames.size(); xx++) {
			rs = md.getTables(null, null, tablenames.get(xx), new String[]{"TABLE"});
			rs = md.getExportedKeys(null, null, tablenames.get(xx));
			int x;
			while (rs.next()) {
				x = 0;
				while (x < tableDatas.size()) {
					TableData tableData = tableDatas.get(x);
					if (tableData.getTableName().equals(rs.getString("FKTABLE_NAME"))) {
						for (int y = 0; y < tableData.getSetOfData().size(); y++) {
							if (tableData.getSetOfData().get(y).getName().equals(rs.getString("FKCOLUMN_NAME"))) {
								// tableData.getSetOfData().get(y).setFk(rs.getString("FKTABLE_NAME")
								// + "." + rs.getString("FKCOLUMN_NAME"));
//								System.out.println(tablenames.get(xx)+" : "+rs.getString("FKCOLUMN_NAME"));
								tableData.getSetOfData().get(y).setFk(new ForeignKey(tablenames.get(xx), rs.getString("FKCOLUMN_NAME")));
								break;
							}
						}
					}
					x++;
				}
			}
		}

		/*
		 * rs = md.getTables(null, null, tablename, new String[]{"TABLE"}); rs =
		 * md.getExportedKeys(null, null, tablename); while(rs.next()) { int x =
		 * 0; while (!(rs.getString("FKCOLUMN_NAME").equals(tableDatas.get(
		 * tableInProcessNumber).getSetOfData().get(x).getName()))) x++;
		 * tableDatas
		 * .get(tableInProcessNumber).getSetOfData().get(x).setFk(rs.getString
		 * ("FKTABLE_NAME") + "." + rs.getString("FKCOLUMN_NAME")); }
		 */
	}

	/**
	 * Reads all Extra Attributes (isNullabe, isAutoincrement, Columnname, Type)
	 * and saves them into a SetOfData
	 * 
	 * @throws SQLException
	 */
	private void readExtraAttributes() throws SQLException {
		for (int i = 1; i <= columns; i++) {
			setOfTableData = new SetOfData();
			setOfTableData.setName(rsmd.getColumnName(i));
			setOfTableData.setAutoincrement(rsmd.isAutoIncrement(i)); // true
																		// wenn
																		// ja
																		// und
																		// false
																		// wenn
																		// nicht
			if (rsmd.isNullable(i) == 1) // returns 1, if there is no null-value
											// allowed
				setOfTableData.setIsNullable(true);
			setOfTableData.setType(rsmd.getColumnTypeName(i));

			tableDatas.get(tableInProcessNumber).addSetOfData(setOfTableData);
		}
	}

	/**
	 * Reads All Tablenames from the connected DB and saves them into an
	 * String-ArrayList
	 * 
	 * @throws SQLException
	 */
	private void readAllTablenames() throws SQLException {
		rs = md.getTables(null, null, "%", null); // Get all Tables
		while (rs.next())
			tablenames.add(rs.getString(3));
		rs.close();
		rs = null; // Cleaning the resultSet rs. The stored informations
					// wouldn't be needed anymore.
	}

	/**
	 * Reads All from All Tables
	 * 
	 * @return TableOfData which stores all Informations from all tables
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
			setOfTableData = new SetOfData(); // Create new SetOfData
			tableDatas.add(new TableData(tablenames.get(tableInProcessNumber))); // Set
																					// tablename

			this.readExtraAttributes();
			this.readPk(tablenames.get(tableInProcessNumber));
			// this.readFk(tablenames.get(tableInProcessNumber));
		}
		this.readFk(""); //
		return tableDatas;
	}

	/**
	 * Closes all Connections (Resultset and Connection)
	 * 
	 * @throws SQLException
	 */
	public void closeConnections() throws SQLException {
		rs.close();
		conn.close();
	}
}
