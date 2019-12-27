package com.jr.stf.resources;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jr.stf.domain.Autor;

@RestController
@RequestMapping(value="/autor")
public class AutorResource {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<Autor> test() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Autor autor1 = new Autor(null, "João", "teste@gmail.com", "123.123.123-12", sdf.parse("19/02/1988"), "Brazil", "Masculino");
		return ResponseEntity.ok().body(autor1);
	}
}
