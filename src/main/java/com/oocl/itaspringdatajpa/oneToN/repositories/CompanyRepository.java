package com.oocl.itaspringdatajpa.oneToN.repositories;

import com.oocl.itaspringdatajpa.oneToN.controllers.dto.CompanyDTO;
import com.oocl.itaspringdatajpa.oneToN.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{

	Company findAllById(Long id);
}