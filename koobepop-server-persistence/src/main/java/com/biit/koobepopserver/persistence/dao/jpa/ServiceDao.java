package com.biit.koobepopserver.persistence.dao.jpa;

import org.springframework.stereotype.Repository;

import com.biit.koobepopserver.persistence.dao.IServiceDao;
import com.biit.koobepopserver.persistence.entity.Service;

@Repository
public class ServiceDao extends AnnotatedGenericDao<Service, Long> implements IServiceDao {

	public ServiceDao(){
		super(Service.class);
	}


}
