package com.oocl.itaspringdatajpa.OneToOne.controllers;

import com.oocl.itaspringdatajpa.OneToOne.controllers.dto.BookDTO;
import com.oocl.itaspringdatajpa.OneToOne.entities.Book;
import com.oocl.itaspringdatajpa.OneToOne.entities.BookDetail;
import com.oocl.itaspringdatajpa.OneToOne.repositories.BookDetailRepository;
import com.oocl.itaspringdatajpa.OneToOne.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookDetailRepository bookDetailRepository;

	@Transactional
	@GetMapping(path = "")
	public List<BookDTO> findAllBooks(){
		return bookRepository.findAll().stream()
				.map(book -> new BookDTO(book))
				.collect(Collectors.toList());
	}

	@Transactional
	@GetMapping(path = "/{id}")
	public BookDTO findBookById(@PathVariable Long id){
		return new BookDTO(bookRepository.findById(id).get());
	}


	// TODO: how to return http status code and data
	@Transactional
	@PostMapping(path = "")
	public Book addBook(@RequestBody Book book){
		book.getBookDetail().setBook(book);
		return bookRepository.save(book);
	}

	@Transactional
	@PutMapping(path = "/{id}")
	public BookDTO updateBookInfo(@PathVariable Long id ,@RequestBody Book book){
		Book book1 = bookRepository.findById(id).get();
		book1.setName(book.getName()!=null?book.getName():book1.getName());
		return new BookDTO(bookRepository.save(book1));
	}

	@Transactional
	@PutMapping(path = "/{id}/detail")
	public BookDTO updateBookDetail(@PathVariable Long id, @RequestBody BookDetail bookDetail){
		Book book = bookRepository.findById(id).get();
		BookDetail bookDetail1 = book.getBookDetail();
		bookDetail1.setAuthorName(bookDetail.getAuthorName()!=null
				?bookDetail.getAuthorName()
				:bookDetail1.getAuthorName());
		bookDetail1.setNumberOfPages(bookDetail.getNumberOfPages()!=0
				?bookDetail.getNumberOfPages()
				:bookDetail1.getNumberOfPages());
		book.setBookDetail(bookDetail1);
		bookDetailRepository.save(bookDetail1);
		bookRepository.save(book);
		return new BookDTO(book);
	}

	@Transactional
	@DeleteMapping(path = "/{id}")
	public BookDTO deleteBook(@PathVariable Long id){
		Book book = bookRepository.findById(id).get();
		bookRepository.delete(book);
		return new BookDTO(book);
	}

}
