package com.biit.koobepopserver.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biit.koobepopserver.persistence.dao.IContactDao;
import com.biit.koobepopserver.persistence.entity.Contact;

@Repository
public class ContactDao extends AnnotatedGenericDao<Contact, Long> implements IContactDao {

	public ContactDao() {
		super(Contact.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
	public List<Contact> getAll(String name, String phone, String mail) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
		Root<Contact> contact = criteriaQuery.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (name != null && name.length() > 0) {
			predicates.add(criteriaBuilder.equal(contact.get("name"), name));
		}
		if (phone != null && phone.length() > 0) {
			predicates.add(criteriaBuilder.equal(contact.get("phone"), phone));
		}
		if (mail != null && mail.length() > 0) {
			predicates.add(criteriaBuilder.equal(contact.get("mail"), mail));
		}
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {})));

		try {
			return getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<Contact>();
		}
	}
}
