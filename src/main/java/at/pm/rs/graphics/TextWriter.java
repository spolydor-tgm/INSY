package at.pm.rs.graphics;

import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * This class creates a Writer object that prints text to a file. It generates
 * from the given SetOfData the fitting relations model.
 * 
 * @author Patrick Malik
 * @version 20150114
 */
public class TextWriter extends FileWriter {

	/**
	 * prints the given SetOfData as RM to a file.
	 * 
	 * @see Writer#print(rs.at.pm.connection.SetOfData)
	 * @param data
	 *            the SetOfData to be printed.
	 */
	@Override
	public void print(TableData data[]) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(this.getOutputDir() + "/RM.html", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO logger error
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Logger error
			writer.close();
		}

		File htmlTemplate = new File("ressource/template.html");
		String content = "";

		try {
			content = new Scanner(htmlTemplate).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String body = model(data);

		content.replace("$title", "MyFancyRm");
		content.replace("$body", body);

		writer.print(content);
		writer.close();
	}

	/**
	 * Creates html-code in a String out of the {@link SetOfData} given as
	 * parameter.
	 * 
	 * @param data
	 *            the given {@link SetOfData}
	 * @return a html representation of the RM created from the
	 *         {@link SetOfData}
	 */
	private String model(TableData dataSets[]) {

		String tables = "";

		for (TableData data : dataSets) {

			String set = "";

			set += tableBegin(data.getTableName());

			for (SetOfData cur : data.getSetOfData()) {
				String name = cur.getName();
				HTMLTag attr = new Element(name);

				if (cur.getFk() != null || !cur.getFk().equals("")) {
					attr.setTag(cur.getFk() + ":" + name);
					attr = new ForeignKey(attr);
				}

				if (cur.getIsPk())
					attr = new PrimaryKey(attr);

				set += attr.getTag() + ", ";
			}
			set = set.substring(0, set.lastIndexOf(",") - 1);
			set += tableEnd();
			tables += set + "\n";
		}

		return tables;
	}

	private String tableBegin(String tableName) {
		return tableName + "(";
	}

	private String tableEnd() {
		return ")";
	}

}
