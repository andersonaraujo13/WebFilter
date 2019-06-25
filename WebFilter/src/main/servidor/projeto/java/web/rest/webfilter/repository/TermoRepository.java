package projeto.java.web.rest.webfilter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto.java.web.rest.webfilter.dominio.Termo;
import projeto.java.web.rest.webfilter.dto.TermoDto;

public interface TermoRepository extends JpaRepository<Termo, Integer>{

	@Query(
			nativeQuery = true, 
			value = "select upper(termo) as termo, count(id_termo) as total \r\n" + 
					"	from comum.termo \r\n" + 
					"	where \r\n" + 
					"	length(termo) > 3 \r\n" + 
					"	and termo not ilike '%#%' \r\n" + 
					"	and termo not ilike '%@%' \r\n" + 
					"	and termo not ilike '%https%'\r\n" + 
					"	and termo not ilike '%http%'\r\n" + 
					"	and :inicio < data_postagem\r\n" + 
					"	and :fim > data_postagem\r\n" + 
					"	group by upper(termo) \r\n" + 
					"	order by total desc\r\n" + 
					"	limit 10;"
			)
	public List<TermoDto> findAllByDate(@Param("inicio")Date inicio, @Param("fim")Date fim);

}
