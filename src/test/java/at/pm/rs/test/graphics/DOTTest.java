package at.pm.rs.test.graphics;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.pm.rs.connection.SetOfData;
import at.pm.rs.graphics.dot.DOTDecorator;
import at.pm.rs.graphics.dot.DOTIsAutoIncrement;
import at.pm.rs.graphics.dot.DOTNode;
import at.pm.rs.graphics.dot.EntityConnection;
import at.pm.rs.graphics.dot.Node;

public class DOTTest {

	//Testing Decorator
	@Test
	public void TestGetAndSetSpecname() {
		DOTDecorator dd = new DOTIsAutoIncrement(new Node(null, "testnode", "test"));
		dd.setSpecname("test1");
		assertEquals("test1",dd.getSpecname());
	}
	
	//Testing DOTNode
	@Test
	public void TestGetAndSetParent(){
		DOTNode dn = new Node(null, "test", "test1");
		SetOfData sod = new SetOfData();
		sod.setName("name");
		dn.setParent(sod);
		assertEquals("name",dn.getParent().getName());	
	}
	
	@Test
	public void TestGetAndSetName(){
		DOTNode dn = new Node(null,"name","name1");
		dn.setName("test1");
		assertEquals("test1",dn.getName());
	}
	
	EntityConnection ec;
	
	@Before
	public void setUpEC(){
		ec = new EntityConnection("name", "leftNode", "rightNode", 'c', 'n');
	}
	
	//Testing EntityConnection
	@Test
	public void TestGetAndSetNameEntity(){
		ec.setName("test1");
		assertEquals("test1", ec.getName());
	}
	
	@Test
	public void TestGetAndSetLeftNode(){
		ec.setLeftNode("lefttest");
		assertEquals("lefttest", ec.getLeftNode());
	}
	
	@Test
	public void TestGetAndSetLeftCard(){
		ec.setLeftCard('t');
		assertEquals('t', ec.getLeftCard());
	}
	
	@Test
	public void TestGetAndSetRightNode(){
		ec.setRightNode("righttest");
		assertEquals("righttest", ec.getRightNode());
	}
	
	@Test
	public void TestGetAndSetRightCard(){
		ec.setRightCard('r');
		assertEquals('r', ec.getRightCard());
	}
	
	@Test
	public void TestCompareToFor0(){
		EntityConnection com1 = new EntityConnection("name", "leftNode", "rightNode", 'n', 'n');
		EntityConnection com2 = new EntityConnection("name", "leftNode", "rightNode", 'n', 'n');
		assertEquals(0, com1.compareTo(com2));
	}
	
	@Test
	public void TestCompareToForMinus1(){
		EntityConnection com1 = new EntityConnection("name", "leftNode", "rightNode", 'n', 'n');
		EntityConnection com2 = new EntityConnection("name", "leftNode2", "rightNode", 'n', 'n');
		assertEquals(-1, com1.compareTo(com2));
	}
	

}
