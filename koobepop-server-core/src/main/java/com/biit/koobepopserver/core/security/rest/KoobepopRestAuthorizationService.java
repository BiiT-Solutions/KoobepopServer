package com.biit.koobepopserver.core.security.rest;

import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IAuthenticationService;
import com.biit.webservice.rest.RestAuthorizationService;

public class KoobepopRestAuthorizationService extends RestAuthorizationService {

	@Override
	public Boolean checkSpecificAuthorization(IUser<Long> arg0) {
		return true;
	}

	@Override
	public IAuthenticationService<Long, Long> getAuthenticationService() {
		return null;
	}

}