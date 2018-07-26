package com.oocl.itaspringdatajpa.OneToOne.repositories;

import com.oocl.itaspringdatajpa.OneToOne.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public interface BookRepository extends JpaRepository<Book,Long> {
}
