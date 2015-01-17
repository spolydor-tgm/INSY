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

	private File file;

	private FileWriter fileWriter;

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
		for (int x = 0; x < lines.size(); x++) {
			fileWriter.write(lines.get(x));
			fileWriter.write(System.lineSeparator()); // "\r"
		}

		this.closeWriter();
	}

	public void closeWriter() throws IOException {
			fileWriter.flush(); // Leeren des Streams
			fileWriter.close(); // Writer Stream wird geschlossen
	}

}
