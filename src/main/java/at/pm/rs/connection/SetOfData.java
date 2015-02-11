package at.pm.rs.connection;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class SetOfData {

	private String[] name;
	private String[] type;
	private boolean[] pk;
	private String[] fk;
	private boolean[] autoincrement;
	private boolean[] isNullable;

	public SetOfData(int size) {
		type = new String[size];
		pk = new boolean[size];
		fk = new String[size];
		name = new String[size];
		autoincrement = new boolean[size];
		isNullable = new boolean[size];
	}

	public String[] getType() {
		return type;
	}

	public void setType(String type, int position) {
		this.type[position] = type;
	}

	public boolean[] getIsPk() {
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

	public String[] getName() {
		return name;
	}

	public void setName(String name, int position) {
		this.name[position] = name;
	}

	public boolean[] getIsAutoincremet() {
		return autoincrement;
	}

	public void setAutoincrement(boolean autoincrement, int position) {
		this.autoincrement[position] = autoincrement;
	}

	public boolean[] getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(boolean notNull, int position) {
		this.isNullable[position] = notNull;
	}

	@Override
	public String toString() {
		String out = "" + '\n';
		for (int x = 0; x < type.length; x++) {
			out += name[x] + ": " + type[x];
			if (pk[x])
				out += " PK= " + pk[x];

			if (fk[x] != null)
				out += " FK= " + fk[x];

			if (autoincrement[x])
				out += " autoincrement= " + autoincrement[x];

			if (isNullable[x])
				out += " nullable= " + isNullable[x];

			out += '\n';
		}
		return out;
	}
}
