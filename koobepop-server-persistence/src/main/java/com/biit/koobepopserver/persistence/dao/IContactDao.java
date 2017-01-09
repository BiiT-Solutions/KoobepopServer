package com.biit.koobepopserver.persistence.dao;

import java.util.List;

import com.biit.koobepopserver.persistence.entity.Contact;
import com.biit.persistence.dao.IJpaGenericDao;

public interface IContactDao extends IJpaGenericDao<Contact, Long> {

	List<Contact> getAll(String name, String phone, String mail);
}
