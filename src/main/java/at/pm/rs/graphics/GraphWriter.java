package at.pm.rs.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;
import at.pm.rs.graphics.dot.DOTDecorator;
import at.pm.rs.graphics.dot.DOTIsAutoIncrement;
import at.pm.rs.graphics.dot.DOTIsNotNull;
import at.pm.rs.graphics.dot.DOTNode;
import at.pm.rs.graphics.dot.DOTPrimaryKey;
import at.pm.rs.graphics.dot.DOTUnique;
import at.pm.rs.graphics.dot.EntityConnection;
import at.pm.rs.graphics.dot.Node;

/**
 * This class creates a Writer object that prints the given
 * {@link rs.at.pm.connection.SetOfData} modelled to a entity-relationship-model
 * to a file with graphviz (.dot).
 * 
 * @author Patrick Malik
 * @version 20150114
 *
 */
public class GraphWriter extends FileWriter {
	
	String dotSource;
	
	public GraphWriter(String dotSource) {
		this.dotSource = dotSource;
	}

	public void print(TableData[] data) {
		PrintWriter writer = null;
		File output = new File(this.getOutputDir() + "\\ER.dot");
		System.out.println(output.getAbsolutePath());
		try {
			writer = new PrintWriter(output, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// TODO logger error
			return;
		} catch (UnsupportedEncodingException e) {
			// TODO Logger error
			e.printStackTrace();
			return;
		}

		writer.print(model(data));

		writer.close();
		//this.writeToImg(this.getOutputDir(), dotSource);
	}

	@Override
	public String model(TableData[] datasets) {
		String content = "";
		// For shaping them as entities or attributes
		ArrayList<String> tables = new ArrayList<>();
		// ArrayList<String> attributes = new ArrayList<>();
		// The relations between the entities or attributes get mapped in this
		// map
		HashMap<String, String> rel = new HashMap<>();
		HashMap<String, ArrayList<DOTNode>> duplicates = new HashMap<>();

		ArrayList<EntityConnection> cons = new ArrayList<>();

		content += "graph ER {\n";

		int i = 0;

		for (TableData data : datasets) {
			String curTableName = data.getTableName();
			tables.add(curTableName);

			for (SetOfData cur : data.getSetOfData()) {

				if (!duplicates.containsKey(cur.getName()))
					duplicates.put(cur.getName(), new ArrayList<>());

				DOTNode n = new Node(cur, cur.getName(), cur.getName() + i);

				if (cur.getIsPk()) {
					n = new DOTPrimaryKey(n);
				} else {

					if (!cur.getIsNullable())
						n = new DOTIsNotNull(n);

					if (cur.getIsUnique())
						n = new DOTUnique(n);

				}

				if (cur.getIsAutoincrement())
					n = new DOTIsAutoIncrement(n);

				duplicates.get(cur.getName()).add(n);

				rel.put(cur.getName() + i, curTableName);

				if (cur.getFk() != null) {
					char c1 = 'z';
					char c2 = 'z';
					if (cur.getIsPk() || cur.getIsUnique()) {
						c1 = '1';
						for (TableData td : datasets) {
							if (td.getTableName().equals(cur.getFk().getRefTable())) {
								for (SetOfData sod : td.getSetOfData()) {
									if (sod.getName().equals(cur.getFk().getRefAttribute())) {
										if (sod.getIsPk() || cur.getIsUnique()) {
											c2 = '1';
										} else {
											c2 = 'n';
										}
									}
								}
							}
						}

					} else {
						c1 = 'n';
						for (TableData td : datasets) {
							if (td.getTableName().equals(cur.getFk().getRefTable())) {
								for (SetOfData sod : td.getSetOfData()) {
									if (sod.getName().equals(cur.getFk().getRefAttribute())) {
										if (sod.getIsPk()) { // Or unique
											c2 = '1';
										}
									}
								}
							}
						}
					}
					EntityConnection ec = new EntityConnection("" + i, data.getTableName(), cur.getFk().getRefTable(), c1, c2);

					boolean existing = false;

					for (EntityConnection con : cons)
						if (con.compareTo(ec) == 0)
							existing = true;

					if (!existing)
						cons.add(ec);
				}

				i++;

			}

		}

		content += "node [shape=box];";

		for (String s : tables)
			content += s + ";";

		content += "\nnode [shape=ellipse];";

		for (Entry<String, ArrayList<DOTNode>> entry : duplicates.entrySet()) {
			ArrayList<DOTNode> values = entry.getValue();

			String doub = "{node [label=\"" + entry.getKey() + "\" decorate=\"true\"] ";

			for (DOTNode val : values) {
				doub += val.getSpecname();
				if (val instanceof DOTDecorator)
					doub += " [label=<" + val.getName() + ">]";
				// val.setName(val.getName().replace("$name",entry.getKey()));
				doub += ";";
			}

			doub += "}";
			content += doub + "\n";
		}

		content += "node [shape=diamond, label=\"\"];";
		for (EntityConnection s : cons)
			content += s.getName() + ";";

		// for (String s : attributes){
		// content += s + ";";
		// }

		// System.out.println(rel.toString());

		for (Map.Entry<String, String> entry : rel.entrySet())
			content += "\n" + entry.getKey() + " -- " + entry.getValue();

		content += "\n";

		for (EntityConnection ec : cons)
			content += "\n" + ec.toString();

		content += "\n}";
		// System.out.println(content);
		return content;
	}

	public void writeToImg(String outputDir, String dotSource) {
		GraphViz gv = new GraphViz(dotSource);
		gv.readSource(outputDir);
		String type = ".png";
		File out = new File(outputDir + "/ER" + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
	}

	public String getDotSource() {
		return dotSource;
	}

	public void setDotSource(String dotSource) {
		this.dotSource = dotSource;
	}
	
	
}
