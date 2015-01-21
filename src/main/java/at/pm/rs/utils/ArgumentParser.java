package at.pm.rs.utils;

import at.pm.rs.connection.ConnectionArguments;

public class ArgumentParser {
//	Options options;
	String outputDir;
	
	public ArgumentParser(){
//		options = new Options();
		outputDir = "";
	}
	
	@Option(name = "-h", usage = "the hostname used to establish a connection to the database")
	private String hostname = "localhost";
	
	@SuppressWarnings("static-access")
	public ConnectionArguments parseArguments(String... args){
//		Option hostname = OptionBuilder.hasArg().isRequired().withDescription("the hostname of the db ou want to connect with i.e. 192.168.0.22:2233").withArgName("hostname").create("h");
//		Option username = OptionBuilder.hasArg().isRequired().withDescription("the username needed to establish a connection").withArgName("username").create("u");
//		Option password = OptionBuilder.hasArg().withDescription("the password needed to establish a connection").withArgName("password").create("p");
//		Option dbname = OptionBuilder.hasArg().isRequired().withDescription("the name of the database you want to work with").withArgName("databasename").create("d");
//		Option dbms = OptionBuilder.hasArg().isRequired().withDescription("The dbms on which the database runs").withArgName("dbms").create("D");
//		Option outDir = OptionBuilder.hasArg().isRequired().withDescription("The directory in which the files should be saved").withArgName("output directory").create("o");
//		
//		options.addOption(hostname);
//		options.addOption(username);
//		options.addOption(password);
//		options.addOption(dbname);
//		options.addOption(dbms);
//		options.addOption(outDir);
//		
//		CommandLineParser parser = new BasicParser();
//		CommandLine line = null;
//		try {
//			line = parser.parse(options, args);
//		} catch (ParseException e) {
//			System.err.println("An Error occurred while parsing the arguments, check if all if your arguments are valid");
//			printHelp();
//		}
//		setOutputDir(line.getOptionValue("o"));
//		ConnectionArguments conargs = new ConnectionArguments(line.getOptionValue("h"), line.getOptionValue("u"), line.getOptionValue("p"), line.getOptionValue("d"), line.getOptionValue("D"));
//		return conargs;
		
		
		return null;

	}
	
//	public void printHelp(){
//		HelpFormatter formatter = new HelpFormatter();
//		formatter.printHelp("DB", options);
//	}
//	
//	public Options getOptions() {
//		return options;
//	}
//	public void setOptions(Options options) {
//		this.options = options;
//	}
//	public String getOutputDir() {
//		return outputDir;
//	}
//	public void setOutputDir(String outputDir) {
//		this.outputDir = outputDir;
//	}
	
	
	
}
