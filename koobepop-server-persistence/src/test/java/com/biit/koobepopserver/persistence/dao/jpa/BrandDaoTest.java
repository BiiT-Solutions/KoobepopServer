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

import com.biit.koobepopserver.persistence.dao.IBrandDao;
import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.entity.Brand;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "brandDao" })
public class BrandDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	private final static String COMPANY_COUNTRY_1 = "Commonwealth";
	private final static String COMPANY_NAME_1 = "Arcanum";
	private final static String BRAND_NAME_1 = "Artificery";
	private final static String BRAND_NAME_2 = "Tinkers' stuff";

	@Autowired
	private ICompanyDao companyDao;

	@Autowired
	private IBrandDao brandDao;

	private Company company;
	
	private Brand brand;

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
	public void insertBrand() {
		Assert.assertEquals(brandDao.getRowCount(), 0);
		brand = createTestBrand(BRAND_NAME_1, company);
		brand = brandDao.merge(brand);
		Assert.assertEquals(brandDao.getAll(BRAND_NAME_1).get(0).getName(), BRAND_NAME_1);
	}

	@Test(dependsOnMethods = { "insertBrand" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void editBrand() {
		brand.setName(BRAND_NAME_2);
		brand = brandDao.merge(brand);
		Assert.assertEquals(brandDao.getRowCount(), 1);
		Assert.assertEquals(brandDao.getAll().get(0).getName(), BRAND_NAME_2);
	}

	@Test(dependsOnMethods = { "editBrand" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void removeBrand() throws ElementCannotBeRemovedException {
		Assert.assertEquals(brandDao.getRowCount(), 1);
		brandDao.makeTransient(brand);
		Assert.assertEquals(brandDao.getRowCount(), 0);
	}

	@AfterClass(alwaysRun = true)
	@Rollback(value = false)
	public void clearDatabase() throws ElementCannotBeRemovedException {
		deleteFromTables(new String[] { "brands" });
		deleteFromTables(new String[] { "companies" });
		Assert.assertEquals(brandDao.getRowCount(), 0);
		Assert.assertEquals(companyDao.getRowCount(), 0);
	}

	public static Brand createTestBrand(String name, Company company) {
		Brand brand = new Brand();
		brand.setName(name);
		brand.setCompany(company);
		return brand;
	}
}
