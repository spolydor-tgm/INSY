package at.pm.rs.graphics.dot;

public class DOTPrimaryKey extends DOTDecorator{
	
	public DOTPrimaryKey(DOTNode node) {
		this.node = node;
	}

	@Override
	public String getName() {
		return "<u>"+node.getName()+"</u>";
	}
}
