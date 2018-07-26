package com.oocl.itaspringdatajpa.OneToN.repositories;

import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{

	Company findAllById(Long id);
}