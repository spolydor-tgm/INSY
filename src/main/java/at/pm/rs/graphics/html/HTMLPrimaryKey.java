package at.pm.rs.graphics.html;


public class HTMLPrimaryKey extends HTMLDecorator {

	public HTMLPrimaryKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}

	@Override
	public String getTag() {
		return "<u>"+htmltag.getTag()+"</u>";
	}
}
