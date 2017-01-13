package com.biit.koobepopserver.persistence.dao.jpa;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import com.biit.koobepopserver.persistence.entity.Brand;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Product;

public class ProductDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	public static Product createTestProduct(String name,Company company) {
		Product brand = new Product();
		brand.setName(name);
		brand.setCompany(company);
		return brand;
	}
}

