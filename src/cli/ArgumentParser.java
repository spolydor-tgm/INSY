package cli;


import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class ArgumentParser {

	// variable for saving all 11 arguments
	private String[] arguments = new String[11];

	// @Option(name="-t") private String t = "default_value";
	// Creating all needed Options for the cmd line
	@Option(name="-h") private String host = "localhost";

	@Option(name="-u") private String user = System.getProperty("user.name");

	@Option(name="-p") private String pwd = null;

	@Option(name="-d", required = true) private String dbname;

	@Option(name="-s") private String fieldSort;

	@Option(name="-r", depends = {"-s"}) private String sortDir;

	@Option(name="-w") private String where;

	@Option(name="-t") private String trennzeichen = ";";

	@Option(name="-f", required = true) private String selectFields;

	@Option(name="-o") private String outputType;

	@Option(name="-T", required = true) private String tableName;

	/**
	 * Parsing all the Options and check if there are some required Options are missing.
	 * Saving them all and if there are some Options missing, the synopsis plus a little help will be printed out.
	 * @param args all Options which were given from the cmd line
	 */
	public ArgumentParser(String[] args) {
		CmdLineParser cmdLineParser = new CmdLineParser(this); // Creating new CmdLineParser

		try {
			cmdLineParser.parseArgument(args); // Parsing all Arguments
			// Saving all Options
			arguments[0] = host;
			arguments[1] = user;
			arguments[2] = pwd;
			arguments[3] = dbname;
			arguments[4] = fieldSort;
			arguments[5] = sortDir;
			arguments[6] = where;
			arguments[7] = trennzeichen;
			arguments[8] = selectFields;
			arguments[9] = outputType;
			arguments[10] = tableName;

		} catch (CmdLineException e) { // If Something is wrong with the Options, this will be printed out
			System.err.println("Die Parameter wurden nicht passend eingegeben" + '\n');
			System.err.println("bzw. eine der Benoetigten Parameter ist nicht angegeben. -d Datenbankname -f FelderImErgebnisEnthalten -T tabellenname" + '\n');
			System.err.println("Beispiel der Verwendung mit vielen Parametern:");
			System.err.println("java -jar TheExporter.jar -h projekte.tgm.ac.at -u test -p test -d megamarkt -T produkt -f titel,preis -s preis -r DESC -w \"preis > 4\" -t \"$\" ");
			System.exit(0);
		}

	}

	/**
	 *
	 * @return Arguments all Saved ones
	 */
	public String[] getArguments() {
		return arguments;
	}

}
