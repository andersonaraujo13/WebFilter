package projeto.java.web.rest.webfilter.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "comum", name = "termo")
public class Termo implements Serializable {

	private static final long serialVersionUID = 4993209998833628775L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_termo", unique = true, nullable = false)
	private int idTermo;
	
	@Column(name = "termo")
	private String termo;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@Column(name = "data_postagem_mili")
	private Long dataPostagemMili;
	
	@Column(name = "data_postagem")
	private Date dataPostagem;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_post")
	private Post post;
	
	public int getIdTermo() {
		return idTermo;
	}
	public void setIdTermo(int idTermo) {
		this.idTermo = idTermo;
	}
	public String getTermo() {
		return termo;
	}
	public void setTermo(String termo) {
		this.termo = termo;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Date getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	public Long getDataPostagemMili() {
		return dataPostagemMili;
	}
	public void setDataPostagemMili(Long dataPostagemMili) {
		this.dataPostagemMili = dataPostagemMili;
	}
}
