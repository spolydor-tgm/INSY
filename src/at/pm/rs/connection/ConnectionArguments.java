package at.pm.rs.connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Cares about all the Arguments that are needed to establish a connection and
 * collects them.
 * 
 * The Map stores all the arguments with their obvious names as keys.
 * 
 * keys: <li>hostname</li> 
 * 	     <li>username</li> 
 *       <li>password</li>
 *       <li>dbname</li>
 *       <li>dbms</li>
 * 
 * @author Patrick Malik
 * @version 141226
 *
 */
public class ConnectionArguments {
	Map<String, String> arguments;

	// TODO dbname wird gebraucht?
	/**
	 * Creates a ConnectionArguments-Object and sets all the necessary and given
	 * arguments.
	 * 
	 * @param hostname
	 *            the hostname to establish a connection
	 * @param username
	 *            the username to establish a connection
	 * @param pwd
	 *            the password needed to establish a connection
	 * @param dbname
	 *            the name of the db to work with
	 * @param dbms
	 *            the name of the dbms you want to connect with
	 */
	public ConnectionArguments(String hostname, String username, String pwd, String dbname, String dbms) {
		arguments = new HashMap<>();
		getArguments().put("hostname", hostname);
		getArguments().put("username", username);
		getArguments().put("password", pwd);
		getArguments().put("dbname", dbname);
		getArguments().put("dbms", dbms);

	}

	public Map<String, String> getArguments() {
		return arguments;
	}

	public void setArguments(Map<? extends String, ? extends String> arguments) {
		this.arguments = (Map<String, String>) arguments;
	}

	/**
	 * Gets the saved hostname from the map.
	 * 
	 * @return the value of key hostname.
	 */
	public String getHostname() {
		return getArguments().get("hostname");
	}

	/**
	 * Saves the new hostname in the arguments map
	 * 
	 * @param hostname
	 *            the new hostname
	 */
	public void setHostname(String hostname) {
		getArguments().put("hostname", hostname);
	}

	/**
	 * Gets the saved username from the map.
	 * 
	 * @return the value of key username
	 */
	public String getUsername() {
		return getArguments().get("username");
	}

	/**
	 * Saves the new username in the arguments map.
	 * 
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		getArguments().put("username", username);
	}

	/**
	 * Gets the saved password from the arguments map.
	 * 
	 * @return the value of key password
	 */
	public String getPwd() {
		return getArguments().get("password");
	}

	/**
	 * Saves the new Password in the arguments map.
	 * 
	 * @param pwd
	 *            the new password
	 */
	public void setPwd(String pwd) {
		getArguments().put("password", pwd);
	}
	
	public String getDBName(){
		return getArguments().get("dbname");
	}

	public void setDBName(String dbname){
		getArguments().put("dbname", dbname);
	}
	
	public String getDBMS(){
		return getArguments().get("dbms");
	}
	
	public void setDBMS(String dbms){
		getArguments().put("dbms", dbms);
	}
}
