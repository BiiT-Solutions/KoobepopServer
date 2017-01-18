package com.biit.koobepopserver.persistence.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biit.persistence.entity.BaseStorableObject;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "companies")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.biit.koobepopserver.persistence.entity.Company")
public class Company extends BaseStorableObject {
	private static final long serialVersionUID = -925528382828636133L;

	public static final int DESCRIPTION_LENGTH = 1000;
	
	@Expose //Gson takes this field for generating json
	@Column(nullable=false)
	private String name;
	
	@Expose
	private String country;
	
	@Expose
	@Lob
	@Column(length = DESCRIPTION_LENGTH)
	private String description;

	@Expose
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
	private List<Contact> contacts;

	@Expose
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
	private Set<Brand> brands;
	
	@Expose
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
	private Set<Service> services;
	
	@Expose
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
	private Set<Product> products;
	
	
	public Company() {
		super();
		contacts = new ArrayList<>();
		brands = new HashSet<>();
		services = new HashSet<>();
		products = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public void addContact(Contact contact){
		
		if (contact != null && !this.contacts.contains(contact)){
		this.contacts.add(contact);
		contact.setCompany(this);
		}
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString(){
		return "Company ["+getName()+"]";
	}
	
}
