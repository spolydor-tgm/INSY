package java.rs.at.pm.connection;

public class SetOfData {

	private String type;
	private boolean pk;
	private String fk;
	private String tableName;
	private String name;
	private boolean autoincremet;
	private boolean notNull;
	
	public SetOfData(String tableName, String name, String type, boolean pk, String fk, boolean notNull, boolean autoincrement){
		this.tableName = tableName;
		this.name = name;
		this.type = type;
		this.pk = pk;
		this.fk = fk;
		this.notNull = notNull;
		this.autoincremet = autoincrement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAutoincremet() {
		return autoincremet;
	}

	public void setAutoincremet(boolean autoincremet) {
		this.autoincremet = autoincremet;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	
	
}
