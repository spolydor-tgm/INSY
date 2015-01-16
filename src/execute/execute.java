package execute;

import connection.Connection;

import java.sql.SQLException;

/**
 * @author Stefan Polydor
 * @version 14.01.15
 */
public class execute {
	public static void main(String[] args) {
		// PreparedStatement preparedStatement = new PreparedStatement("Select * from ?");
		String[] arg = new String[2];
		try {
			Connection con = new Connection(arg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}