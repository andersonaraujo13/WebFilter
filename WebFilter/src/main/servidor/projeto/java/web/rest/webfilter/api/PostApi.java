package projeto.java.web.rest.webfilter.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projeto.java.web.rest.webfilter.dominio.Post;
import projeto.java.web.rest.webfilter.service.PostService;

@RestController
@RequestMapping(value = "/post")
public class PostApi {
	@Autowired
	private PostService postservice;
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> listTermos(
			@RequestParam(value="data_inicio", defaultValue = "") String dataInicio, 
			@RequestParam(value="data_fim", defaultValue = "") String dataFim,
			@RequestParam(value="termo", defaultValue = "")String termo) {
		return postservice.getallByTermoAndDataInicioAndDataFim(termo, dataInicio, dataFim);
	}
}