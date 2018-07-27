package com.oocl.itaspringdatajpa.NToN.repositories;

import com.oocl.itaspringdatajpa.NToN.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
