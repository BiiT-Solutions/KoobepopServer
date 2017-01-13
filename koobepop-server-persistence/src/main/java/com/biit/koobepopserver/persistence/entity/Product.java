package com.biit.koobepopserver.persistence.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biit.persistence.entity.BaseStorableObject;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "products")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.biit.koobepopserver.persistence.entity.Brand")
public class Product extends BaseStorableObject {
	private static final long serialVersionUID = 150876456165930307L;

	@Expose
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Company company;

	public Product() {
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
		if (company != null && !company.getProducts().contains(this)) {
			company.getProducts().add(this);
		}
	}

	@Override
	public String toString() {
		return "Product [" + this.name + "]";
	}
}
