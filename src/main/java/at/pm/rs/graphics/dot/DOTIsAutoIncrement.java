package at.pm.rs.graphics.dot;

public class DOTIsAutoIncrement extends DOTDecorator {
	
	public DOTIsAutoIncrement(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return node.getName()+": is AUTO INCREMENT";
	}

}
