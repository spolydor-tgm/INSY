package at.pm.rs.graphics.dot;

import at.pm.rs.connection.SetOfData;

/**
 * The abstract class providing the basic functions of a DOTNode
 * 
 * The name to be displayed is saved in the String attribute name. This is the
 * name that will be decorated.
 * 
 * The attribute parent saves the {@link SetOfData} pendant of the Node. This is
 * necessary to gather extra information.
 * 
 * The attribute specname is used to prevent duplicates, this name is not to be
 * displayed, it should be only used for identifying purposes. (Either a name
 * with a number, just a number or something else similar)
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
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
