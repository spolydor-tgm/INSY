package at.pm.rs.connection;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class TableData {

	private String tableName;

	private SetOfData dataSet;

	public TableData(String tableName) {
		this.tableName = tableName;
	}

	public void addSetOfData(SetOfData setOfData) {
		dataSet = setOfData;
	}

	public SetOfData getSetOfData() {
		return dataSet;
	}

	public String getTableName() {
		return tableName;
	}
}