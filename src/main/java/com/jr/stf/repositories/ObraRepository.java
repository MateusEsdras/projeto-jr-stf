package com.jr.stf.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jr.stf.domain.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer>{

}
