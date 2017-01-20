package com.biit.koobepopserver.persistence.dao.jpa;

import java.util.HashSet;
import java.util.Set;

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
import org.testng.annotations.Test;

import com.biit.koobepopserver.persistence.dao.IBrandDao;
import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.dao.IContactDao;
import com.biit.koobepopserver.persistence.dao.IProductDao;
import com.biit.koobepopserver.persistence.dao.IServiceDao;
import com.biit.koobepopserver.persistence.entity.Brand;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Product;
import com.biit.koobepopserver.persistence.entity.Service;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "companyDao" })
public class CompanyDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	private final static String CONTACT_NAME = "Khvote";
	private final static String CONTACT_MAIL = "kvhote@arcanumTest.com";
	private final static String CONTACT_PHONE = "000000000";

	private final static String BRAND_NAME_1 = "Artificery";
	private static final String NON_EXISTENT_BRAND = "Ambrose's Ambrosy";

	private static final String PRODUCT_NAME = "Blodless";

	private static final String SERVICE_NAME = "Safeguard";

	private final static String COMPANY_COUNTRY_1 = "Commonwealth";
	private final static String COMPANY_COUNTRY_2 = "Aturan Empire";
	private final static String COMPANY_NAME_1 = "Arcanum";
	private final static String COMPANY_NAME_2 = "Maer's court";
	private final static String COMPANY_DESCRIPTION_1 = "Full of people medling with dark forces better left alone";
	private final static String COMPANY_DESCRIPTION_2 = "A jungle of stupidity and plotting";
	private final static Integer COMPANY_PRIORITY_1 = 1;
	private final static Integer COMPANY_PRIORITY_2 = 2;
	
	

	private Company company;

	@Autowired
	private ICompanyDao companyDao;

	@Autowired
	private IContactDao contactDao;

	@Autowired
	private IBrandDao brandDao;

	@Autowired
	private IServiceDao serviceDao;

	@Autowired
	private IProductDao productDao;

	public static Company createTestCompany(String name, String country) {
		Company company = new Company();
		company.setName(name);
		company.setCountry(country);
		return company;
	}

	@Test
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void storeCompany() {
		Assert.assertEquals(companyDao.getRowCount(), 0);
		company = createTestCompany(COMPANY_NAME_1, COMPANY_COUNTRY_1);
		ServiceDaoTest.createTestService(SERVICE_NAME, company);
		ProductDaoTest.createTestProduct(PRODUCT_NAME, company);
		BrandDaoTest.createTestBrand(BRAND_NAME_1, company);
		company = companyDao.makePersistent(company);
		Assert.assertEquals(companyDao.getRowCount(), 1);
		Assert.assertEquals(brandDao.getRowCount(), 1);
		Assert.assertEquals(productDao.getRowCount(), 1);
		Assert.assertEquals(serviceDao.getRowCount(), 1);

	}

	@Test(dependsOnMethods = { "storeCompany" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void searchCompany() {
		Assert.assertEquals(companyDao.getRowCount(), 1);
		// get by name
		Assert.assertNotNull(companyDao.getAll(COMPANY_NAME_1, null).get(0));
		Assert.assertTrue(companyDao.getAll(COMPANY_NAME_2, null).isEmpty());
	}

	/**
	 * TODO Modify company to make a valid test
	 * */
	@Test(dependsOnMethods = { "searchCompany" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void editCompany() {
		// Contacts
		ContactDaoTest.createTestContact(CONTACT_NAME, CONTACT_PHONE, CONTACT_MAIL, company);
		company = companyDao.merge(company);
		Assert.assertNotNull(contactDao.getAll(CONTACT_NAME, CONTACT_PHONE, CONTACT_MAIL).get(0));

	}
	
	@Test(dependsOnMethods = { "editCompany" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void removeCompany() throws ElementCannotBeRemovedException {
		Assert.assertEquals(companyDao.getRowCount(), 1);
		companyDao.makeTransient(company);
		Assert.assertEquals(companyDao.getRowCount(), 0);
		Assert.assertEquals(contactDao.getRowCount(), 0);
		Assert.assertEquals(brandDao.getRowCount(), 0);
		Assert.assertEquals(productDao.getRowCount(), 0);
		Assert.assertEquals(serviceDao.getRowCount(), 0);

	}
	@Test(dependsOnMethods = {"removeCompany"})
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void getAllWithJoin(){

		Assert.assertEquals(companyDao.getRowCount(), 0);
		// Fill DB with a couple of companies 
		Company company1 = createTestCompany(COMPANY_NAME_1, COMPANY_COUNTRY_1);
		Company company2 = createTestCompany(COMPANY_NAME_2, COMPANY_COUNTRY_2);		
		BrandDaoTest.createTestBrand(BRAND_NAME_1, company1);
		BrandDaoTest.createTestBrand(BRAND_NAME_1, company2);
		ProductDaoTest.createTestProduct(PRODUCT_NAME, company1);
		ProductDaoTest.createTestProduct(PRODUCT_NAME, company2);
		ServiceDaoTest.createTestService(SERVICE_NAME, company1);
		ServiceDaoTest.createTestService(SERVICE_NAME, company2);
		companyDao.merge(company1);
		companyDao.merge(company2);
		Assert.assertEquals(companyDao.getRowCount(), 2);
		Assert.assertEquals(companyDao.getAll(COMPANY_COUNTRY_1, BRAND_NAME_1, PRODUCT_NAME, SERVICE_NAME).size(),1);
		Assert.assertEquals(companyDao.getAll(null, BRAND_NAME_1, PRODUCT_NAME, SERVICE_NAME).size(),2);
		Assert.assertEquals(companyDao.getAll(null, null, PRODUCT_NAME, SERVICE_NAME).size(),2);
		Assert.assertEquals(companyDao.getAll(null, null, null, SERVICE_NAME).size(),2);
		Assert.assertEquals(companyDao.getAll(null, null, null, null).size(),0);
		Assert.assertEquals(companyDao.getAll(null, NON_EXISTENT_BRAND, null, null).size(),0);
		
	} 
	
	@AfterClass(alwaysRun = true)
	@Rollback(value = false)
	public void clearDatabase() throws ElementCannotBeRemovedException {
		deleteFromTables(new String[] { "contacts" });
		deleteFromTables(new String[] { "products" });
		deleteFromTables(new String[] { "services" });
		deleteFromTables(new String[] { "brands" });
		deleteFromTables(new String[] { "companies" });
		Assert.assertEquals(contactDao.getRowCount(), 0);
		Assert.assertEquals(companyDao.getRowCount(), 0);
	}

}
