package java.rs.at.pm.graphics;

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
	 * Gets the set aoutpur directory
	 * 
	 * @return outputDir the output directory
	 */
	public String getOutputDir() {
		return outputDir;
	}

	/**
	 * Sets the given output directory
	 * 
	 * @param outputDir the given output directory
	 */
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

}
