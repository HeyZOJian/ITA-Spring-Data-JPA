package com.oocl.itaspringdatajpa.NToN.repositories;

import com.oocl.itaspringdatajpa.NToN.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
