package output;

import java.io.IOException;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class WriterFactory {

	/**
	 * Creates a new Writer. Depending of the type
	 * @param type of the Writer
	 * @param filename if the choosen type needs a filename it must be given, else you can write null.
	 * @return new Writer
	 * @throws java.io.IOException if the File cannot be created or written
	 */
	public Writer createWriter(String type, String filename) throws IOException {
		switch (type) {
			case "file":
				return new FileWriterPersonal(filename);
			case "console":
				return new ConsoleWriter();
		}
		return null;
	}

}
