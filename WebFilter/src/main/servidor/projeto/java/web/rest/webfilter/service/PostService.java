package projeto.java.web.rest.webfilter.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.java.web.rest.webfilter.dominio.Post;
import projeto.java.web.rest.webfilter.repository.PostRepository;

@Service
public class PostService implements Serializable {
	
	private static final long serialVersionUID = 8743413696628340125L;
	@Autowired
	private PostRepository postRepository;
	
	public Post salvar(Post post) {
		return postRepository.save(post);
	}
}
