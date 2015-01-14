package at.pm.rs.graphics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import at.pm.rs.connection.SetOfData;

/**
 * This class creates a Writer object that prints text to a file. It generates
 * from the given SetOfData the fitting relations model.
 * 
 * @author Patrick Malik
 * @version 20150114
 */
public class TextWriter extends FileWriter {

	/**
	 * prints the given SetOfData as RM to a file.
	 * 
	 * @see at.pm.rs.graphics.Writer#print(at.pm.rs.connection.SetOfData)
	 * @param data
	 *            the SetOfData to be printed.
	 */
	@Override
	public void print(SetOfData data) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("the-file-name.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			//TODO logger error
		} catch (UnsupportedEncodingException e) {
			// TODO Logger error
		}
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
	}

	private String model(SetOfData data) {
		
		return null;
	}
}
