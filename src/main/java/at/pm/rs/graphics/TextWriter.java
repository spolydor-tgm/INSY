package at.pm.rs.graphics;

import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;

import java.io.*;

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
			System.out.println("asdasdasdsda");
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

		System.out.println("Während auslesen");
		String body = model(data);

		System.out.println(body);

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
	private String model(TableData dataSets[]) {

		String tables = "";

		for (TableData data : dataSets) {

			String set = "";
			System.out.println(data.getSetOfData().get(0).getName());
			set += tableBegin(data.getTableName());

			for (SetOfData cur : data.getSetOfData()) {
				String name = cur.getName();
				HTMLTag attr = new Element(name);

				if (cur.getFk() != null) {
					if (!cur.getFk().equals("")) {
						attr.setTag(cur.getFk() + ":" + name);
						attr = new ForeignKey(attr);
					}
				}
				
				
				
				if (cur.getIsPk())
					attr = new PrimaryKey(attr);

				set += attr.getTag() + ", ";
			}
			set = set.substring(0, set.lastIndexOf(","));
			set += tableEnd();
			tables += set + "\n";
		}

		return tables;
	}

	private String tableBegin(String tableName) {
		return "<p>"+tableName + "(";
	}

	private String tableEnd() {
		return ")</p>";
	}

}
