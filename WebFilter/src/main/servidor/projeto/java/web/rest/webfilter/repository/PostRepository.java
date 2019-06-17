package projeto.java.web.rest.webfilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.web.rest.webfilter.dominio.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
