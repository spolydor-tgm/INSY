package at.pm.rs.utils;

import at.pm.rs.connection.ConnectorToMySQL;
import at.pm.rs.connection.SetOfData;
import at.pm.rs.connection.TableData;
import at.pm.rs.graphics.FileWriter;
import at.pm.rs.graphics.GraphWriter;
import at.pm.rs.graphics.TextWriter;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @author Patrick Malik
 * @version 28.01.15
 */

public class Main {
	
	public static void main(String[] args) {
		ArgumentParser ap = new ArgumentParser();
		try {
			ConnectorToMySQL connectorToMySQL = new ConnectorToMySQL(ap.parseArguments(args));
			ArrayList<TableData> test = connectorToMySQL.readAllFromAllTables();

			/*
			 * "C:\Program Files (x86)\Graphviz2.38\bin\dot" -Tpng src\ER.DOT -o outfile.png
			 */			
			FileWriter t = new TextWriter();
			t.setOutputDir(ap.getOutputDir());
			FileWriter g = new GraphWriter(ap.getDotDir());
			g.setOutputDir(ap.getOutputDir());
			TableData[] te = new TableData[test.size()];
			for (int x = 0; x < te.length; x++)
				te[x] = test.get(x);
			g.print(te);
			t.print(te);
			
			
			connectorToMySQL.closeConnections();
		} catch (SQLException e) {
			System.err.println("Failed to connect to the DBMS!!!");
			System.err.println("Wrong username/password/dmbs-Adress");
		} catch (ClassNotFoundException ee) {
			System.err.println("Unnable to load the class");
		}
	}
}
