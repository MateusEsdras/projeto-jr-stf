package com.jr.stf.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jr.stf.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

	@Transactional
	@Query(value="SELECT * FROM Autor", nativeQuery=true)
	List<Autor> findAllAutores();
	
	@Transactional(readOnly=true)
	Autor findByEmail(String email);
}
