package projeto.java.web.rest.webfilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.web.rest.webfilter.dominio.Credencial;

public interface CredenciaisRepository extends JpaRepository<Credencial, Integer>{

}
