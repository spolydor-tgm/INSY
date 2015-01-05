package at.pm.rs.connection;

import java.sql.Connection;

public interface ConnectorTo {
	public Connection connect();
}
