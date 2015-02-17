package at.pm.rs.graphics.dot;

import at.pm.rs.connection.SetOfData;

public abstract class DOTNode {

	String name;
	SetOfData parent;
	String specname;

	public SetOfData getParent() {
		return parent;
	}

	public void setParent(SetOfData parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecname() {
		return specname;
	}

	public void setSpecname(String realname) {
		this.specname = realname;
	}

}
