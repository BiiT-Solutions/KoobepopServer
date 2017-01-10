package com.biit.koobepopserver.core.rest;

public class SearchFromJson {
	private String brand;
	private String country;
	private String product;
	private String service;
	public SearchFromJson(){		
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}	
}
