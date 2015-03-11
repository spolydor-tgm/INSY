package at.pm.rs.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import at.pm.rs.test.connection.ConnectionArgumentsTest;
import at.pm.rs.test.connection.ConnectorToMySQLTest;
import at.pm.rs.test.connection.ForeignKeyTest;
import at.pm.rs.test.connection.SetOfDataTest;
import at.pm.rs.test.connection.TableDataTest;
import at.pm.rs.test.graphics.DOTTest;
import at.pm.rs.test.graphics.GraphWriterTest;
import at.pm.rs.test.graphics.TextWriterTest;
import at.pm.rs.test.utils.ArgumentParserTest;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.02.15
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		ConnectionArgumentsTest.class,
		ForeignKeyTest.class,
		SetOfDataTest.class,
		TableDataTest.class,
		ConnectorToMySQLTest.class,
		GraphWriterTest.class,
		TextWriterTest.class,
		ArgumentParserTest.class,
		DOTTest.class
		})

public class AllTests {}
