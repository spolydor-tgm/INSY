package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class FileWriterPersonal implements Writer {

	private File file;

	private FileWriter fileWriter;

	public FileWriterPersonal(String filename) throws IOException {
		String filenameUendung = filename + ".txt";
		file = new File(filenameUendung);
		try {
			fileWriter = new FileWriter(file, true);
		} catch (IOException e) {
			System.err.println("Unable to write into the File");
			System.exit(0);
		}
	}

	/**
	 * @see output.Writer#write(java.lang.String)
	 * Writes the line into the File
	 *  
	 */
	public void write(String line) throws IOException {
		try {
			fileWriter.write(line);
			fileWriter.write("\r");
		} catch (IOException e) {
			System.err.println("Unable to write the File"); // Outsourcing later !!!!!!!!!!!!!!!
			System.exit(0);
		}
	}

	public void closeWriter() throws IOException {
		try {
			fileWriter.flush(); // Leeren des Streams
			fileWriter.close(); // Writer Stream wird geschlossen
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
