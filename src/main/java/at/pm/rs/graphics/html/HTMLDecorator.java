package at.pm.rs.graphics.html;


/**
 * The Decorator class needed for decorating the HTMLTag.
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public abstract class HTMLDecorator extends HTMLTag{
	
	HTMLTag htmltag;

	public abstract String getTag();
}
