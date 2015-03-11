package at.pm.rs.test.connection;

import at.pm.rs.connection.ForeignKey;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ForeignKeyTest {

	private ForeignKey foreignKey;

	@Before
	public void setUp() throws Exception {
		foreignKey = new ForeignKey("master", "slave");
	}

	@Test
	public void testToString() throws Exception {
		assertEquals("master.slave", foreignKey.toString());
	}

	@Test
	public void testGetRefTable() throws Exception {
		assertEquals("master", foreignKey.getRefTable());
	}

	@Test
	public void testSetRefTable() throws Exception {
		foreignKey.setRefTable("masterOfDisaster");
		assertEquals("masterOfDisaster", foreignKey.getRefTable());
	}

	@Test
	public void testGetRefAttribute() throws Exception {
		assertEquals("slave", foreignKey.getRefAttribute());
	}

	@Test
	public void testSetRefAttribute() throws Exception {
		foreignKey.setRefAttribute("slaveblak");
		assertEquals("slaveblak", foreignKey.getRefAttribute());
	}
}