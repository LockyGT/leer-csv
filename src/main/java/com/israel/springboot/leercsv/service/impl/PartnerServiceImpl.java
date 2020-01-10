package com.israel.springboot.leercsv.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.israel.springboot.leercsv.entity.Partner;
import com.israel.springboot.leercsv.repository.PartnerRepository;
import com.israel.springboot.leercsv.service.PartnerService;
import com.israel.springboot.leercsv.utils.CsvReader;

@Service
public class PartnerServiceImpl implements PartnerService {
	
	@Autowired
	private PartnerRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> findAll() {
		
		CsvReader csv = new CsvReader();
		Map<String, Object> reader = csv.read("prueba-2.csv"); 
		return reader;
	}

	@Override
	@Transactional
	public Partner post(Partner partner) {
		
		return repository.save(partner);
	}

	@Override
	@Transactional
	public Partner put(Partner partner) {
		
		return repository.save(partner);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
