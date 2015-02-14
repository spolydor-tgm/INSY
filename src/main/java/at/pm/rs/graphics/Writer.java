package at.pm.rs.graphics;

import at.pm.rs.connection.TableData;

/**
 * This interface should be implemented by classes which want to print a
 * SetOfData. It does not matter if the class wants to print it in a File or to
 * the console.
 * 
 * @author Patrick Malik
 * @version 20150114
 */
public interface Writer {

	/**
	 * prints a Set of Data to wherever the implementing class defines
	 * 
	 * @param data
	 *            the SetOfData to be printed.
	 */
	public void print(TableData data[]);

}
