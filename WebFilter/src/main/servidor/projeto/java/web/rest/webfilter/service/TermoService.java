package projeto.java.web.rest.webfilter.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.java.web.rest.webfilter.dominio.Termo;
import projeto.java.web.rest.webfilter.repository.TermoRepository;

@Service
public class TermoService implements Serializable {

	private static final long serialVersionUID = 7477402140549794385L;
	@Autowired
	private TermoRepository termoRepository;
	
	public Termo salvar(Termo termo) {
		return termoRepository.save(termo);
	}
}
