package at.pm.rs.graphics;

public class PrimaryKey extends HTMLDecorator {

	public PrimaryKey(HTMLTag htmltag) {
		this.htmltag = htmltag;
	}

	@Override
	public String getTag() {
		return "<u>"+htmltag.tag+"</u>";
	}
}
