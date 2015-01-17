package cli;


import org.kohsuke.args4j.Option;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class ArgumentParser {

	private String[] arguments;

	@Option(name="-h") private String host;

	@Option(name="-u") private String user;

	@Option(name="-p") private String pwd;

	@Option(name="-d", required = true) private String dbname;

	@Option(name="-s") private String fieldSort;

	@Option(name="-r", depends = {"-s"}) private String sortDir;

	@Option(name="-w") private String where;

	@Option(name="-t") private String trennzeichen;

	@Option(name="-f", required = true) private String selectFields;

	@Option(name="-o") private String outputType;

	@Option(name="-T", required = true) private String tableName;

	public ArgumentParser(String[] args) {
		/*
		 if (arguments[0] != null)
		 dbms = arguments[0];
		 if (arguments[1] != null)		// Auslagern !!!
		 user = arguments[1];
		 if (arguments[2] != null)
		 pwd	= arguments[2];
		 */

	}

	public String[] getArguments() {
		return arguments;
	}

}
