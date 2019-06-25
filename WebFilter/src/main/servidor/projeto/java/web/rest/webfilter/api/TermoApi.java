package projeto.java.web.rest.webfilter.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projeto.java.web.rest.webfilter.dto.TermoDto;
import projeto.java.web.rest.webfilter.service.TermoService;

@RestController
@RequestMapping(value = "/termo")
public class TermoApi {

	@Autowired
	private TermoService termoService;
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TermoDto>> listTermos(
			@RequestParam(value="data_inicio", defaultValue = "") String dataInicio, 
			@RequestParam(value="data_fim", defaultValue = "") String dataFim) {
		return termoService.findByDate(dataInicio, dataFim);
	}
}
