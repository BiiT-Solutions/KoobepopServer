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

import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.entity.Company;

@Repository
public class CompanyDao extends AnnotatedGenericDao<Company, Long> implements ICompanyDao {

	public CompanyDao() {
		super(Company.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
	public List<Company> getAll(String name, String country) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
		Root<Company> company = criteriaQuery.from(getEntityClass());
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (name != null && name.length() > 0) {
			predicates.add(criteriaBuilder.equal(company.get("name"), name));
		}
		if (country != null && country.length() > 0) {
			predicates.add(criteriaBuilder.equal(company.get("country"), country));
		}
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {})));

		try {
			return getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<Company>();
		}
	}

	
}
