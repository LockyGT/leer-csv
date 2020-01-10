package com.israel.springboot.leercsv.utils;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.israel.springboot.leercsv.entity.Partner;

import au.com.bytecode.opencsv.CSVReader;

public class CsvReader {

	
	private List<ConfigCSV> configurations;
	private List<String> messages;

	private String nombre          = "*Nombre";
	private String email           = "*E-mail";
	private String apellidoPaterno = "﻿*Apellido Paterno";
	private String fechaNacimiento = "*Fecha de Nacimiento";
	private String apellidoMaterno = "*Apellido Materno";

	public CsvReader() {
		this.configurations = new ArrayList<ConfigCSV>();
		this.messages = new ArrayList<String>();
		this.configurations.add(new ConfigCSV(this.nombre, true));
		this.configurations.add(new ConfigCSV(this.email, true, Patterns.EMAIL));
		this.configurations.add(new ConfigCSV(this.fechaNacimiento, true));
		this.configurations.add(new ConfigCSV(this.apellidoPaterno, true));
		this.configurations.add(new ConfigCSV(this.apellidoMaterno, true));

	}

	public Map<String, Object> read(String urlFile) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		List<Partner> partners = new ArrayList<Partner>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		boolean correctCsv = true;
		try {
			
			CSVReader csvReader = new CSVReader(new FileReader(urlFile));

			List<String[]> allData = csvReader.readAll();

			// Buscando cabeceras para obtener sus indices
			for (ConfigCSV configCSV : this.configurations) {
				String[] s = allData.get(0);
				int index = Arrays.asList(s).indexOf(configCSV.getHeader());
				if (index > -1) {
					configCSV.setIndex(index);

				} else {
					correctCsv = false;
					messages.add("La columna '" + configCSV.getHeader() + "' no existe!!!");
				}
			}
			
			// Obtiene los json sobrantes, haciendo referencia a los sobrantes
			List<ConfigCSV> jsonProperties = findJSONProperties(allData.get(0));
			
			
			
			// Validando CSV de acuerdo a la información recaudada
			if (correctCsv) {
				String data;
				int sizeArray = allData.get(0).length;
				for (int i = 1; i < allData.size(); i++) {

					if (allData.get(i).length >= sizeArray) {

						for (ConfigCSV configCSV : this.configurations) {
							data = allData.get(i)[configCSV.getIndex()];

							if (configCSV.isRequired() && data.replaceAll("\\s", "").isEmpty()) {
								messages.add("Error la columna '" + configCSV.getHeader() + "' en la fila #" + (i + 1)
										+ " es requerido");
								correctCsv = false;
							}

							if (configCSV.getRegex() != null
									&& !PatternRecognition.validatePattern(configCSV.getRegex(), data)) {
								messages.add("Error la columna '" + configCSV.getHeader() + "' " + "en la fila #" + (i + 1)
										+ " el patrón no reconocido");
								correctCsv = false;
							}
						}
					} else {
						messages.add("Fila #" + (i + 1) + " no cuenta con suficientes columnas");
					}
				}
			}

			// Si el CSV fue validado correctamente se comienza a leer el archivo
			if (correctCsv) {
				Partner partner;
				String[] data1;

				for (int i = 1; i < allData.size(); i++) {
					
					data1 = allData.get(i);
					partner = new Partner();
					partner.setId((long) i);
					partner.setNombre(data1[indexOf(this.nombre)]);
					partner.setApellidoPaterno(data1[indexOf(this.apellidoPaterno)]);
					partner.setApellidoMaterno(data1[indexOf(this.apellidoMaterno)]);
					partner.setEmail(data1[indexOf(this.email)]);
					partner.setFechaNacimiento(formatter.parse(data1[indexOf(this.fechaNacimiento)]));
					partner.setJson(getJson(jsonProperties, data1));
					partners.add(partner);
				}

			}

			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(correctCsv) {
			response.put("status",true);
			response.put("partners", partners);
		} else {
			response.put("status", false);
			response.put("messages", messages);
		}
		
		return response;
	}

	private int indexOf(String keyword) {
		ConfigCSV config = this.configurations.stream().filter(c -> c.getHeader().equals(keyword)).findFirst()
				.orElse(null);
		if (config != null) {

			return config.getIndex();
		}

		return -1;
	}
	

	private List<ConfigCSV> findJSONProperties(String[] headers) {
		List<ConfigCSV> properties = new ArrayList<ConfigCSV>();
		
		for(int i = 0; i<headers.length; i++) {
		
			if(indexOf(headers[i]) == -1) {
				properties.add(new ConfigCSV(headers[i], false, i));
			}
		}
		
		return properties;
	}
	
	private String getJson(List<ConfigCSV> properties, String[] data) {
		String json = "{";
			
		for(ConfigCSV ccsv: properties) {
			json += "\""+ccsv.getHeader()+"\": \""+data[ccsv.getIndex()]+"\",";
		}
		json = json.substring(0, json.length() - 1)+"}";
		return json;
	}
}
