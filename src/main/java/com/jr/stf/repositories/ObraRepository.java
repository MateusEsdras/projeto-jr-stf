package com.jr.stf.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jr.stf.domain.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer>{

	@Transactional
	@Query(value="SELECT * FROM Obra", nativeQuery=true)
	List<Obra> findAllObras();
}
