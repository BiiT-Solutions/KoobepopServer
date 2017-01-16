package com.biit.koobepopserver.persistence.dao.jpa;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.dao.IServiceDao;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Service;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "serviceDao" })
public class ServiceDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	private final static String COMPANY_COUNTRY_1 = "Commonwealth";
	private final static String COMPANY_NAME_1 = "Arcanum";
	private final static String SERVICE_NAME_1 = "Music";
	private final static String SERVICE_NAME_2 = "Defense";

	@Autowired
	private ICompanyDao companyDao;

	@Autowired
	private IServiceDao serviceDao;

	private Company company;
	private Service service;

	@BeforeClass(alwaysRun = true)
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void storeCompany() {
		Assert.assertEquals(companyDao.getRowCount(), 0);
		company = CompanyDaoTest.createTestCompany(COMPANY_NAME_1, COMPANY_COUNTRY_1);
		company = companyDao.makePersistent(company);
		Assert.assertEquals(companyDao.getRowCount(), 1);
	}

	@Test
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void insertService() {
		Assert.assertEquals(serviceDao.getRowCount(), 0);
		service = createTestService(SERVICE_NAME_1, company);
		service = serviceDao.merge(service);
		Assert.assertEquals(serviceDao.getAll(SERVICE_NAME_1).get(0).getName(), SERVICE_NAME_1);
	}

	@Test(dependsOnMethods = { "insertService" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void editService() {
		service.setName(SERVICE_NAME_2);
		service = serviceDao.merge(service);
		Assert.assertEquals(serviceDao.getRowCount(), 1);
		Assert.assertEquals(serviceDao.getAll().get(0).getName(), SERVICE_NAME_2);
	}

	@Test(dependsOnMethods = { "editService" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void removeService() throws ElementCannotBeRemovedException {
		serviceDao.makeTransient(service);
		Assert.assertEquals(serviceDao.getRowCount(), 0);
	}

	@AfterClass(alwaysRun = true)
	@Rollback(value = false)
	public void clearDatabase() throws ElementCannotBeRemovedException {
		deleteFromTables(new String[] { "services" });
		deleteFromTables(new String[] { "companies" });
		Assert.assertEquals(serviceDao.getRowCount(), 0);
		Assert.assertEquals(companyDao.getRowCount(), 0);
	}
	public static Service createTestService(String name,Company company) {
		Service service = new Service();
		service.setName(name);
		service.setCompany(company);
		return service;
	}
}
