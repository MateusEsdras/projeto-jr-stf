package com.jr.stf.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jr.stf.domain.Autor;
import com.jr.stf.repositories.AutorRepository;
import com.jr.stf.repositories.ObraRepository;
import com.jr.stf.services.exceptions.DataIntegrityException;
import com.jr.stf.services.exceptions.ObjectNotFoundException;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private ObraRepository obraRepository;
	
	public Autor find(Integer id) {
		Optional<Autor> obj = autorRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado!" + Autor.class.getName()));
	}
	
	public List<Autor> findAll(){
		return autorRepository.findAllAutores();
	}
	
	@Transactional
	public Autor insert(Autor obj) {
		obj.setId(null);
		obj = autorRepository.save(obj);
		obraRepository.saveAll(obj.getObras());
		return obj;
	}
	
	public Autor update(Autor obj) {
		obraRepository.saveAll(obj.getObras());
		return autorRepository.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			autorRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("O autor não pôde ser excluído pois o mesmo ainda possui obras cadastradas");
		}
	}
}
