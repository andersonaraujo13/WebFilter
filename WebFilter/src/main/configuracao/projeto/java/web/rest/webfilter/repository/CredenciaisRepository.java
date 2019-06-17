package projeto.java.web.rest.webfilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.web.rest.webfilter.dominio.Credenciais;

public interface CredenciaisRepository extends JpaRepository<Credenciais, Integer>{

}
