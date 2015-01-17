package format;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Stefan Polydor
 * @version 16.01.15
 */
public class Process {

	// Saving all the lines, which will be written later
	private ArrayList<String> formattedOutput = new ArrayList<>();

	// ResultSet from a table of a Database after the executed SELECT Statement
	private ResultSet resultSet;

	// Seperator for a columns in a row
	private String trennzeichen;

	// columncount
	private int columns;

	/**
	 * Saving all needed variables for reading all data rows
	 * @param resultSet from a table of a Database after the executed SELECT Statement
	 * @param trennzeichen Seperator for a columns in a row
	 * @param columns columncount
	 */
	public Process(ResultSet resultSet, String trennzeichen, int columns) {
		this.resultSet = resultSet;
		this.trennzeichen = trennzeichen;
		this.columns = columns;
	}

	/**
	 * Reading all Data Rows from the executed SELECT Statement
	 * @return ArrayList(String) with all lines which were select
	 */
	public ArrayList<String> readAllLines() {
		try {
			String build = "";
			while(resultSet.next()) { // Row Count
				for (int y = 1; y <= columns; y++) // Column Count
					build += resultSet.getObject(y) + trennzeichen + " ";
				formattedOutput.add(build);
				build = "";
			}
		} catch (SQLException e) {}
		return formattedOutput; // Return the rows as a String seperated with the value of the seperator "trennzeichen"
	}
}
