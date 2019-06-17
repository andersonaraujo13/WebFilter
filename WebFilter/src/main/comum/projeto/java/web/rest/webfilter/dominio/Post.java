package projeto.java.web.rest.webfilter.dominio;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "comum", name = "post")
public class Post implements Serializable {
	
	private static final long serialVersionUID = -8727479041004215972L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_post", unique = true, nullable = false)
	private int idPost;
	
	@Column(name = "texto")
	private String texto;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@Column(name = "data_postagem")
	private Date dataPostagem;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "identificador_plataforma")
	private String identificadorPlataforma;
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIdentificadorPlataforma() {
		return identificadorPlataforma;
	}
	public void setIdentificadorPlataforma(String identificadorPlataforma) {
		this.identificadorPlataforma = identificadorPlataforma;
	}
}
