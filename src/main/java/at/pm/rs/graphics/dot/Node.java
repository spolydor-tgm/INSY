package at.pm.rs.graphics.dot;

import at.pm.rs.connection.SetOfData;

/**
 * The actual Element, sets up the basic parts of a DOTNode and for getting
 * decorated.
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public class Node extends DOTNode {

	/**
	 * Creates a Node, the basic representation of a Node in .dot format. It
	 * sets its parent to get informations like if its a primary key, ...
	 * 
	 * @param parent
	 *            The parent of the node or the SetOfData pendant to the node,
	 *            necessary for extra information
	 * @param name
	 *            the name with which the node will be displayed, in case this
	 *            name will be modified by decorators
	 * @param specname
	 *            the name used to avoid duplicates
	 */
	public Node(SetOfData parent, String name, String specname) {
		this.name = name;
		this.parent = parent;
		this.specname = specname;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public SetOfData getParent() {
//		return parent;
//	}
//
//	public void setParent(SetOfData parent) {
//		this.parent = parent;
//	}

}
