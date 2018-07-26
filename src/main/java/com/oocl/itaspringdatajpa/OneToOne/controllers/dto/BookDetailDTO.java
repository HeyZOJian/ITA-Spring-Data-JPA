package com.oocl.itaspringdatajpa.OneToOne.controllers.dto;

import com.oocl.itaspringdatajpa.OneToOne.entities.BookDetail;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public class BookDetailDTO{
	private int id;
	private String authorName;
	private int bookId;

	public BookDetailDTO(BookDetail bookDetail) {
		this.id = bookDetail.getId();
		this.authorName = bookDetail.getAutoName();
		this.bookId = bookDetail.getBook().getId();
	}

	public int getId() {
		return id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public int getBookId() {
		return bookId;
	}
}
