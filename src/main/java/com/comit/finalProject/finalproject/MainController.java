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
@RequestMapping(path="/books")
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookRepositoryCustom bookRepositoryCustom;

	
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Books> getAllBooks(@RequestParam Map<String,String> percentPerGenreQuery) {
		return bookRepositoryCustom.findBooksByGenreAndPercentage(percentPerGenreQuery);
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody Optional<Books> getOneBook(@PathVariable(value="id") Integer id, String someAttr) {
		return bookRepository.findById(id);
	}
	
	
	
	

}
