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
import com.jr.stf.dto.AutorDTO;
import com.jr.stf.services.AutorService;

@CrossOrigin
@RestController
@RequestMapping(value="/autor")
public class AutorResource {
	
	@Autowired
	private AutorService autorService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Autor> find(@PathVariable Integer id){
		Autor obj = autorService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<AutorDTO>> findAll(){
		List<Autor> list = autorService.findAll();
		List<AutorDTO> listDTO = list.stream().map(obj -> new AutorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Autor obj){
		obj = autorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Autor obj, @PathVariable Integer id){
		obj.setId(id);
		obj = autorService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		autorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
