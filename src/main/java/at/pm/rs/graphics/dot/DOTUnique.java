package at.pm.rs.graphics.dot;

/**
 * This class is used to Decorate a Node with the keyword "is UNIQUE"
 * 
 * @author Patrick Malik
 * @version 20150218
 */
public class DOTUnique extends DOTDecorator{
	
	/**
	 * Creates a DOTUnique object based on a given node.
	 * 
	 * @param node The node to use for decorating
	 */
	public DOTUnique(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return node.getName()+": is UNIQUE";
	}

}
