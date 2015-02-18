package at.pm.rs.graphics.dot;

/**
 * The decorator abstract class, used to override the necessary getters and
 * setters with asking for their compositions attributes
 * 
 * @author Patrick Malik
 * @version 20150218
 */
public abstract class DOTDecorator extends DOTNode {

	DOTNode node;

	public abstract String getName();

	public String getSpecname() {
		return node.getSpecname();
	}

	public void setSpecname(String specname) {
		node.setSpecname(specname);
	}

}
