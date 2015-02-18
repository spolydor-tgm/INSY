package at.pm.rs.graphics;

import at.pm.rs.connection.TableData;

/**
 * Implements the Writer interface. This class is the skelleton for every Writer
 * that wants to print to a File.
 * 
 * @author Patrick Malik
 * @version 20150114
 *
 */
public abstract class FileWriter implements Writer {

	String outputDir;

	/**
	 * Gets the set outpur directory
	 * 
	 * @return outputDir the output directory
	 */
	public String getOutputDir() {
		return outputDir;
	}

	/**
	 * Sets the given output directory
	 * 
	 * @param outputDir
	 *            the given output directory
	 */
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public abstract void print(TableData data[]);
	/**
	 * Creates a stringified model of the given data in the right format (i.e.
	 * .dot or .html) to insert into a prepared file
	 * 
	 * @param datasets All tables to be modeled to a String
	 * @return a stringified model of the tables
	 */
	public abstract String model(TableData datasets[]);
}
