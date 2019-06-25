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

import projeto.java.web.rest.webfilter.dominio.Termo;
import projeto.java.web.rest.webfilter.dto.TermoDto;
import projeto.java.web.rest.webfilter.repository.TermoRepository;

@Service
public class TermoService implements Serializable {

	private static final long serialVersionUID = 7477402140549794385L;
	@Autowired
	private TermoRepository termoRepository;
	
	public Termo salvar(Termo termo) {
		return termoRepository.save(termo);
	}

	public ResponseEntity<List<TermoDto>> findByDate(String dataInicio, String dataFim) {
		
		if(dataInicio.isEmpty() || dataFim.isEmpty()) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date inicio = format.parse(dataInicio);
			Date fim = format.parse(dataFim);
			
			List<TermoDto> termos = termoRepository.findAllByDate(inicio, fim);
			return new ResponseEntity<>(termos, HttpStatus.OK);
		} catch (ParseException e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}
}
