package at.pm.rs.utils;

import at.pm.rs.connection.ConnectionArguments;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * The argument parser for "Rueckwaertssalto", receives all the expected arguments and handles them
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public class ArgumentParser {
	
	CmdLineParser parser;
	
	/**
	 * Creates an ArgumentParser without big presets.
	 */
	public ArgumentParser(){
		outputDir = "";
		parser = new CmdLineParser(this);
	}
	
	@Option(name = "-h", usage = "the hostname used to establish a connection to the database, if there is no hostname given, it will use \"localhost\" as hostname!", required=false)
	private String hostname = "localhost";

	@Option(name="-u", usage="the username needed to estblish a connection with the database, if there is no username given, it uses the name of the user that runs the application.", required=false)
	private String username = System.getProperty("user.name");
	
	@Option(name="--password", usage="the password needed to establish a connection to the database, as default there is no password used!", required=false)
	private String password = "";
	
	@Option(name="--dbname", usage="the name of the database you want to connect with.", required=true)
	private String dbname;
	
	@Option(name="--dbms", usage="the name of your dbms.  You can choose from:\tmysql", required=true)
	private String dbms;
	
	@Option(name="--outputDir", usage="the directory of the output files", required=true)
	private String outputDir;
	
	@Option(name="--dotDir", usage="the directory of the dot executable (probably in the bin folder of Graphviz",  required=true)
	private String dotDir;
	
	
	/**
	 * Parses all the given arguments
	 * 
	 * @param args the arguments to parse
	 * @return ConnectionArguments object with the received and parsed data
	 */
	public ConnectionArguments parseArguments(String... args){
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			parser.printUsage(System.out);
			System.exit(1);
		}
		return new ConnectionArguments(hostname, username, password, dbname, dbms);
	}
	
	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getDotDir() {
		return dotDir;
	}

	public void setDotDir(String dotDir) {
		this.dotDir = dotDir;
	}
	
}
