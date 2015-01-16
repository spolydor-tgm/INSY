package output;

import java.io.IOException;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public interface Writer {

	/**
	 * Provides the write(...) method for the different Writers
	 * @param line which will be written
	 */
	public abstract void write(String line) throws IOException;

}
