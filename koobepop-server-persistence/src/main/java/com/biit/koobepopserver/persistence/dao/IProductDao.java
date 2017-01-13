package com.biit.koobepopserver.persistence.dao;

import java.util.List;

import com.biit.koobepopserver.persistence.entity.Product;
import com.biit.persistence.dao.IJpaGenericDao;

public interface IProductDao  extends IJpaGenericDao<Product, Long> {

	List<Product> getAll(String name);

}
