package com.oocl.itaspringdatajpa.OneToOne.entities;

import javax.persistence.*;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private BookDetail bookDetail;

	public Book() {
	}

	public Book(String name) {
		this.name = name;
	}

	public Book(String name, BookDetail bookDetail) {
		this.name = name;
		this.bookDetail = bookDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(BookDetail bookDetail) {
		this.bookDetail = bookDetail;
	}
}
