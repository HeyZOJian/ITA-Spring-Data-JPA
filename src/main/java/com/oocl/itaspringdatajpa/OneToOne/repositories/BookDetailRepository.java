package com.oocl.itaspringdatajpa.OneToOne.repositories;

import com.oocl.itaspringdatajpa.OneToOne.entities.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {
}
