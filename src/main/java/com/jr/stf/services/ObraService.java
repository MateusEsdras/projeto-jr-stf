package com.jr.stf.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jr.stf.domain.Obra;
import com.jr.stf.repositories.AutorRepository;
import com.jr.stf.repositories.ObraRepository;
import com.jr.stf.services.exceptions.DataIntegrityException;
import com.jr.stf.services.exceptions.ObjectNotFoundException;

@Service
public class ObraService {

	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	public Obra find(Integer id) {
		Optional<Obra> obj = obraRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Obra não encontrada!" + Obra.class.getName()));
	}
	
	public List<Obra> findAll(){
		return obraRepository.findAllObras();
	}
	
	@Transactional
	public Obra insert(Obra obj) {
		autorRepository.saveAll(obj.getAutores());
		obj = obraRepository.save(obj);
		return obj;
	}
	
	public Obra update(Obra obj) {
		Obra oldObj = find(obj.getId());
		obj.getAutores().addAll(oldObj.getAutores());
		autorRepository.saveAll(obj.getAutores());
		return obraRepository.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			obraRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("RELACIONAMENTOS!");
		}
	}
}
