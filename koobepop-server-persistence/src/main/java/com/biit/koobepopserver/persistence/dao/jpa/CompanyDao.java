package com.biit.koobepopserver.persistence.dao.jpa;

import org.springframework.stereotype.Repository;

import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.entity.Company;

@Repository
public class CompanyDao extends AnnotatedGenericDao<Company, Long> implements ICompanyDao{

	public CompanyDao() {
		super(Company.class);
	}


}
