package at.pm.rs.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import at.pm.rs.connection.ConnectorToMySQL;
import at.pm.rs.connection.TableData;
import at.pm.rs.graphics.FileWriter;
import at.pm.rs.graphics.GraphWriter;
import at.pm.rs.graphics.TextWriter;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class Main {
	
	public static void main(String[] args) {
		ArgumentParser ap = new ArgumentParser();
		//new ConnectorToMySQL(ap.parseArguments(args));
		try {
			ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL(ap.parseArguments(args));
			ArrayList<TableData> test = connectorToMySQL.readAllFromAllTables();
//			for (TableData data : test) {
//				System.out.print(data.getTableName());
//				for (SetOfData setOfData : data.getSetOfData())
//					System.out.print(setOfData.toString());
//
//				System.out.println("" + '\n');
//			}
			
			FileWriter w = new TextWriter();
			w.setOutputDir("src");
			TableData[] te = new TableData[test.size()];
			for (int x = 0; x < te.length; x++)
				te[x] = test.get(x);
			w.print(te);
			
			connectorToMySQL.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ee) {
			ee.printStackTrace();
		}
	}
}
