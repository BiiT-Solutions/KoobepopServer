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
@Table(name = "contacts")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.biit.koobepopserver.persistence.entity.Contact")
public class Contact extends BaseStorableObject {

	private static final long serialVersionUID = 1172630136348751139L;
	
	@Expose
	private String name;

	@Expose
	private String phone;

	@Expose
	private String mail;
	
	//no @Expose because of the bilateral reference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Company company;

	public Contact() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
		if (company != null && !company.getContacts().contains(this)) {
			company.getContacts().add(this);
		}
	}

	@Override
	public String toString() {
		return "Contact [" + getId() + ", " + getName() + ", "+getPhone()+", "+getMail()+"]";
	}
}
