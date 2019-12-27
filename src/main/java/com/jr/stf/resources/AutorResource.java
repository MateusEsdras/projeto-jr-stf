package com.jr.stf.resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/autor")
public class AutorResource {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<String> test(){
		String msg = "Test ok";
		return ResponseEntity.ok().body(msg);
	}
}
