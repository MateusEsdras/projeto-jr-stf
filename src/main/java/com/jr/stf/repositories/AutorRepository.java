package com.jr.stf.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jr.stf.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

}
