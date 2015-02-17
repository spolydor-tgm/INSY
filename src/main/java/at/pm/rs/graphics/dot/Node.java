package at.pm.rs.graphics.dot;

import at.pm.rs.connection.SetOfData;

public class Node extends DOTNode {

	public Node(SetOfData parent,  String name, String specname) {
		this.name = name;
		this.parent = parent;
		this.specname = specname;
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
