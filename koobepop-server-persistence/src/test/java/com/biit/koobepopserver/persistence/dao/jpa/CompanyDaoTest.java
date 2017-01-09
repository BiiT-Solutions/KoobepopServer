package com.biit.koobepopserver.persistence.dao.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.biit.koobepopserver.persistence.entity.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
@Test(groups = {"companyDao"})
public class CompanyDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	
	public static Company createTestCompany(String name){
		Company company = new Company();
		company.setName(name);		
		return company;
	}
}
