package output;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class ConsoleWriter implements Writer {

	/**
	 * @see output.Writer#write(java.lang.String)
	 * Writes the line on the console
	 *  
	 */
	public void write(String line) {
		System.out.println(line);
	}

}
