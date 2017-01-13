package com.biit.koobepopserver.persistence.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biit.persistence.entity.BaseStorableObject;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "brands")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.biit.koobepopserver.persistence.entity.Brand")
public class Brand extends BaseStorableObject {
	private static final long serialVersionUID = -1530395525317506930L;
	
	@Expose
	private String name;

	
	@OneToOne(optional = false)
	private Company company;

	public Brand() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
		if (company != null && !company.getBrands().contains(this)) {
			company.getBrands().add(this);
		}
	}
	
	@Override
	public String toString(){
		return "Service ["+this.name+"]";
	}
}
