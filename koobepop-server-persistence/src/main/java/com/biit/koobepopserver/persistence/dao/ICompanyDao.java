package com.biit.koobepopserver.persistence.dao;

import java.util.List;

import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.persistence.dao.IJpaGenericDao;

public interface ICompanyDao  extends IJpaGenericDao<Company, Long> {

	List<Company> getAll(String name, String country);

	List<Company> getAll(String country, String brand, String product, String service);

}
