package connection;

import java.sql.*;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class Connection {

	private java.sql.Connection conn;

	private ResultSet resultSet;

	private int columns;

	private String outputType;

	private String trennzeichen;

	private String selectStatementString = "Select ";

	/**
	 * Creating the SELECT Statement and the connection to a MySQL DBMS
	 * @param arguments parsed Options from the cli.ArgumentParser
	 * @throws SQLException if there is a problem by connecting to the DBMS or by executing the SELECT Statement
	 */
	public Connection(String[] arguments) throws SQLException {
		this.connect(arguments[0], arguments[3], arguments[1], arguments[2]); // Establishing the connection to the DBMS

		// Creating the SELECT Statement
		selectStatementString += arguments[8] + " from ";

		selectStatementString += arguments[10] + " ";
		if (arguments[6] != null)
			selectStatementString += "where " + arguments[6] + " ";

		if (arguments[4] != null) {
			selectStatementString += "order by " + arguments[4] + " ";
			if (arguments[5] != null)
				selectStatementString += arguments[5] + " ";
		}
		selectStatementString += ";";

		outputType = arguments[9]; // Saving the OutputType
		trennzeichen = arguments[7]; // Saving the trennzeichen

		Statement statement = conn.createStatement();
		resultSet = statement.executeQuery(selectStatementString); // Executing the SELECT Statement
		if (arguments[8].equals("*")) {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			columns = rsmd.getColumnCount(); // Get ColumnCount for the Specified Table in the SELECT Statement
		} else // If there are only some Columns which will be selected
			columns = arguments[8].split(",").length;
	}

	/**
	 *
	 * @return ResultSet with the executed Query
	 */
	public ResultSet getResultSet() {
		return resultSet;
	}

	/**
	 *
	 * @return Columns from the table or which are selected from the Table
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 *
	 * @return Trennzeichen to seperate the columns values in a row
	 */
	public String getTrennzeichen() {
		return trennzeichen;
	}

	/**
	 *
	 * @return OutputType null if it will be printed to the console or a String which represents the filename
	 */
	public String getOutputType() {
		if (outputType == null)
			return null;
		return outputType;
	}

	/**
	 * Closing all open Connections
	 */
	public void	closeConnection() {
		try {
			resultSet.close(); // Closing the ResultSet
			conn.close(); // Closing the Connectnion
		} catch (SQLException e) { // If there is an error, shutdown the whole program
			System.exit(0);
		}
	}

	/**
	 * Connect to a MySQL DBMS
	 * @param hostname ip (URI) of the DBMS
	 * @param DBname Database name
	 * @param username a username of an user in the DBMS
	 * @param pwd password for the user
	 */
	public void connect(String hostname, String DBname, String username, String pwd) {
		try {
			// Loading the Driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establish the connection to the DBMS
			String url = "jdbc:mysql://" + hostname + "/" + DBname;
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
			System.exit(1);
		} catch (SQLException e) {
			System.err.println("Wrong hostname or DBname or username or pwd!!!");
			System.exit(1);
		}
	}

}