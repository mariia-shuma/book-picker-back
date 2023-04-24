package com.comit.finalProject.finalproject;


import org.springframework.data.repository.CrudRepository;

public interface GenresRepository extends CrudRepository<Genres, Integer> {
	
	  Iterable<Genres> findAll();
}
