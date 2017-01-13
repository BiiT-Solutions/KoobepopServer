package com.biit.koobepopserver.persistence.dao.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.biit.koobepopserver.persistence.entity.Brand;
import com.biit.koobepopserver.persistence.entity.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "brandDao" })
public class BrandDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	
	public static Brand createTestBrand(String name,Company company) {
		Brand brand = new Brand();
		brand.setName(name);
		brand.setCompany(company);
		return brand;
	}
}
