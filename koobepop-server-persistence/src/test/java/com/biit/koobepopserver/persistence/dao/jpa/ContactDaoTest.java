
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
import com.biit.koobepopserver.persistence.dao.IContactDao;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Contact;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@Test(groups = { "contactDao" })
public class ContactDaoTest extends AbstractTransactionalTestNGSpringContextTests {
	private final static String CONTACT_NAME = "Khvote";
	private final static String CONTACT_MAIL = "kvhote@arcanumTest.com";
	private final static String CONTACT_PHONE = "000000000";
	private final static String CONTACT_NAME2 = "Sim";
	private final static String CONTACT_MAIL2 = "simon@arcanumTest.com";
	private final static String CONTACT_PHONE2 = "000000001";
	private final static String COMPANY_NAME_1 = "Arcanum";
	private final static String COMPANY_COUNTRY_1 = "Aturan Empire";

	private Company company;

	@Autowired
	private IContactDao contactDao;

	@Autowired
	private ICompanyDao companyDao;

	@BeforeClass(alwaysRun = true)
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void storeCompanyForContact() {
		Assert.assertEquals(companyDao.getRowCount(), 0);
		company = CompanyDaoTest.createTestCompany(COMPANY_NAME_1, COMPANY_COUNTRY_1);
		company = companyDao.makePersistent(company);
		Assert.assertEquals(companyDao.getRowCount(), 1);
	}

	@Test
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void storeContact() {
		Assert.assertTrue(contactDao.getAll().isEmpty());
		Contact contact = createTestContact(CONTACT_NAME, CONTACT_PHONE, CONTACT_MAIL, company);
		contact=contactDao.makePersistent(contact);
		Assert.assertFalse(contactDao.getAll().isEmpty());
	}

	@Test(dependsOnMethods = { "storeContact" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void searchContact() {
		Assert.assertEquals(contactDao.getRowCount(), 1);
		Assert.assertNotNull(contactDao.getAll(CONTACT_NAME, CONTACT_PHONE, CONTACT_MAIL).get(0));
	}
	@Test(dependsOnMethods={"searchContact"})
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void editContact(){
		Assert.assertEquals(contactDao.getRowCount(), 1);
		Contact contact = contactDao.getAll(CONTACT_NAME, CONTACT_PHONE, CONTACT_MAIL).get(0);
		contact.setName(CONTACT_NAME2);
		contact.setPhone(CONTACT_PHONE2);
		contact.setMail(CONTACT_MAIL2);
		contact = contactDao.merge(contact);
		Assert.assertEquals(contactDao.getRowCount(), 1);
		Assert.assertNotNull(contactDao.getAll(CONTACT_NAME2,CONTACT_PHONE2,CONTACT_MAIL2).get(0));
		contact.setName(CONTACT_NAME);
		contact.setPhone(CONTACT_PHONE);
		contact.setMail(CONTACT_MAIL);
		contactDao.merge(contact);
	}
	
	@Test(dependsOnMethods = { "editContact" })
	@Rollback(value = false)
	@Transactional(value = TxType.NEVER)
	public void removeContact() throws ElementCannotBeRemovedException {
		Assert.assertEquals(contactDao.getRowCount(), 1);
		Contact contact = contactDao.getAll(CONTACT_NAME, CONTACT_PHONE, CONTACT_MAIL).get(0);
		contactDao.makeTransient(contact);
		Assert.assertEquals(contactDao.getRowCount(), 0);
	}

	@AfterClass(alwaysRun = true)
	@Rollback(value = false)
	public void clearDatabase() throws ElementCannotBeRemovedException {
		deleteFromTables(new String[] { "contacts" });
		deleteFromTables(new String[] { "companies" });
		Assert.assertEquals(contactDao.getRowCount(), 0);
		Assert.assertEquals(companyDao.getRowCount(), 0);
	}

	public static Contact createTestContact(String name, String phone, String mail, Company company) {
		Contact contact = new Contact();
		contact.setName(name);
		contact.setMail(mail);
		contact.setPhone(phone);
		contact.setCompany(company);
		return contact;
	}
}
