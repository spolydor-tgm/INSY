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

	public Connection(String[] arguments) throws SQLException {
		this.connect(arguments[0], arguments[3], arguments[1], arguments[2]);

		/*
		if (arguments[8].equals(".classpath"))
			selectStatementString += "* from ";
		else
		*/
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

		PreparedStatement preparedStatement = conn.prepareStatement(selectStatementString);
		System.out.println(preparedStatement.toString() + '\n'); // Testausgabe
		resultSet = preparedStatement.executeQuery();
		ResultSetMetaData rsmd = preparedStatement.getMetaData();
		columns = rsmd.getColumnCount();
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public int getColumns() {
		return columns;
	}

	public String getTrennzeichen() {
		return trennzeichen;
	}

	public String getOutputType() {
		if (outputType == null)
			return null;
		return outputType;
	}

	public void	closeConnection() {
		try {
			resultSet.close();
			conn.close();
		} catch (SQLException e) {
			System.exit(0);
		}
	}

	public void connect(String hostname, String DBname, String username, String pwd) {
		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung mit dem DBMS herstellen
			String url = "jdbc:mysql://" + hostname + "/" + DBname;
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
			System.exit(0);
		} catch (SQLException e) {
			System.err.println("Wrong hostname or DBname or username or pwd!!!");
			System.exit(0);
		}
	}

}