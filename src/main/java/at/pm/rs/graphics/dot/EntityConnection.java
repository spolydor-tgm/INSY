package at.pm.rs.graphics.dot;

public class EntityConnection {

	String name;
	
	//Nodes müssen specname sein!
	String leftNode;
	char leftCard;
	
	String rightNode;
	char rightCard;
	
	public EntityConnection(String name, String leftNode, String rightNode, char leftCard, char rightCard) {
		this.name = name;
		this.leftNode = leftNode;
		this.leftCard = leftCard;
		this.rightNode = rightNode;
		this.rightCard = rightCard;
	}
	
	
	public String toString(){
		return leftNode+" -- "+this.name+"[label=\""+this.leftCard+"\"];\n"+this.name+" -- "+rightNode+"[label=\""+this.rightCard+"\"];";
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
	
}
