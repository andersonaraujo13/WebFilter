package projeto.java.web.rest.webfilter.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<List<Post>> getallByTermoAndDataInicioAndDataFim(String termo, String dataInicio, String dataFim) {
		
		if(termo.isEmpty() || dataFim.isEmpty() || dataInicio.isEmpty()) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date inicio = format.parse(dataInicio);
			Date fim = format.parse(dataFim);
			
			List<Post> post = postRepository.getallByTermoAndDataInicioAndDataFim(termo, inicio, fim);
			
			return new ResponseEntity<>(post, HttpStatus.OK);
		} catch (ParseException e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}
}
