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
import org.testng.annotations.Test;

import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.dao.IContactDao;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Contact;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "companyDao" })
public class CompanyDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	private final static String CONTACT_NAME = "Khvote";
	private final static String CONTACT_MAIL = "kvhote@arcanumTest.com";
	private final static String CONTACT_PHONE = "000000000";
	private final static String CONTACT_NAME2 = "Sim";
	private final static String CONTACT_MAIL2 = "simon@arcanumTest.com";
	private final static String CONTACT_PHONE2 = "000000001";
	private final static String COMPANY_NAME_1 = "Arcanum";

	private Company company;

	@Autowired
	private ICompanyDao companyDao;

	@Autowired
	private IContactDao contactDao;

	public static Company createTestCompany(String name) {
		Company company = new Company();
		company.setName(name);
		return company;
	}

	public static Contact createTestContact(String name, String mail, String phone, Company company) {
		Contact contact = new Contact();
		contact.setName(name);
		contact.setMail(mail);
		contact.setPhone(phone);
		contact.setCompany(company);
		return contact;
	}

	@Test
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void storeCompany() {
		Assert.assertEquals(companyDao.getRowCount(), 0);
		company = createTestCompany(COMPANY_NAME_1);
		company = companyDao.makePersistent(company);
		Assert.assertEquals(companyDao.getRowCount(), 1);
	}

	@Test(dependsOnMethods = { "storeCompany" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void searchCompany() {
		Assert.assertEquals(companyDao.getRowCount(), 1);
		Assert.assertNotNull(companyDao.getAll(COMPANY_NAME_1).get(0));
	}
	
	/**
	 * 
	 * */
	@Test(dependsOnMethods = { "searchCompany" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void editCompany() {
		createTestContact(CONTACT_NAME, CONTACT_MAIL, CONTACT_PHONE, company);
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
	}

	@AfterClass(alwaysRun = true)
	@Rollback(value = false)
	public void clearDatabase() throws ElementCannotBeRemovedException {
		deleteFromTables(new String[] { "contacts" });
		deleteFromTables(new String[] { "companies" });
		Assert.assertEquals(contactDao.getRowCount(), 0);
		Assert.assertEquals(companyDao.getRowCount(), 0);
	}

}
