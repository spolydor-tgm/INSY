package at.pm.rs.test.connection;

import at.pm.rs.connection.ForeignKey;
import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TableDataTest {

	private SetOfData setOfData;

	private TableData tableData;

	@Before
	public void setUp() throws Exception {
		tableData = new TableData("testing");

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
	public void testAddSetOfData() throws Exception {
		tableData.addSetOfData(setOfData);
		assertEquals("[" + '\n' + "test: INT PK= true FK= master.slave autoincrement= true nullable= true unique= true]", tableData.getSetOfData().toString());
	}

	@Test
	public void testGetSetOfData() throws Exception {
		setOfData.setAutoincrement(false);
		tableData.addSetOfData(setOfData);
		assertEquals("[" + '\n' + "test: INT PK= true FK= master.slave nullable= true unique= true]", tableData.getSetOfData().toString());
	}

	@Test
	public void testGetTableName() throws Exception {
		assertEquals("testing", tableData.getTableName());
	}
}