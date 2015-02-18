package at.pm.rs.graphics.html;


/**
 * Decorates the HTMLTag with the underlined (dashed) tag.
 * 
 * @author Patrick Malik
 * @version 20150218
 *
 */
public class HTMLForeignKey extends HTMLDecorator {
	
	/**
	 * Creates an HTMLForeignKey object using the given htmltag as base.
	 * 
	 * @param htmltag the given htmltag to decorate.
	 */
	public HTMLForeignKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}
	
	@Override
	public String getTag() {		
		return "<u class=\"dashed\">"+htmltag.getTag()+"</u>";
	}

}
