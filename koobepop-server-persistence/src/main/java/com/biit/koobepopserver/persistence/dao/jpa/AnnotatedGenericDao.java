package com.biit.koobepopserver.persistence.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biit.persistence.dao.IJpaGenericDao;
import com.biit.persistence.dao.jpa.GenericDao;
import com.biit.persistence.entity.exceptions.ElementCannotBeRemovedException;

public class AnnotatedGenericDao<EntityClass, PrimaryKeyClass extends Serializable> extends GenericDao<EntityClass, PrimaryKeyClass> implements
		IJpaGenericDao<EntityClass, PrimaryKeyClass> {

	@PersistenceContext(unitName = "defaultPersistenceUnit")
	@Qualifier(value = "koobepopserverProjectManagerFactory")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public AnnotatedGenericDao(Class<EntityClass> entityClass) {
		super(entityClass);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public EntityClass makePersistent(EntityClass entity) {
		return super.makePersistent(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public void makeTransient(EntityClass entity) throws ElementCannotBeRemovedException {
		super.makeTransient(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public EntityClass merge(EntityClass entity) {
		return super.merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public EntityClass get(PrimaryKeyClass id) {
		return super.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
	public int getRowCount() {
		return super.getRowCount();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public List<EntityClass> getAll() {
		return super.getAll();
	}

	@Override
	public void evictAllCache() {
		super.evictAllCache();
		getEntityManager().clear();
	}

	@Transactional
	public void clearHibernateCache() {
		SessionFactory sessionFactory = getEntityManager().unwrap(Session.class).getSessionFactory();
		sessionFactory.getCache().evictEntityRegions();
		sessionFactory.getCache().evictCollectionRegions();
		sessionFactory.getCache().evictDefaultQueryRegion();
		sessionFactory.getCache().evictQueryRegions();
		sessionFactory.getCache().evictNaturalIdRegions();
		sessionFactory.getCache().evictAllRegions();
	}
	

}
