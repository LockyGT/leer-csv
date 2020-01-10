package com.israel.springboot.leercsv.utils;

public class ConfigCSV {
	
	private String header;
	private Boolean required;
	private String regex;
	private Integer index;
	
	public ConfigCSV() { }

	/**
	 * @param header
	 * @param required
	 */
	public ConfigCSV(String header, Boolean required) {
		this.header = header;
		this.required = required;
	}
	
	

	/**
	 * @param header
	 * @param required
	 * @param index
	 */
	public ConfigCSV(String header, Boolean required, Integer index) {
		super();
		this.header = header;
		this.required = required;
		this.index = index;
	}

	/**
	 * @param header
	 * @param required
	 * @param regex
	 */
	public ConfigCSV(String header, Boolean required, String regex) {
		
		this.header = header;
		this.required = required;
		this.regex = regex;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the required
	 */
	public Boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "ConfigCSV [header=" + header + ", required=" + required + ", regex=" + regex + ", index=" + index + "]";
	}
	
}
