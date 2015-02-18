package at.pm.rs.connection;

/**
 * @author Stefan Polydor &lt;spolydor@student.tgm.ac.at&gt;
 * @version 28.01.15
 */

public class SetOfData {

	private String name;
	private String type;
	private boolean pk;
	private ForeignKey fk;
	private boolean autoincrement;
	private boolean isNullable;
	private boolean isUnique;

	/**
	 * Is Saving all neccessary informations
	 * Sets the pk attribute default to false
	 * Sets the isNullable default to false
	 */
	public SetOfData() {
		pk = false;
		isNullable = false;
		isUnique = false;
	}

	/**
	 *
	 * @return String as Type of the attribute in the db
	 */
	public String getType() {
		return type;
	}

	/**
	 *
	 * @param type which should be saved from the db
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @param unique which should be saved from the db (true if is unique and false if it is not)
	 */
	public void setUnique(boolean unique) {
		this.isUnique = unique;
	}

	/**
	 *
	 * @return boolean true, if the attribute is a primary Key
	 */
	public boolean getIsUnique() {
		return isUnique;
	}

	/**
	 *
	 * @return boolean true, if the attribute is a primary Key
	 */
	public boolean getIsPk() {
		return pk;
	}

	/**
	 *
	 * @param pk saves true if the attribute is a primary Key
	 */
	public void setPk(boolean pk) {
		this.pk = pk;
	}

	/**
	 *
	 * @return ForeignKey if the attribute is a Foreign Key. example: Tablename.Columnname
	 */
	public ForeignKey getFk() {
		return fk;
	}

	/**
	 *
	 * @param fk Sets the Tablename.Columnname for the Foreign Key
	 */
	public void setFk(ForeignKey fk) {
		this.fk = fk;
	}

	/**
	 *
	 * @return String name of the attribute
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name sets the name of the attribute
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return boolean true if the attribute is autoincrement
	 */
	public boolean getIsAutoincrement() {
		return autoincrement;
	}

	/**
	 *
	 * @param autoincrement sets true if the attribute in the db is autoincrementable
	 */
	public void setAutoincrement(boolean autoincrement) {
		this.autoincrement = autoincrement;
	}

	/**
	 *
	 * @return boolean true, if the attribute can be NULL
	 */
	public boolean getIsNullable() {
		return isNullable;
	}

	/**
	 *
	 * @param notNull if the attribute in the db can be a NULL
	 */
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

		if (isUnique)
			out += " unique= " + isUnique;

		return out;
	}
}
