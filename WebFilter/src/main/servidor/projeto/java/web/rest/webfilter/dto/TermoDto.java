package projeto.java.web.rest.webfilter.dto;

import java.io.Serializable;

public class TermoDto implements Serializable{

	private static final long serialVersionUID = 5837847336412561941L;
	private Integer total;
	private String termo;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getTermo() {
		return termo;
	}
	public void setTermo(String termo) {
		this.termo = termo;
	}

}
