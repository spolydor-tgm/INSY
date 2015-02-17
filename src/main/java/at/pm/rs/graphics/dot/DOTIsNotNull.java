package at.pm.rs.graphics.dot;

public class DOTIsNotNull extends DOTDecorator{
	
	public DOTIsNotNull(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return node.getName()+": is NOT NULL";
	}

}
