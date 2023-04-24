package com.comit.finalProject.finalproject;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

@Component
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Books> findBooksByGenreAndPercentage(Map<String, String> percentPerGenre) {
		String separator = "";
		String sqlString = "SELECT b FROM Books b INNER JOIN b.genres g ON g.book_id=b.id WHERE ";
		
		for (Map.Entry<String, String> entry : percentPerGenre.entrySet() ) {
			
			Double percentage = Double.parseDouble(entry.getValue());
			
			sqlString = sqlString
					+ separator
					+ "(g.genre_id="+ (entry.getKey()) +" AND g.overall_percentage > "+ (percentage - 6 ) +" AND g.overall_percentage < "+  (percentage + 6 ) +")";
			separator = " OR ";
		}
		
		sqlString = sqlString + " GROUP BY b.id HAVING count(b.id) = " + percentPerGenre.size();
		

	    TypedQuery<Books> query = entityManager.createQuery(sqlString, Books.class);	    
	    List<Books> resultList = query.getResultList();
	    return resultList;
	}
	

	private List<Books> findBooksByGenreAndPercentage_works(Map<Integer, Double> percentPerGenre) {
//		SELECT *
//		FROM `books` b INNER JOIN `book_genred` ON `book_genred`.book_id=b.id
//		WHERE 
//		(`book_genred`.genre_id=76 AND `book_genred`.overall_percentage > 55 AND `book_genred`.overall_percentage < 58)
//		OR
//		(`book_genred`.genre_id=67 AND `book_genred`.overall_percentage > 5 AND `book_genred`.overall_percentage < 8)
//
//		GROUP BY b.id
//		HAVING count(b.id) = 2
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Books> query = cb.createQuery(Books.class);
        Root<Books> user = query.from(Books.class);

//        Path<String> emailPath = user.get("email");
//
//        List<Predicate> predicates = new ArrayList<>();
//        for (String email : emails) {
//            predicates.add(cb.like(emailPath, email));
//        }
        
//        query.select(user)
//            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        
        
        Path<String> idPath = user.get("id");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(idPath, "2247"));
        predicates.add(cb.equal(idPath, "2248"));
//        query.groupBy(null)
//        query.having(null)
        
        query.select(user).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
            .getResultList();
	}
	

	
}
