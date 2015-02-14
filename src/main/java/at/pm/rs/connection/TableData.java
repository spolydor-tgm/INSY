package at.pm.rs.connection;

import java.util.ArrayList;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class TableData {

	private String tableName; // Stores the tablename

	private ArrayList<SetOfData> dataSet = new ArrayList<>(); // Stores all attributes of the table

	/**
	 *
	 * @param tableName name of the table wich will be saved
	 */
	public TableData(String tableName) {
		this.tableName = tableName;
	}

	/**
	 *
	 * @param setOfData which an attribute from the table
	 */
	public void addSetOfData(SetOfData setOfData) {
		dataSet.add(setOfData);
	}

	/**
	 *
	 * @return SetOfData which contains all attributes from the table
	 */
	public ArrayList<SetOfData> getSetOfData() {
		return dataSet;
	}

	/**
	 *
	 * @return String tablename
	 */
	public String getTableName() {
		return tableName;
	}
}