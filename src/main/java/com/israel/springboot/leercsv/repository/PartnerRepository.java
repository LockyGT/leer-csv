package com.israel.springboot.leercsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.springboot.leercsv.entity.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long>{
	
}
