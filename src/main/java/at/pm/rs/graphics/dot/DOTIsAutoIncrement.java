package at.pm.rs.graphics.dot;

/**
 * This class is used to Decorate a Node with the keyword "is AUTO INCREMENT"
 * 
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public class DOTIsAutoIncrement extends DOTDecorator {
	
	/**
	 * Creates a DOTIsAutoIncrement object based on a given Node 
	 * 
	 * @param node The already existing node to use
	 */
	public DOTIsAutoIncrement(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return node.getName()+": is AUTO INCREMENT";
	}

}
