package at.pm.rs.test.graphics;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import at.pm.rs.connection.ForeignKey;
import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;
import at.pm.rs.graphics.TextWriter;

public class TextWriterTest {

	TextWriter t;

	@Before
	public void setUp() {
		t = new TextWriter();
	}

	@Test
	public void TestIfModelWithNullReturnsNothing() {
		t.model(null);
	}

	@Test
	public void TestIfModelWorksWithJustOneTableWithPK() {
		SetOfData sod = new SetOfData();
		sod.setAutoincrement(false);
		sod.setFk(null);
		sod.setIsNullable(false);
		sod.setName("a1");
		sod.setPk(true);
		sod.setType("integer");
		sod.setUnique(true);
		TableData td = new TableData("t1");
		td.addSetOfData(sod);
		TableData[] tds = new TableData[1];
		tds[0] = td;
		InputStreamReader fileReader = new InputStreamReader(getClass().getResourceAsStream("/testResources/oneEntity.html"));

		int i;
		String content = "";
		try {
			while ((i = fileReader.read()) != -1) {
				char ch = (char) i;

				content += ch;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(content, t.model(tds));

	}

	@Test
	public void TestIfModelShowsAttributesRight() {
		SetOfData sod = new SetOfData();
		sod.setAutoincrement(false);
		sod.setFk(null);
		sod.setIsNullable(false);
		sod.setName("a1");
		sod.setPk(true);
		sod.setType("integer");
		sod.setUnique(true);
		TableData td = new TableData("t1");
		td.addSetOfData(sod);

		SetOfData sod2 = new SetOfData();
		sod2.setAutoincrement(false);
		sod2.setFk(null);
		sod2.setIsNullable(false);
		sod2.setName("a2");
		sod2.setPk(false);
		sod2.setType("integer");
		sod2.setUnique(true);

		td.addSetOfData(sod2);

		TableData[] tds = new TableData[1];
		tds[0] = td;
		InputStreamReader fileReader = new InputStreamReader(getClass().getResourceAsStream("/testResources/withAttributes.html"));

		int i;
		String content = "";
		try {
			while ((i = fileReader.read()) != -1) {
				char ch = (char) i;

				content += ch;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(content, t.model(tds));
	}

	@Test
	public void TestModelWithAForeignKey() {
		SetOfData sod = new SetOfData();
		sod.setAutoincrement(false);
		sod.setFk(null);
		sod.setIsNullable(false);
		sod.setName("a1");
		sod.setPk(true);
		sod.setType("integer");
		sod.setUnique(true);

		TableData td = new TableData("t1");
		td.addSetOfData(sod);

		SetOfData sod2 = new SetOfData();
		sod2.setAutoincrement(false);
		sod2.setFk(null);
		sod2.setIsNullable(false);
		sod2.setName("a2");
		sod2.setPk(false);
		sod2.setType("integer");
		sod2.setUnique(true);
		td.addSetOfData(sod2);

		TableData td2 = new TableData("t2");

		SetOfData sod3 = new SetOfData();
		sod3.setAutoincrement(false);
		sod3.setFk(null);
		sod3.setIsNullable(false);
		sod3.setName("a1");
		sod3.setPk(true);
		sod3.setType("integer");
		sod3.setUnique(true);
		td2.addSetOfData(sod3);

		SetOfData sod4 = new SetOfData();
		sod4.setAutoincrement(false);
		sod4.setFk(new ForeignKey("t1", "a2"));
		sod4.setIsNullable(false);
		sod4.setName("a2");
		sod4.setPk(false);
		sod4.setType("integer");
		sod4.setUnique(true);
		td2.addSetOfData(sod4);

		TableData[] tds = new TableData[2];
		tds[0] = td;
		tds[1] = td2;

		InputStreamReader fileReader = new InputStreamReader(getClass().getResourceAsStream("/testResources/foreignkeys.html"));

		int i;
		String content = "";
		try {
			while ((i = fileReader.read()) != -1) {
				char ch = (char) i;

				content += ch;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(content, t.model(tds));
	}

	@Test
	public void TestIfPrintPrintsToFile() {
		SetOfData sod = new SetOfData();
		sod.setAutoincrement(false);
		sod.setFk(null);
		sod.setIsNullable(false);
		sod.setName("a1");
		sod.setPk(true);
		sod.setType("integer");
		sod.setUnique(true);
		TableData td = new TableData("t1");
		td.addSetOfData(sod);
		TableData[] tds = new TableData[1];
		tds[0] = td;
		InputStreamReader fileReader = new InputStreamReader(getClass().getResourceAsStream("/testResources/RMTest.html"));

		int i;
		String content = "";
		try {
			while ((i = fileReader.read()) != -1) {
				char ch = (char) i;

				content += ch;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			t.setOutputDir(getClass().getResource("/testResources").toURI().getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.print(tds);

		InputStreamReader fileReader2 = new InputStreamReader(getClass().getResourceAsStream("/testResources/RM.html"));

		int i2;
		String content2 = "";
		try {
			while ((i2 = fileReader2.read()) != -1) {
				char ch = (char) i2;

				content2 += ch;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(content, content2);

	}
}
