package projeto.java.web.rest.webfilter.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "configuracao", name = "credenciais")
public class Credenciais implements Serializable {

	private static final long serialVersionUID = 3080177198805987917L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_credenciais", unique = true, nullable = false)
	private Integer idCredenciais;
	
	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "api_secret_key")
	private String apiSecretKey;
	
	@Column(name = "aceess_token")
	private String accessToken;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "plataforma")
	private Plataforma platforma;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "senha")
	private String senha;

	public Integer getIdCredenciais() {
		return idCredenciais;
	}

	public void setIdCredenciais(Integer idCredenciais) {
		this.idCredenciais = idCredenciais;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecretKey() {
		return apiSecretKey;
	}

	public void setApiSecretKey(String apiSecretKey) {
		this.apiSecretKey = apiSecretKey;
	}

	public Plataforma getPlatforma() {
		return platforma;
	}

	public void setPlatforma(Plataforma platforma) {
		this.platforma = platforma;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
