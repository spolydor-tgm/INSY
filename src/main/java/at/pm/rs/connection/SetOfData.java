package at.pm.rs.connection;

import java.util.ArrayList;

public class SetOfData {

	private ArrayList<String> type = new ArrayList<>();
	private ArrayList<Boolean> pk = new ArrayList<>();
	private ArrayList<String> fk = new ArrayList<>();
	private String tableName;
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<Boolean> autoincremet = new ArrayList<>();
	private ArrayList<Boolean> notNull = new ArrayList<>();

	public ArrayList<String> getType() {
		return type;
	}

	public void setType(String type) {
		this.type.add(type);
	}

	public ArrayList<Boolean> isPk() {
		return pk;
	}

	public void setPk(boolean pk, int position) {
		this.pk.add(position, pk);
	}

	public ArrayList<String> getFk() {
		return fk;
	}

	public void setFk(String fk, int position) {
		this.fk.add(position, fk);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<String> getName() {
		return name;
	}

	public void setName(String name) {
		this.name.add(name);
	}

	public ArrayList<Boolean> isAutoincremet() {
		return autoincremet;
	}

	public void setAutoincremet(boolean autoincremet) {
		this.autoincremet.add(autoincremet);
	}

	public ArrayList<Boolean> isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull.add(notNull);
	}

	@Override
	public String toString() {
		for (int x = 0; x < type.size(); x++);
		return "";
	}
}
