package at.pm.rs.graphics.dot;

/**
 * The decorator based on a DOTNode, underlining the given name at its getter to
 * decorate it like a primary key should look
 * 
 * @author Patrick Malik
 * @version 20150218
 */
public class DOTPrimaryKey extends DOTDecorator {

	/**
	 * Creates a DOTPrimaryKey object based on a given node.
	 * 
	 * @param node the node to be decorated
	 */
	public DOTPrimaryKey(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return "<u>" + node.getName() + "</u>";
	}
}
