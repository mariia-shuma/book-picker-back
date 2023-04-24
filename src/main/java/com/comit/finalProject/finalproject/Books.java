package com.comit.finalProject.finalproject;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Books {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String full_name;
	private String author;
	private String cover;
	private String short_summary;
	private Double rating_overall;
	private Integer rating_people_voted;
	private Integer reviews_amount;
	
	@OneToMany(mappedBy = "book_id")
	private List<BookGenred> genres;
	
	

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getShort_summary() {
		return short_summary;
	}
	public void setShort_summary(String short_summary) {
		this.short_summary = short_summary;
	}
	public Double getRating_overall() {
		return rating_overall;
	}
	public void setRating_overall(Double rating_overall) {
		this.rating_overall = rating_overall;
	}
	public Integer getRating_people_voted() {
		return rating_people_voted;
	}
	public void setRating_people_voted(Integer rating_people_voted) {
		this.rating_people_voted = rating_people_voted;
	}
	public Integer getReviews_amount() {
		return reviews_amount;
	}
	public void setReviews_amount(Integer reviews_amount) {
		this.reviews_amount = reviews_amount;
	}
	
	
	
}