package projeto.java.web.rest.webfilter.plataform.twitter;

import java.io.Serializable;

public class Auth implements Serializable{
	
	private static final long serialVersionUID = 5957240302675028584L;
	private String token_type;
	private String access_token;
	
	public String getToken_type() {
		return token_type;
	}
	
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	 
}
