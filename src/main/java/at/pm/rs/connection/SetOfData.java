package at.pm.rs.connection;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class SetOfData {

	private String name;
	private String type;
	private boolean pk;
	private String fk;
	private boolean autoincrement;
	private boolean isNullable;

	public SetOfData() {
		pk = false;
		isNullable = false;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getIsPk() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsAutoincremet() {
		return autoincrement;
	}

	public void setAutoincrement(boolean autoincrement) {
		this.autoincrement = autoincrement;
	}

	public boolean getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(boolean notNull) {
		this.isNullable = notNull;
	}

	@Override
	public String toString() {
		String out = "" + '\n';
		out += name + ": " + type;
		if (pk)
			out += " PK= " + pk;

		if (fk != null)
			out += " FK= " + fk;

		if (autoincrement)
			out += " autoincrement= " + autoincrement;

		if (isNullable)
			out += " nullable= " + isNullable;
		return out;
	}
}
