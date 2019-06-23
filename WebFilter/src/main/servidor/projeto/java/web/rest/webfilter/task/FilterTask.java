package projeto.java.web.rest.webfilter.task;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import projeto.java.web.rest.webfilter.dominio.Credencial;
import projeto.java.web.rest.webfilter.plataform.strategy.PlataformStrategy;
import projeto.java.web.rest.webfilter.plataform.strategy.TwitterStrategy;
import projeto.java.web.rest.webfilter.repository.CredenciaisRepository;

@Component
public class FilterTask {

	@Autowired
	private CredenciaisRepository credenciaisRepository;
	@Autowired
	private TwitterStrategy twitterStrategy;

	private static final Logger log = LoggerFactory.getLogger(FilterTask.class);

	@Scheduled(fixedRate = 1200000)
	public void run() {
		List<Credencial> listCredenciais = credenciaisRepository.findAll();

		for (Credencial credencial : listCredenciais) {
			try {
				PlataformStrategy plataform = getStrategy(credencial);
				plataform.feeding(credencial);
			} catch (OperationNotSupportedException e) {
				log.info("Não foi possível inatanciar a plataforma " + credencial.getPlatforma().valor);
				e.printStackTrace();
			}
		}
	}

	public PlataformStrategy getStrategy(Credencial credencial) throws OperationNotSupportedException {
		switch (credencial.getPlatforma()) {
		case TWITTER:
			return twitterStrategy;
		default:
			throw new OperationNotSupportedException("A credencia não possui uma estrategia implementada");
		}
	}
}
