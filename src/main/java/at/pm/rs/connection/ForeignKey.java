package at.pm.rs.connection;

public class ForeignKey {

	String refTable;
	String refAttribute;
	
	public ForeignKey(String refTable, String refAttribute) {
		this.refTable = refTable;
		this.refAttribute = refAttribute;
	}
	
	public String toString(){
		return this.getRefTable()+"."+this.getRefAttribute();
	}

	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

	public String getRefAttribute() {
		return refAttribute;
	}

	public void setRefAttribute(String refAttribute) {
		this.refAttribute = refAttribute;
	}
	
}
