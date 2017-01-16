package com.biit.koobepopserver.persistence.dao.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "serviceDao" })
public class ServiceDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	public static Service createTestService(String name,Company company) {
		Service service = new Service();
		service.setName(name);
		service.setCompany(company);
		return service;
	}
}
