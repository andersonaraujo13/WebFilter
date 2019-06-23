package projeto.java.web.rest.webfilter.plataform.twitter;

import java.io.Serializable;

public class Tweet implements Serializable {
	private static final long serialVersionUID = -7044360851101914451L;
	private String created_at;
	private Long id;
	private String id_str;
	private String text;
	private Entity entities;
	
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getId_str() {
		return id_str;
	}
	public void setId_str(String id_str) {
		this.id_str = id_str;
	}
	public String getText() {
		return text;
	}
	public Entity getEntities() {
		return entities;
	}
	public void setEntities(Entity entities) {
		this.entities = entities;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
