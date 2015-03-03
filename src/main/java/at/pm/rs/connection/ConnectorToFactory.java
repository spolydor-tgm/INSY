package at.pm.rs.connection;

import java.sql.SQLException;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */
public class ConnectorToFactory {

	/**
	 * Checking for the right Connector. Is Easy to expand the count of DBMS which can be connected
	 * The default Connector is an MySQL Connector (ConnectorToMySQL)!!!
	 * @param args Arguments from the Input of the start
	 * @param type Which Connector should be created
	 * @return ConnectorTo right Connector
	 */
	public ConnectorTo ConnectorTo(ConnectionArguments args, String type) throws SQLException, ClassNotFoundException {
		switch (type.toLowerCase()) {
			default: // "mysql":
				return new ConnectorToMySQL(args);
		}
	}
}
