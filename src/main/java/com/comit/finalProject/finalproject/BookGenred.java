package com.comit.finalProject.finalproject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_genred")
public class BookGenred {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer book_id;
	private Integer genre_id;
	private Integer num_users_voted;
	private Double overall_percentage;
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public Integer getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}
	public Integer getNum_users_voted() {
		return num_users_voted;
	}
	public void setNum_users_voted(Integer num_users_voted) {
		this.num_users_voted = num_users_voted;
	}
	public Double getOverall_percentage() {
		return overall_percentage;
	}
	public void setOverall_percentage(Double overall_percentage) {
		this.overall_percentage = overall_percentage;
	}
	
}