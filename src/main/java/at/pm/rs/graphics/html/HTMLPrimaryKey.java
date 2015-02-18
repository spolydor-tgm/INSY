package at.pm.rs.graphics.html;


/**
 * Decorates the HTMLTag with the underlined tag as a primary key.
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public class HTMLPrimaryKey extends HTMLDecorator {

	/**
	 * Creates an HTMLPrimaryKey object based on a htmltag.
	 * 
	 * @param htmltag the htmltag to use as base
	 */
	public HTMLPrimaryKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}

	@Override
	public String getTag() {
		return "<u>"+htmltag.getTag()+"</u>";
	}
}
