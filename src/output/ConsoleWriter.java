package output;

import java.util.ArrayList;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class ConsoleWriter implements Writer {

	/**
	 * @see output.Writer#write(java.util.ArrayList)
	 * Writes the lines on the console
	 *
	 */
	public void write(ArrayList<String> lines) {
		for (int x = 0; x < lines.size(); x++)
			System.out.println(lines.get(x));
	}

}
