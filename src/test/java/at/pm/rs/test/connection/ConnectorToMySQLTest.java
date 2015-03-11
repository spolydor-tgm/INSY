package at.pm.rs.test.connection;

import at.pm.rs.connection.ConnectionArguments;
import at.pm.rs.connection.ConnectorToFactory;
import at.pm.rs.connection.ConnectorToMySQL;
import org.junit.Test;

import java.sql.SQLException;

public class ConnectorToMySQLTest {

	@Test (expected = SQLException.class)
	public void testConnect() throws Exception {
		ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL(new ConnectionArguments("local", "master", "sicha_nicht", "haha", "mysql"));
		connectorToMySQL.connect();
	}

	@Test (expected = SQLException.class)
	public void testReadAllFromAllTables() throws Exception {
		ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL(new ConnectionArguments("local", "master", "sicha_nicht", "haha", "mysql"));
		connectorToMySQL.readAllFromAllTables();
	}

	@Test (expected = SQLException.class)
	public void testCloseConnections() throws Exception {
		ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL(new ConnectionArguments("local", "master", "sicha_nicht", "haha", "mysql"));
		connectorToMySQL.closeConnections();
	}

	@Test (expected = SQLException.class)
	public void testConnectorToFactory() throws Exception {
		ConnectorToFactory connectorToFactory = new ConnectorToFactory();
		connectorToFactory.ConnectorTo(new ConnectionArguments("local", "master", "sicha_nicht", "haha", "mysql"), "mysql");
	}
}