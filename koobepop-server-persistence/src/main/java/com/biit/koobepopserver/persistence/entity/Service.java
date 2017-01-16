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
@Table(name = "services")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.biit.koobepopserver.persistence.entity.Service")
public class Service extends BaseStorableObject {
	private static final long serialVersionUID = -3611299577577905581L;

	@Expose
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Company company;

	public Service() {
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
		if (company != null && !company.getServices().contains(this)) {
			company.getServices().add(this);
		}
	}

	@Override
	public String toString() {
		return "Service [" + this.name + "]";
	}

}
