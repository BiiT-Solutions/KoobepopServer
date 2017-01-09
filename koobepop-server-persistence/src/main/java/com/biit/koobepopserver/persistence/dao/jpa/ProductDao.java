package com.biit.koobepopserver.persistence.dao.jpa;

import org.springframework.stereotype.Repository;

import com.biit.koobepopserver.persistence.dao.IProductDao;
import com.biit.koobepopserver.persistence.entity.Product;

@Repository
public class ProductDao extends AnnotatedGenericDao<Product, Long> implements IProductDao{

	public ProductDao() {
		super(Product.class);
	}


}
