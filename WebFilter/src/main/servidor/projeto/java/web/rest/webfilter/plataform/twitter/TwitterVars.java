package projeto.java.web.rest.webfilter.plataform.twitter;

public enum TwitterVars {
	q("q"), result_type("result_type"), until("until"), count("count"), max_per_feeding("max_per_feeding"),
	since_id("since_id"), max_id("max_id"), bearer("Bearer"), content_type("Content_Type"),
	authorization("Authorization"), body("body"), url("url"), api_key("api_key"), api_secret("api_Secret");

	private String valor;

	TwitterVars(String valor) {
		this.valor = valor;
	}

	public String valor() {
		return valor;
	}

}
