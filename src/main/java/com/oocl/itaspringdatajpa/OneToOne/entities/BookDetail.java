package com.oocl.itaspringdatajpa.OneToOne.entities;

import javax.persistence.*;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@Entity
public class BookDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String autoName;

	@OneToOne
	private Book book;

	public BookDetail() {
	}

	public BookDetail(String auto_name) {
		this.autoName = auto_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutoName() {
		return autoName;
	}

	public void setAutoName(String autoName) {
		this.autoName = autoName;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
