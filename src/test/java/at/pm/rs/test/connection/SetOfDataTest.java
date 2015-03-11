package at.pm.rs.test.connection;

import at.pm.rs.connection.ForeignKey;
import at.pm.rs.connection.SetOfData;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SetOfDataTest {

	private SetOfData setOfData;

	@Before
	public void setUp() throws Exception {
		setOfData = new SetOfData();

		setOfData.setType("INT");
		setOfData.setUnique(true);
		setOfData.setFk(new ForeignKey("master", "slave"));
		setOfData.setName("test");
		setOfData.setAutoincrement(true);
		setOfData.setPk(true);
		setOfData.setIsNullable(true);
	}

	@Test
	public void testGetType() throws Exception {
		assertEquals("INT", setOfData.getType());
	}

	@Test
	public void testSetType() throws Exception {
		setOfData.setType("VARCHAR");
		assertEquals("VARCHAR", setOfData.getType());
	}

	@Test
	public void testSetUnique() throws Exception {
		setOfData.setUnique(false);
		assertEquals(false, setOfData.getIsUnique());
	}

	@Test
	public void testGetIsUnique() throws Exception {
		assertEquals(true, setOfData.getIsUnique());
	}

	@Test
	public void testGetIsPk() throws Exception {
		assertEquals(true, setOfData.getIsPk());
	}

	@Test
	public void testSetPk() throws Exception {
		setOfData.setPk(false);
		assertEquals(false, setOfData.getIsPk());
	}

	@Test
	public void testGetFk() throws Exception {
		assertEquals("master.slave", setOfData.getFk().toString());
	}

	@Test
	public void testSetFk() throws Exception {
		setOfData.setFk(new ForeignKey("masterOfDisaster", "slaveblak"));
		assertEquals("masterOfDisaster.slaveblak", setOfData.getFk().toString());
	}

	@Test
	public void testGetName() throws Exception {
		assertEquals("test", setOfData.getName());
	}

	@Test
	public void testSetName() throws Exception {
		setOfData.setName("testing");
		assertEquals("testing", setOfData.getName());
	}

	@Test
	public void testGetIsAutoincrement() throws Exception {
		assertEquals(true, setOfData.getIsAutoincrement());
	}

	@Test
	public void testSetAutoincrement() throws Exception {
		setOfData.setAutoincrement(false);
		assertEquals(false, setOfData.getIsAutoincrement());
	}

	@Test
	public void testGetIsNullable() throws Exception {
		assertEquals(true, setOfData.getIsNullable());
	}

	@Test
	public void testSetIsNullable() throws Exception {
		setOfData.setIsNullable(false);
		assertEquals(false, setOfData.getIsNullable());
	}

	@Test
	public void testToString() throws Exception {
		assertEquals("" + '\n' + "test: INT PK= true FK= master.slave autoincrement= true nullable= true unique= true", setOfData.toString());
	}
}