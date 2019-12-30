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
import com.jr.stf.services.exceptions.InvalidCpfException;
import com.jr.stf.services.exceptions.ObjectNotFoundException;
import com.jr.stf.services.validation.BR;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private ObraService obraService;
	
	public Autor find(Integer id) {
		Optional<Autor> obj = autorRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
	}
	
	public List<Autor> findAll(){
		return autorRepository.findAllAutores();
	}
	
	@Transactional
	public Autor insert(Autor obj) {
		obj.setId(null);
		if(BR.isValidCPF(obj.getCpf())) {
			obj = autorRepository.save(obj);
			obraRepository.saveAll(obj.getObras());
			return obj;
		} else {
			throw new InvalidCpfException("O CPF informado é inválido");
		}
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
	
	public void addObra(Integer idAutor, Integer idObra) {
		Obra obra = obraService.find(idObra);
		Autor autor = find(idAutor);
		obra.addAutor(autor);
		obraRepository.save(obra);
	}
	
	public void removeObra(Integer idAutor, Integer idObra) {
		Obra obra = obraService.find(idObra);
		Autor autor = find(idAutor);
		obra.removeAutor(autor);
		obraRepository.save(obra);
		
		if(obra.getAutores().size() == 0) {
			obraService.delete(obra.getId());
		}
	}
}
