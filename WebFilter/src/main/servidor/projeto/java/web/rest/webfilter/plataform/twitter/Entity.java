package projeto.java.web.rest.webfilter.plataform.twitter;

import java.io.Serializable;
import java.util.List;

public class Entity implements Serializable{
	
	private static final long serialVersionUID = -2191688592556004895L;
	private List<Hashtag> hashtags;
	private List<Mentions> user_mentions;
	public List<Hashtag> getHashtags() {
		return hashtags;
	}
	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	public List<Mentions> getUser_mentions() {
		return user_mentions;
	}
	public void setUser_mentions(List<Mentions> user_mentions) {
		this.user_mentions = user_mentions;
	}
	
}
