package at.pm.rs.graphics.dot;

public abstract class DOTDecorator extends DOTNode{

	DOTNode node;
	
	public abstract String getName();

	public String getSpecname() {
		return node.getSpecname();
	}

	public void setSpecname(String specname) {
		node.setSpecname(specname);
	}
	
}
