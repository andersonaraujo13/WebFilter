package projeto.java.web.rest.webfilter.plataform.twitter;

import java.io.Serializable;

public class Mentions implements Serializable {

	private static final long serialVersionUID = 9159656114470676246L;
	private String screen_name;
	private String name;
	
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}