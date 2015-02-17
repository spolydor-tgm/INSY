package at.pm.rs.graphics.html;


public class ForeignKey extends HTMLDecorator {
	
	public ForeignKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}
	
	@Override
	public String getTag() {		
		return "<u class=\"dashed\">"+htmltag.getTag()+"</u>";
	}

}
