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
import com.jr.stf.domain.Obra;
import com.jr.stf.dto.ObraActiveDTO;
import com.jr.stf.services.ObraService;

@CrossOrigin
@RestController
@RequestMapping(value="/obra")
public class ObraResource {
	
	@Autowired
	private ObraService obraService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ObraActiveDTO> find(@PathVariable Integer id){
		Obra obj = obraService.find(id);
		ObraActiveDTO objDTO = new ObraActiveDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<ObraActiveDTO>> findAll(){
		List<Obra> list = obraService.findAll();
		List<ObraActiveDTO> listDTO = list.stream().map(obj -> new ObraActiveDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Obra obj){
		obj = obraService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Obra obj, @PathVariable Integer id){
		obj.setId(id);
		obj = obraService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		obraService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
