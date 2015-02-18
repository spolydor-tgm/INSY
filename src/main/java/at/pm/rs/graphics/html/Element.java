package at.pm.rs.graphics.html;


/**
 * This class provides the basic object for a HTMLTag.
 * 
 * @author Patrick Malik
 * @version 20150215
 *
 */
public class Element extends HTMLTag{

	/**
	 * Creates an Element object and sets the tag to the wildcard $tag
	 */
	public Element(){
		this.setTag("$tag");
	}
}
