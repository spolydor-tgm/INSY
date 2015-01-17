package format;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Stefan Polydor
 * @version 16.01.15
 */
public class Process {

	private ArrayList<String> formattedOutput = new ArrayList<>();

	private ResultSet resultSet;

	private String trennzeichen;

	private int columns;

	public Process(ResultSet resultSet, String trennzeichen, int columns) {
		this.resultSet = resultSet;
		this.trennzeichen = trennzeichen;
		this.columns = columns;
	}

	public ArrayList<String> readAllLines() {
		try {
			String build = "";
			for (int x = 1; resultSet.next(); x++) {
				for (int y = 1; y <= columns; y++)
					build += resultSet.getObject(y) + trennzeichen + " ";
				formattedOutput.add(build);
				build = "";
			}
		} catch (SQLException e) {}
		return formattedOutput;
	}
}
