package projeto.java.web.rest.webfilter.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import projeto.java.web.rest.webfilter.dominio.Credenciais;
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
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void run() {
		List<Credenciais> listCredenciais = credenciaisRepository.findAll();

		for (Credenciais credencial : listCredenciais) {
			try {
				PlataformStrategy plataform = getStrategy(credencial);
				plataform.feeding(credencial);
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
		}

		log.info("The time is now {}", dateFormat.format(new Date()));
	}

	public PlataformStrategy getStrategy(Credenciais credencial) throws OperationNotSupportedException {
		switch (credencial.getPlatforma()) {
		case TWITTER:
			return twitterStrategy;
		default:
			throw new OperationNotSupportedException("A credencia não possui uma estrategia implementada");
		}
	}
}
