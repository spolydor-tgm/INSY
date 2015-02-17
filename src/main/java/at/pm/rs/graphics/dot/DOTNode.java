package at.pm.rs.graphics.dot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class DOTNode {

	String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public static void setUp(File f, String name) throws IOException{
		new FileWriter(f).write("digraph "+name+" {");
	}
	
	public static void endFile(File f) throws IOException{
		new FileWriter(f).write("}");
	}
	
	public String toAttribute(DOTNode node){
		return this.getName()+" -> "+node.getName();
	}
	
	public String toTable(DOTNode node,int kardl, int kardr){
		return this.getName()+" -> "+node.getName()+"[headlabel=\""+kardl+"\", taillabel=\""+kardr+"\"]";
	}
}
