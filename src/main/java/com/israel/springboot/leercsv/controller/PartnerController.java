package com.israel.springboot.leercsv.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.springboot.leercsv.service.PartnerService;

@RestController
@RequestMapping("/")
public class PartnerController {
	
	@Autowired
	private PartnerService service;
	
	private final Logger log = LoggerFactory.getLogger(PartnerController.class);
	
	@GetMapping("/ejecutar")
	public ResponseEntity<?> ejecutar() {
		
		log.debug("Leyendo archivo");
		return new ResponseEntity<Map<String, Object>>( service.findAll(), HttpStatus.OK);
	}
}
