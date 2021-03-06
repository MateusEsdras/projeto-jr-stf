package com.jr.stf.resources;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.jr.stf.domain.Autor;
import com.jr.stf.dto.AutorActiveDTO;
import com.jr.stf.dto.CredenciaisDTO;
import com.jr.stf.services.AutorService;

@CrossOrigin
@RestController
@RequestMapping(value="/autor")
public class AutorResource {
	
	@Autowired
	private AutorService autorService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AutorActiveDTO> find(@PathVariable Integer id){
		Autor obj = autorService.find(id);
		AutorActiveDTO objDTO = new AutorActiveDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<AutorActiveDTO>> findAll(){
		List<Autor> list = autorService.findAll();
		List<AutorActiveDTO> listDTO = list.stream().map(obj -> new AutorActiveDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Autor> insert(@Valid @RequestBody Autor obj){
		obj = autorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Autor> login(@Valid @RequestBody CredenciaisDTO credenciais) {
		Autor obj = autorService.validateSenha(credenciais);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Autor obj, @PathVariable Integer id){
		obj.setId(id);
		obj = autorService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/add/{idAutor}/{idObra}", method=RequestMethod.PUT)
	public ResponseEntity<Void> addObra(@PathVariable Integer idAutor, @PathVariable Integer idObra){
		autorService.addObra(idAutor, idObra);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/remove/{idAutor}/{idObra}", method=RequestMethod.PUT)
	public ResponseEntity<Void> removeObra(@PathVariable Integer idAutor, @PathVariable Integer idObra){
		autorService.removeObra(idAutor, idObra);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		autorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
