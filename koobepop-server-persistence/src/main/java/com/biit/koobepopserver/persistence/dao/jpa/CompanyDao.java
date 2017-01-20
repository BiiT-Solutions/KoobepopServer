package com.biit.koobepopserver.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biit.koobepopserver.persistence.dao.ICompanyDao;
import com.biit.koobepopserver.persistence.entity.Brand;
import com.biit.koobepopserver.persistence.entity.Company;
import com.biit.koobepopserver.persistence.entity.Product;
import com.biit.koobepopserver.persistence.entity.Service;

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
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
	public List <Company> getAll (String country, String brandName, String productName, String serviceName){
		boolean countryIsVoid = (country==null || country.length() <= 0);
		boolean brandIsVoid = (brandName==null || brandName.length() <= 0);
		boolean productIsVoid = (productName==null || productName.length() <= 0);
		boolean serviceIsVoid = (serviceName==null || serviceName.length() <= 0);
		if( countryIsVoid && brandIsVoid && productIsVoid && serviceIsVoid){
			return new ArrayList<Company>();
		}
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();				
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
		Root<Company> company = criteriaQuery.from(getEntityClass());
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (country != null && country.length() > 0) {	
			predicates.add(criteriaBuilder.equal(company.get("country"), country));
		}
		if (brandName != null && brandName.length() > 0) {
			Join<Company,Brand> brand = company.join("brands");
			predicates.add(criteriaBuilder.equal(brand.get("name"),brandName));
		}
		if (brandName != null && brandName.length() > 0) {
			Join<Company,Product> product = company.join("products");
			predicates.add(criteriaBuilder.equal(product.get("name"),productName));
		}
		if (brandName != null && brandName.length() > 0) {
			Join<Company,Service> service = company.join("services");
			predicates.add(criteriaBuilder.equal(service.get("name"),serviceName));
		}
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {})));
		try {
			return getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (NoResultException nre) {
			return new ArrayList<Company>();
		}
	}
	
}
