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

import com.biit.koobepopserver.persistence.dao.IBrandDao;
import com.biit.koobepopserver.persistence.entity.Brand;

@Repository
public class BrandDao extends AnnotatedGenericDao<Brand, Long> implements IBrandDao{

	public BrandDao() {
		super(Brand.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
	public List<Brand> getAll(String name) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
		Root<Brand> contact = criteriaQuery.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (name != null && name.length() > 0) {
			predicates.add(criteriaBuilder.equal(contact.get("name"), name));
		}
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {})));

		try {
			return getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<Brand>();
	

		}
	}

}
