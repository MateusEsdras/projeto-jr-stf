package com.jr.stf.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jr.stf.domain.Autor;
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
	
	@Autowired
	private AutorService autorService;
	
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
			throw new DataIntegrityException("Ocorreu um erro por conta dos relacionamentos desta obra");
		}
	}
	
	public void addAutor(Integer idObra, Integer idAutor) {
		Autor autor = autorService.find(idAutor);
		Obra obra = find(idObra);
		obra.addAutor(autor);
		obraRepository.save(obra);
	}
	
	public void removeAutor(Integer idObra, Integer idAutor) {
		Autor autor = autorService.find(idAutor);
		Obra obra = find(idObra);
		obra.removeAutor(autor);
		obraRepository.save(obra);
		
		if(obra.getAutores().size() == 0) {
			delete(obra.getId());
		}
	}
}
