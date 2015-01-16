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

	private String user = System.getProperty("user.name");

	private String pwd = null;

	private String dbms = "localhost";

	private String selectStatementString = "Select ";

	public Connection(String[] arguments) throws SQLException {
		if (arguments[0] != null)
			dbms = arguments[0];
		if (arguments[1] != null)
			user = arguments[1];
		if (arguments[2] != null)
			pwd	= arguments[3];

		this.connect(dbms, arguments[4], user, pwd);

		if (arguments[8].equals(".classpath"))
			selectStatementString += "* from ";
		else
			selectStatementString += arguments[8] + "from ";

		selectStatementString += arguments[10] + " ";
		if (arguments[6] != null)
			selectStatementString += "where" + arguments[6] + " ";

		if (arguments[4] != null) {
			selectStatementString += "order by " + arguments[4] + " ";
			if (arguments[5] != null)
				selectStatementString += "desc ";
		}
		selectStatementString += ";";
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void	closeConnection() {
		try {
			conn.close();
			resultSet.close();
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
