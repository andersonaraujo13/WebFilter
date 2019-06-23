package projeto.java.web.rest.webfilter.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "configuracao", name = "credenciais")
public class Credencial implements Serializable {

	private static final long serialVersionUID = 3080177198805987917L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_credenciais", unique = true, nullable = false)
	private Integer idCredenciais;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "plataforma")
	private Plataforma platforma;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "credencial")
	private List<Parametro> parametros;

	public Integer getIdCredenciais() {
		return idCredenciais;
	}

	public void setIdCredenciais(Integer idCredenciais) {
		this.idCredenciais = idCredenciais;
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

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}
	
}
