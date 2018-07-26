package com.oocl.itaspringdatajpa.OneToOne.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@Entity
public class BookDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authorName;
	private int numberOfPages;
	@JsonIgnore
	@JoinColumn(name = "book_id")
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "bookDetail")
	private Book book;

	public BookDetail() {
	}

	public BookDetail(String auto_name, int numberOfPages) {
		this.authorName = auto_name;
		this.numberOfPages = numberOfPages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
