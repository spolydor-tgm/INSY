package format;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Stefan Polydor
 * @version 16.01.15
 */
public class Process {

	private ArrayList<String> formattedOutput;

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
				build += resultSet.getObject(x) + trennzeichen + " ";
				if (x % columns == 0) {
					formattedOutput.add(build);
					build = "";
				}
			}
			return formattedOutput;
		} catch (SQLException e) {}
		return null;
	}
}
