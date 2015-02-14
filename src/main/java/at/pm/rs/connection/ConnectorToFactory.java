package at.pm.rs.connection;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */
public class ConnectorToFactory {

	/**
	 * Checking for the right Connector. Is Easy to expand the count of DBMS which can be connected
	 * @param args Arguments from the Input of the start
	 * @param type Which Connector should be created
	 * @return ConnectorTo right Connector
	 */
	public  ConnectorTo ConnectorTo(ConnectionArguments args, String type){
		switch (type.toLowerCase()) {
			case "mysql":
				return new ConnectorToMySQL(args);
		}
		return null;
	}
}
