package projeto.java.web.rest.webfilter.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(schema = "configuracao", name = "parametro")
public class Parametro implements Serializable {
	
	private static final long serialVersionUID = -5451247484548335431L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_parametro", unique = true, nullable = false)
	private int idParametro;
	
	@Column(name = "key")
	private String key;
	
	@Column(name = "value")
	private String value;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_credenciais")
	private Credencial credencial;

	public int getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

}
