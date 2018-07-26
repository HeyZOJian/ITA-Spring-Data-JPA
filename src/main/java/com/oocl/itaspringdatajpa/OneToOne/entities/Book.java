package com.oocl.itaspringdatajpa.OneToOne.entities;

import javax.persistence.*;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToOne(fetch = FetchType.LAZY)
	private BookDetail bookDetail;

	public Book() {
	}

	public Book(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
