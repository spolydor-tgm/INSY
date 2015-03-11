package at.pm.rs.graphics.dot;

/**
 * The Connection between to Entities (tables).
 * 
 * This class is meant to save the necessary data for a connection between them.
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public class EntityConnection implements Comparable<EntityConnection> {

	String name;

	// Nodes muessen specname sein!
	String leftNode;
	char leftCard;

	String rightNode;
	char rightCard;

	/**
	 * Creates an EntityConnection object, used for connecting related tables of
	 * a database.
	 * 
	 * @param name
	 *            the name as which the diamond (relation) gets saved and used,
	 *            the name is not to be displayed
	 * @param leftNode
	 *            the name of the node to the left of the diamond
	 * @param rightNode
	 *            the name of the node to the right of the diamond
	 * @param leftCard
	 *            the cardinality to the left of the diamond
	 * @param rightCard
	 *            the cardinality to the right of the diamond
	 */
	public EntityConnection(String name, String leftNode, String rightNode, char leftCard, char rightCard) {
		this.name = name;
		this.leftNode = leftNode;
		this.leftCard = leftCard;
		this.rightNode = rightNode;
		this.rightCard = rightCard;
	}

	/**
	 * This toString() method returns a String that can be used for a .dot file
	 * in a graph (not digraph!) it connects the two nodes
	 * 
	 * @return the to dot formatted String representation
	 */
	public String toString() {
		return leftNode + " -- " + this.name + "[label=\"" + this.leftCard + "\"];\n" + this.name + " -- " + rightNode + "[label=\"" + this.rightCard + "\"];";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(String leftNode) {
		this.leftNode = leftNode;
	}

	public char getLeftCard() {
		return leftCard;
	}

	public void setLeftCard(char leftCard) {
		this.leftCard = leftCard;
	}

	public String getRightNode() {
		return rightNode;
	}

	public void setRightNode(String rightNode) {
		this.rightNode = rightNode;
	}

	public char getRightCard() {
		return rightCard;
	}

	public void setRightCard(char rightCard) {
		this.rightCard = rightCard;
	}

	/**
	 * This method compares two EntityConnection objects and returns 0 if they
	 * are equal, if they are not, -1 is returned, this compareTo method is used
	 * as equals
	 */
	@Override
	public int compareTo(EntityConnection o) {

		if (this.getLeftCard() == o.getLeftCard() && this.getRightCard() == o.getRightCard() && this.getLeftNode().equals(o.getLeftNode()) && this.getRightNode().equals(o.getRightNode()))
			return 0;

		return -1;
	}

}
