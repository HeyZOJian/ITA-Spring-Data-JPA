package com.oocl.itaspringdatajpa.OneToOne.controllers.dto;

import com.oocl.itaspringdatajpa.OneToOne.entities.BookDetail;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public class BookDetailDTO{
	private Long id;
	private String authorName;
	private int numberOfPages;
	private Long bookId;

	public BookDetailDTO(BookDetail bookDetail) {
		this.id = bookDetail.getId();
		this.authorName = bookDetail.getAuthorName();
		this.numberOfPages = bookDetail.getNumberOfPages();
		this.bookId = bookDetail.getBook().getId();
	}

	public Long getId() {
		return id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public Long getBookId() {
		return bookId;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}
}
