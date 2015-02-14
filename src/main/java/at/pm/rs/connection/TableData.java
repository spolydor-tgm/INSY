package at.pm.rs.connection;

import java.util.ArrayList;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class TableData {

	private String tableName;

	private ArrayList<SetOfData> dataSet = new ArrayList<>();

	public TableData(String tableName) {
		this.tableName = tableName;
	}

	public void addSetOfData(SetOfData setOfData) {
		dataSet.add(setOfData);
	}

	public ArrayList<SetOfData> getSetOfData() {
		return dataSet;
	}

	public String getTableName() {
		return tableName;
	}
}