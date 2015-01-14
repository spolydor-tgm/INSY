package at.pm.rs.connection;

import at.pm.rs.utils.ArgumentParser;

import java.sql.*;
import java.util.ArrayList;

public class ConnectorToMySQL implements ConnectorTo{

	private ConnectionArguments data;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private SetOfData setOfData;
	
	public ConnectorToMySQL(ConnectionArguments connectionArguments) {
		data = connectionArguments;
		this.connect();

		DatabaseMetaData md = null;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);

			// ResultSetMetaData rsmd = rs.getMetaData();

			// int columnsNumber = rsmd.getColumnCount();
			// System.out.println(columnsNumber);
			while (rs.next()) {
				System.out.println(rs.getString(3));
			}

			ArrayList columnNames = new ArrayList();
			ArrayList data = new ArrayList();


			PreparedStatement st = conn.prepareStatement("Select * from test;");
			ResultSet rss = st.executeQuery();
			ResultSetMetaData rsmd = st.getMetaData();
			int columns = rsmd.getColumnCount();
			//  Get column names
			for (int i = 1; i <= columns; i++) {
				columnNames.add(rsmd.getColumnName(i));
				System.out.println(columnNames.get(i - 1) + ": " + rsmd.getColumnTypeName(i));
				System.out.println(rsmd.isAutoIncrement(i)); // true wenn ja und falls wenn nicht
				System.out.println(rsmd.isNullable(i)); // Gibt 1 zurueck, wenn keine Nullwerte erlaubt sind
			}
			//  Get row data
			while (rss.next()) {
				ArrayList row = new ArrayList(columns);
				for (int i = 1; i <= columns; i++) {
					row.add( rss.getObject(i) );
				}
				data.add( row );
			}

			/*
			ArrayList colNames = new ArrayList();
			ResultSet pkfk = md.getPrimaryKeys(null, null, "test");
			ResultSetMetaData rssmd = pkfk.getMetaData();
			for (int i = 1; i <= rssmd.getColumnCount(); i++) {
				colNames.add(rssmd.getColumnName(i));
				System.out.println(colNames.get(i-1));
			}
			*/
			DatabaseMetaData meta=conn.getMetaData();
			rs= meta.getTables(null, null, "test", new String[]{"TABLE"});
			rs=meta.getPrimaryKeys(null, null, "test");
			while(rs.next())
				System.out.println("Primary Key :"+rs.getString(4));

			rs= meta.getTables(null, null, "test2", new String[]{"TABLE"});
			rs=meta.getExportedKeys(null, null, "test2");
			while(rs.next())
				System.out.println("Foreign Key :" + rs.getString(4));

			rs= meta.getTables(null, null, "test2", new String[]{"TABLE"});
			rs=meta.getPrimaryKeys(null, null, "test2");
			while(rs.next())
				System.out.println("Primary Key :" + rs.getString(4));

			/*
			// Create Vectors and copy over elements from ArrayLists to them
			// Vector is deprecated but I am using them in this example to keep
			// things simple - the best practice would be to create a custom defined
			// class which inherits from the AbstractTableModel class
			Vector columnNamesVector = new Vector();
			Vector dataVector = new Vector();

			for (int i = 0; i < data.size(); i++) {
				ArrayList subArray = (ArrayList)data.get(i);
				Vector subVector = new Vector();
				for (int j = 0; j < subArray.size(); j++) {
					subVector.add(subArray.get(j));
				}
				dataVector.add(subVector);
			}

			for (int i = 0; i < columnNames.size(); i++ )
				columnNamesVector.add(columnNames.get(i));

			//  Create table with database data
			JTable table = new JTable(dataVector, columnNamesVector) {
				public Class getColumnClass(int column) {
					for (int row = 0; row < getRowCount(); row++) {
						Object o = getValueAt(row, column);

						if (o != null) {
							return o.getClass();
						}
					}

					return Object.class;
				}
			};
			/*
			JFrame jFrame = new JFrame();
			JScrollPane scrollPane = new JScrollPane( table );
			jFrame.getContentPane().add( scrollPane);

			JPanel buttonPanel = new JPanel();
			jFrame.getContentPane().add( buttonPanel, BorderLayout.SOUTH );
			jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
			*/

			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void connect() {
		try {
			// Treiber laden
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung mit dem DBMS herstellen
			String url = "jdbc:mysql://" + data.getHostname() + "/" + data.getDBName();
			conn = DriverManager.getConnection(url, data.getUsername(), data.getPwd());

			st = conn.createStatement(); // auslagern !!!!

		} catch (ClassNotFoundException e) {
			System.out.println("Unnable to load teh class");
			System.exit(0);
		} catch (SQLException e) {
			System.err.println("Unable to connect to the Database Management System");
			System.exit(0);
		}
	}

	public void readPkFk(int position) {

	}

	public void readExtraAttributes(int position) {

	}

	public void	readTable() {

	}

	public static void main(String[] args) {
		ArgumentParser ap = new ArgumentParser();
		new ConnectorToMySQL(ap.parseArguments(args));
	}
}
