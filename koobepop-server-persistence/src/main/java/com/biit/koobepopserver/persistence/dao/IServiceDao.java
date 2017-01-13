package com.biit.koobepopserver.persistence.dao;

import java.util.List;

import com.biit.koobepopserver.persistence.entity.Service;
import com.biit.persistence.dao.IJpaGenericDao;

public interface IServiceDao  extends IJpaGenericDao<Service, Long> {

	List<Service> getAll(String name);

}
