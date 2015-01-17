package output;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public interface Writer {

	/**
	 * Provides the write(...) method for the different Writers
	 * @param lines which will be written
	 * @throws java.io.IOException if the File cannot be created or written
	 */
	public abstract void write(ArrayList<String> lines) throws IOException;

}
