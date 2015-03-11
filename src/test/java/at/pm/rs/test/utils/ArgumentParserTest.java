package at.pm.rs.test.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.pm.rs.connection.ConnectionArguments;
import at.pm.rs.utils.ArgumentParser;

public class ArgumentParserTest {
	
	ArgumentParser a;

	@Before
	public void creatingArgumentParser(){
		a = new ArgumentParser();
	}
	
	@Test
	public void testIfConnstructorSetsTheRightAttributes() {
		assertEquals("", a.getOutputDir());
	}
	
	@Test
	public void testIfGetAndSetOutputDirWorksProperly(){
		a.setOutputDir("outputdir");
		assertEquals("outputdir", a.getOutputDir());
	}
	
	@Test
	public void testIfGetAndSetDotDirWorksProperly(){
		a.setDotDir("dotDir");
		assertEquals("dotDir",a.getDotDir());
	}
	
	@Test
	public void testIfParsingArgumentsWorksWithDefaultValueForHostname(){
		ConnectionArguments b = a.parseArguments(new String[]{"--password","a","--dbname","a","--dbms","a","--outputDir","a","--dotDir","a"});
		assertEquals("localhost", b.getHostname());
	}

	@Test
	public void testIfParsingArgumentsWorksWithDefaultValueForUsername(){
		ConnectionArguments b = a.parseArguments(new String[]{"--password","a","--dbname","a","--dbms","a","--outputDir","a","--dotDir","a"});
		assertEquals(System.getProperty("user.name"), b.getUsername());
	}
	
	@Test
	public void testIfParsingArgumentsParsesHostnameRight(){
		ConnectionArguments b = a.parseArguments(new String[]{"-h","hostname","-u","user","--password","password","--dbname","dbname","--dbms","dbms","--outputDir","outputDir","--dotDir","dotDir"});
		assertEquals("hostname", b.getHostname());
	}
	
	@Test
	public void testIfParsingArgumentsParsesUsernameRight(){
		ConnectionArguments b = a.parseArguments(new String[]{"-h","hostname","-u","user","--password","password","--dbname","dbname","--dbms","dbms","--outputDir","outputDir","--dotDir","dotDir"});
		assertEquals("user", b.getUsername());
	}
	
	@Test
	public void testIfParsingArgumentsParsesPasswordRight(){
		ConnectionArguments b = a.parseArguments(new String[]{"-h","hostname","-u","user","--password","password","--dbname","dbname","--dbms","dbms","--outputDir","outputDir","--dotDir","dotDir"});
		assertEquals("password", b.getPwd());
	}
	
	@Test
	public void testIfParsingArgumentsParsesDBNameRight(){
		ConnectionArguments b = a.parseArguments(new String[]{"-h","hostname","-u","user","--password","password","--dbname","dbname","--dbms","dbms","--outputDir","outputDir","--dotDir","dotDir"});
		assertEquals("dbname", b.getDBName());
	}
	
	@Test
	public void testIfParsingArgumentsParsesDBMSRight(){
		ConnectionArguments b = a.parseArguments(new String[]{"-h","hostname","-u","user","--password","password","--dbname","dbname","--dbms","dbms","--outputDir","outputDir","--dotDir","dotDir"});
		assertEquals("dbms", b.getDBMS());
	}
	

}
