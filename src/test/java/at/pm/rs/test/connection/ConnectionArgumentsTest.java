package at.pm.rs.test.connection;

import at.pm.rs.connection.ConnectionArguments;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class ConnectionArgumentsTest {

	private ConnectionArguments connectionArguments;

	@org.junit.Before
	public void setUp() throws Exception {
		connectionArguments = new ConnectionArguments("local", "master", "sicha_nicht", "haha", "mysql");
	}

	@org.junit.Test
	public void testGetArguments() throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("hostname", "local");
		map.put("password", "sicha_nicht");
		map.put("dbname", "haha");
		map.put("dbms", "mysql");
		map.put("username", "master");

		assertEquals(map, connectionArguments.getArguments());
	}

	@org.junit.Test
	public void testSetArguments() throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("hostname", "local");
		map.put("password", "sicha_nicht");
		map.put("dbname", "haha");
		map.put("dbms", "mysql");
		map.put("username", "master");

		assertEquals(map, connectionArguments.getArguments());
	}

	@org.junit.Test
	public void testSetArguments2() throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("hostname", "local");
		map.put("password", "sicha_nicht");
		map.put("dbname", "haha");
		map.put("dbms", "mysql");
		map.put("username", "master");

		connectionArguments.setArguments(map);

		assertEquals(map, connectionArguments.getArguments());
	}

	@org.junit.Test
	public void testGetHostname() throws Exception {
		assertEquals("local", connectionArguments.getHostname());
	}

	@org.junit.Test
	public void testSetHostname() throws Exception {
		connectionArguments.setHostname("test");
		assertEquals("test", connectionArguments.getHostname());
	}

	@org.junit.Test
	public void testGetUsername() throws Exception {
		assertEquals("master", connectionArguments.getUsername());
	}

	@org.junit.Test
	public void testSetUsername() throws Exception {
		connectionArguments.setUsername("tester");
		assertEquals("tester", connectionArguments.getUsername());
	}

	@org.junit.Test
	public void testGetPwd() throws Exception {
		assertEquals("sicha_nicht", connectionArguments.getPwd());
	}

	@org.junit.Test
	public void testGetPwdNull() throws Exception {
		connectionArguments.setPwd(null);
		assertEquals("", connectionArguments.getPwd());
	}

	@org.junit.Test
	public void testSetPwd() throws Exception {
		connectionArguments.setPwd("sicher_nicht");
		assertEquals("sicher_nicht", connectionArguments.getPwd());
	}

	@org.junit.Test
	public void testGetDBName() throws Exception {
		assertEquals("haha", connectionArguments.getDBName());
	}

	@org.junit.Test
	public void testSetDBName() throws Exception {
		connectionArguments.setDBName("yeah");
		assertEquals("yeah", connectionArguments.getDBName());
	}

	@org.junit.Test
	public void testGetDBMS() throws Exception {
		assertEquals("mysql", connectionArguments.getDBMS());
	}

	@org.junit.Test
	public void testSetDBMS() throws Exception {
		connectionArguments.setDBMS("postgresql");
		assertEquals("postgresql", connectionArguments.getDBMS());
	}
}