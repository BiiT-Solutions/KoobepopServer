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
import com.biit.koobepopserver.persistence.dao.IProductDao;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Product;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "productDao" })
public class ProductDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	private final static String COMPANY_COUNTRY_1 = "Commonwealth";
	private final static String COMPANY_NAME_1 = "Arcanum";
	private final static String PRODUCT_NAME_1 = "Blodless";
	private final static String PRODUCT_NAME_2 = "Simpathy lamp";

	@Autowired
	private ICompanyDao companyDao;

	@Autowired
	private IProductDao productDao;

	private Company company;
	private Product product;

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
	public void insertProduct() {
		Assert.assertEquals(productDao.getRowCount(), 0);
		product = createTestProduct(PRODUCT_NAME_1, company);
		product = productDao.merge(product);
		Assert.assertEquals(productDao.getAll(PRODUCT_NAME_1).get(0).getName(), PRODUCT_NAME_1);
	}

	@Test(dependsOnMethods = { "insertProduct" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void editProduct() {
		product.setName(PRODUCT_NAME_2);
		product = productDao.merge(product);
		Assert.assertEquals(productDao.getRowCount(), 1);
		Assert.assertEquals(productDao.getAll().get(0).getName(), PRODUCT_NAME_2);
	}

	@Test(dependsOnMethods = { "editProduct" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void removeProduct() throws ElementCannotBeRemovedException {
		productDao.makeTransient(product);
		Assert.assertEquals(productDao.getRowCount(), 0);
	}

	@AfterClass(alwaysRun = true)
	@Rollback(value = false)
	public void clearDatabase() throws ElementCannotBeRemovedException {
		deleteFromTables(new String[] { "products" });
		deleteFromTables(new String[] { "companies" });
		Assert.assertEquals(productDao.getRowCount(), 0);
		Assert.assertEquals(companyDao.getRowCount(), 0);
	}
	
	
	
	
	
	
	public static Product createTestProduct(String name, Company company) {
		Product product = new Product();
		product.setName(name);
		product.setCompany(company);
		return product;
	}
}
