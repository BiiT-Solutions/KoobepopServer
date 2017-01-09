package com.biit.koobepopserver.persistence.dao.jpa;

import org.springframework.stereotype.Repository;

import com.biit.koobepopserver.persistence.dao.IBrandDao;
import com.biit.koobepopserver.persistence.entity.Brand;

@Repository
public class BrandDao extends AnnotatedGenericDao<Brand, Long> implements IBrandDao{

	public BrandDao() {
		super(Brand.class);
	}

	

}
