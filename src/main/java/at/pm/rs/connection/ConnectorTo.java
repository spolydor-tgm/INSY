package at.pm.rs.connection;

import java.sql.SQLException;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public interface ConnectorTo {
	public void connect() throws SQLException, ClassNotFoundException;
}
