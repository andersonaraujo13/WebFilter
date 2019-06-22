package projeto.java.web.rest.webfilter.plataform.twitter;

import java.io.Serializable;

public class Hashtag implements Serializable {
	private static final long serialVersionUID = 924079835703879605L;
	private String text;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
