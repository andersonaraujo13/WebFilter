package projeto.java.web.rest.webfilter.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.java.web.rest.webfilter.dto.TermoDto;

@RestController
@RequestMapping(value = "/termo")
public class TermoApi {

	@GetMapping(value = "/list")
	public List<TermoDto> listTermos() {
		return null;
	}
}
