package at.pm.rs.graphics.html;


public class PrimaryKey extends HTMLDecorator {

	public PrimaryKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}

	@Override
	public String getTag() {
		return "<u>"+htmltag.getTag()+"</u>";
	}
}
