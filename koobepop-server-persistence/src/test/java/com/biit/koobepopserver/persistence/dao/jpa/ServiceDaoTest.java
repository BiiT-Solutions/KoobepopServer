package com.biit.koobepopserver.persistence.dao.jpa;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Service;

public class ServiceDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	public static Service createTestService(String name,Company company) {
		Service service = new Service();
		service.setName(name);
		service.setCompany(company);
		return service;
	}
}
