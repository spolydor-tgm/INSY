package at.pm.rs.utils;

import at.pm.rs.graphics.FileWriter;
import at.pm.rs.graphics.TextWriter;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class Main {
	
	public static void main(String[] args) {
		
		FileWriter w = new TextWriter();
		w.setOutputDir("Dein outputDir");
		w.print("ein Array von TableDatas");
	}
}
