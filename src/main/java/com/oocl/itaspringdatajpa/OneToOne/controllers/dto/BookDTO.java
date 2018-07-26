package com.oocl.itaspringdatajpa.OneToOne.controllers.dto;

import com.oocl.itaspringdatajpa.OneToOne.entities.Book;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public class BookDTO {
	private int id;
	private String name;
	private int bookDetailId;

	public BookDTO(Book book) {
		this.id = book.getId();
		this.name = book.getName();
		this.bookDetailId = book.getBookDetail().getId();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBookDetailId() {
		return bookDetailId;
	}
}
