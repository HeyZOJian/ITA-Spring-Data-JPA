package com.oocl.itaspringdatajpa.OneToOne.controllers.dto;

import com.oocl.itaspringdatajpa.OneToOne.entities.Book;

import java.util.Optional;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public class BookDTO {
	private Long id;
	private String name;
	private BookDetailDTO bookDetail;

	public BookDTO(Optional<Book> byId) {
	}

	public BookDTO(Book book) {
		this.id = book.getId();
		this.name = book.getName();
		this.bookDetail = new BookDetailDTO(book.getBookDetail());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BookDetailDTO getBookDetail() {
		return bookDetail;
	}
}
