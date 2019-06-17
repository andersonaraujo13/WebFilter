package projeto.java.web.rest.webfilter.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TermoApi {

	@GetMapping(value = "/teste")
	public String teste() {
		return "teste";
	}
}
