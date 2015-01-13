package output;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class WriterFactory {

	/**
	 * Creates a new Writer. Depending of the type
	 * @param type of the Writer
	 * @return new Writer
	 */
	public Writer createWriter(String type) {
		switch (type) {
			case "file":
				return new FileWriter("t");
			case "console":
				return new ConsoleWriter();
		}
		return null;
	}

}
