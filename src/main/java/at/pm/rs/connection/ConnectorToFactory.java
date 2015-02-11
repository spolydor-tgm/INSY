package at.pm.rs.connection;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class ConnectorToFactory {

	public  ConnectorTo ConnectorTo(ConnectionArguments args, String type){
		switch (type.toLowerCase()) {
			case "mysql":
				return new ConnectorToMySQL(args);
		}
		return null;
	}
}
