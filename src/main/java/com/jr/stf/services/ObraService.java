package com.jr.stf.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jr.stf.domain.Obra;
import com.jr.stf.repositories.AutorRepository;
import com.jr.stf.repositories.ObraRepository;
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
	
	public Obra insert(Obra obj) {
		obj.setId(null);
		obj.setNome(obj.getNome());
		obj.setDescricao(obj.getDescricao());
		obj.setPublicacao(obj.getPublicacao());
		obj.setExposicao(obj.getExposicao());
		obj.setAutores(obj.getAutores());
		
		//autorRepository.saveAll(obj.getAutores());
		obj = obraRepository.save(obj);
		return obj;
	}
}
