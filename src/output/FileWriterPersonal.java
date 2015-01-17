package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class FileWriterPersonal implements Writer {

	// The File where the lines will be written
	private File file;

	// Needed for writing lines to the File
	private FileWriter fileWriter;

	/**
	 * Creates a new file with the given filename and is creating a FileWriter for this File
	 * @param filename name of the File
	 * @throws IOException
	 */
	public FileWriterPersonal(String filename) throws IOException {
		String filenameUendung = filename + ".txt";
		file = new File(filenameUendung);

		fileWriter = new FileWriter(file, true);
	}

	/**
	 * @see output.Writer#write(java.util.ArrayList)
	 * Writes the line into the File
	 *  
	 */
	public void write(ArrayList<String> lines) throws IOException {
		for (int x = 0; x < lines.size(); x++) { // Writing all Lines from lines into the File
			fileWriter.write(lines.get(x));
			fileWriter.write(System.lineSeparator()); // "\r"
		}

		this.closeWriter();
	}

	/**
	 * Closing the writer
	 * @throws IOException if there is Something wrong, while closing the FileWriter
	 */
	public void closeWriter() throws IOException {
			fileWriter.flush(); // Leeren des Streams
			fileWriter.close(); // Writer Stream wird geschlossen
	}

}
