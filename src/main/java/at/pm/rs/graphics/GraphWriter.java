package at.pm.rs.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import at.pm.rs.connection.TableData;

/**
 * This class creates a Writer object that prints the given
 * {@link rs.at.pm.connection.SetOfData} modelled to a entity-relationship-model
 * to a file with graphviz.
 * 
 * @author Patrick Malik
 * @version 20150114
 *
 */
public class GraphWriter extends FileWriter {

	// @Override
	public void print(TableData[] data) {
		PrintWriter writer = null;
		File output = new File(this.getOutputDir() + "/ER.DOT");
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO logger error
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Logger error
			writer.close();
		}

		try {
			DOTNode.setUp(output, "G");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			DOTNode.endFile(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.close();
	}

	@Override
	public String model(TableData[] datasets) {
		return null;
	}
}
