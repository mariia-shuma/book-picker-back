package com.comit.finalProject.finalproject;

import java.util.List;
import java.util.Map;

public interface BookRepositoryCustom {
	public List<Books> findBooksByGenreAndPercentage( Map<String,String> percentPerGenre);

}
