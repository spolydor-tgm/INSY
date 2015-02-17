package at.pm.rs.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;
import at.pm.rs.graphics.html.Element;
import at.pm.rs.graphics.html.HTMLForeignKey;
import at.pm.rs.graphics.html.HTMLTag;
import at.pm.rs.graphics.html.HTMLPrimaryKey;

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
		File output = new File(this.getOutputDir() + "/RM.html");
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO logger error
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Logger error
			writer.close();
		}

		// File htmlTemplate = new File("");
		String content = "";

		FileReader fileReader = null;
		try {
			fileReader = new FileReader("template.html");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int i;

		try {
			while ((i = fileReader.read()) != -1) {
				char ch = (char) i;

				content += ch;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// content = new Scanner(htmlTemplate).useDelimiter("\\Z").next();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		String body = model(data);

		content = content.replace("$title", "MyFancyRm");
		content = content.replace("$body", body);

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
	public String model(TableData datasets[]) {

		String tables = "";

		for (TableData data : datasets) {

			
			String set = "";
			set += tableBegin(data.getTableName());

			for (SetOfData cur : data.getSetOfData()) {
				String name = cur.getName();
				HTMLTag attr = new Element();

				if (cur.getFk() != null) {
					if (!cur.getFk().equals("")) {
						name = cur.getFk() + ":" + name;
						attr = new HTMLForeignKey(attr);
					}
				}

				if (cur.getIsPk())
					attr = new HTMLPrimaryKey(attr);
				
				System.out.println(name);
				set += attr.getTag() + ", ";
				set = set.replace("$tag", name);
			}
			set = set.substring(0, set.lastIndexOf(","));
			set += tableEnd();
			tables += set + "\n";
			System.out.println(set);
		}

		return tables;
	}

	private String tableBegin(String tableName) {
		return "<p>" + tableName + "(";
	}

	private String tableEnd() {
		return ")</p>";
	}

}
