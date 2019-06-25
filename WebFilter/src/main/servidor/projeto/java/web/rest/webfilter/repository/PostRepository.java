package projeto.java.web.rest.webfilter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projeto.java.web.rest.webfilter.dominio.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query(value = 	  "select p from Post p "
					+ "where p.dataPostagem > :inicio "
					+ "and p.dataPostagem < :fim "
					+ "and upper(p.texto ) LIKE CONCAT('%',upper(:termo),'%') ")
	public List<Post> getallByTermoAndDataInicioAndDataFim(String termo, Date inicio, Date fim);

}
