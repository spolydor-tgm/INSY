package at.pm.rs.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.02.15
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		ConnectionArgumentsTest.class,
		ForeignKeyTest.class,
		SetOfDataTest.class,
		TableDataTest.class
		})

public class AllTests {}
