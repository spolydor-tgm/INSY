package at.pm.rs.graphics.dot;

/**
 * This class is used to Decorate a Node with the keyword "is NOT NULL"
 * 
 * @author Patrick Malik
 * @version 20150218
 */
public class DOTIsNotNull extends DOTDecorator{
	
	/**
	 * Creates a DOTIsNotNull object based on a given node.
	 * 
	 * @param node The node to use for decorating
	 */
	public DOTIsNotNull(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return node.getName()+": is NOT NULL";
	}

}
