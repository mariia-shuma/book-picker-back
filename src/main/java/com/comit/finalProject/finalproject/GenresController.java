package com.comit.finalProject.finalproject;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/genres")
@CrossOrigin(origins = "http://localhost:3000")
public class GenresController {
	@Autowired
	private GenresRepository Genresrepository;
	
	

	
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Genres> findAll() {
		return Genresrepository.findAll();
	}
	
	
	
	

}
