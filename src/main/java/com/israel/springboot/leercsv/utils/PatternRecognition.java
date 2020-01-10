package com.israel.springboot.leercsv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternRecognition {
	
	public static boolean validateDate(String fecha) {
		
		try {
			SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
			sdt.setLenient(false);
			sdt.parse(fecha);
		}catch(ParseException e) {
			return false;
		}
		return true;
	}
	
	
	public static boolean validatePattern(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}
}
