package rs.at.pm.connection;

public class ConnectorToFactory {

	public  ConnectorTo ConnectorTo(ConnectionArguments args, String type){
		switch (type.toLowerCase()) {
			case "mysql":
				return new ConnectorToMySQL(args);
		}
		return null;
	}
}
