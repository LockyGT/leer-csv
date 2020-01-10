package com.israel.springboot.leercsv.service;

import java.util.Map;

import com.israel.springboot.leercsv.entity.Partner;

public interface PartnerService {
	
	public Map<String, Object> findAll();
	public Partner post(Partner partner);
	public Partner put(Partner partner);
	public void delete(Long id);
}
