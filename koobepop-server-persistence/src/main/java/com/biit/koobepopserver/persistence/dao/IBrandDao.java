package com.biit.koobepopserver.persistence.dao;

import java.util.List;

import com.biit.koobepopserver.persistence.entity.Brand;
import com.biit.persistence.dao.IJpaGenericDao;

public interface IBrandDao  extends IJpaGenericDao<Brand, Long> {

	List<Brand> getAll(String name);

}
