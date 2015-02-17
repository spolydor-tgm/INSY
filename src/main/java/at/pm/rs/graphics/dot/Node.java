package at.pm.rs.graphics.dot;

import at.pm.rs.connection.SetOfData;

public class Node {

	String name;
	SetOfData parent;
	
	public Node(String name, SetOfData parent) {
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SetOfData getParent() {
		return parent;
	}

	public void setParent(SetOfData parent) {
		this.parent = parent;
	}
	
	
}
