package at.pm.rs.connection;

public class SetOfData {

	private String[] type;
	private boolean[] pk;
	private String[] fk;
	private String tableName;
	private String[] name;
	private boolean[] autoincrement;
	private boolean[] notNull;

	public SetOfData(int size) {
		type = new String[size];
		pk = new boolean[size];
		fk = new String[size];
		name = new String[size];
		autoincrement = new boolean[size];
		notNull = new boolean[size];
	}

	public String[] getType() {
		return type;
	}

	public void setType(String type, int position) {
		this.type[position] = type;
	}

	public boolean[] isPk() {
		return pk;
	}

	public void setPk(boolean pk, int position) {
		this.pk[position] = pk;
	}

	public String[] getFk() {
		return fk;
	}

	public void setFk(String fk, int position) {
		this.fk[position] = fk;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String name, int position) {
		this.name[position] = name;
	}

	public boolean[] isAutoincremet() {
		return autoincrement;
	}

	public void setAutoincrement(boolean autoincrement, int position) {
		this.autoincrement[position] = autoincrement;
	}

	public boolean[] isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull, int position) {
		this.notNull[position] = notNull;
	}

	@Override
	public String toString() {
		String out = tableName + '\n';
		for (int x = 0; x < type.length; x++) {
			out += name[x] + ": " + type[x];
			if (pk[x])
				out += " PK= " + pk[x];

			if (fk[x] != null)
				out += " FK= " + fk[x];

			if (autoincrement[x])
				out += " autoincrement= " + autoincrement[x];

			if (notNull[x])
				out += " nullable= " + notNull[x];

			out += '\n';
		}
		return out;
	}
}
