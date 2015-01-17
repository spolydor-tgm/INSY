package execute;

import cli.ArgumentParser;
import connection.Connection;
import format.Process;
import output.Writer;
import output.WriterFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Stefan Polydor
 * @version 14.01.15
 */
public class execute {
	public static void main(String[] args) {
		ArgumentParser argumentParser = new ArgumentParser(args); // Parsing the arguments
		Connection connection = null;
		try {
			connection = new Connection(argumentParser.getArguments()); // Connecting to the DBMS
		} catch (SQLException e) {
			System.err.println("One of your parameters for the Select-Statement is not correct");
			System.exit(0);
		}

		WriterFactory writerFactory = new WriterFactory();
		Writer writer = null;
		String specifiedWriter = null;
		try { // Creating a Writer
			if (connection.getOutputType() == null)
				specifiedWriter = "console";
			else
				specifiedWriter = "file";

			writer = writerFactory.createWriter(specifiedWriter, connection.getOutputType()); // New ConsoleWriter

			format.Process process = new Process(connection.getResultSet(), connection.getTrennzeichen(), connection.getColumns());
			writer.write(process.readAllLines()); // Processing all data sets and writing them to the specified target

		} catch (IOException ioe) {
			System.err.println("Unable to write the File");
			System.exit(0);
		}

		connection.closeConnection(); // Closing all open connections
	}
}