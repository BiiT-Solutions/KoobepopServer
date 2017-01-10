package com.biit.koobepopserver.core.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biit.koobepopserver.core.security.ISecurityService;
import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IAuthenticationService;
import com.biit.webservice.rest.RestAuthorizationService;

@Component
public class KoobepopRestAuthorizationService extends RestAuthorizationService {

	@Autowired
	private ISecurityService securityService;
	
	@Override
	public Boolean checkSpecificAuthorization(IUser<Long> arg0) {
		return true;
	}

	@Override
	public IAuthenticationService<Long, Long> getAuthenticationService() {
		return securityService.getAuthenticationService();
	}

}