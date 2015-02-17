package at.pm.rs.graphics.html;


public class HTMLForeignKey extends HTMLDecorator {
	
	public HTMLForeignKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}
	
	@Override
	public String getTag() {		
		return "<u class=\"dashed\">"+htmltag.getTag()+"</u>";
	}

}
