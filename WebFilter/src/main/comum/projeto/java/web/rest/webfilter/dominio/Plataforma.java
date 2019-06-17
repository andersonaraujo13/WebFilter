package projeto.java.web.rest.webfilter.dominio;

public enum Plataforma {
	TWITTER("TWITTER");
	 
    public String valor;
    
    Plataforma(String valor) {
        this.valor = valor;
    }
    
    public String valor() {
    	return valor;
    }
}
