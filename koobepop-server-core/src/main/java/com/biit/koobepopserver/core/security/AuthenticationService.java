package com.biit.koobepopserver.core.security;

import org.springframework.stereotype.Component;

import com.biit.usermanager.entity.IGroup;
import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IAuthenticationService;
import com.biit.usermanager.security.exceptions.AuthenticationRequired;
import com.biit.usermanager.security.exceptions.InvalidCredentialsException;
import com.biit.usermanager.security.exceptions.UserManagementException;

@Component
public class AuthenticationService implements IAuthenticationService<Long, Long> {

	public AuthenticationService() {
	}

	@Override
	public IUser<Long> authenticate(String userMail, String password) throws UserManagementException, AuthenticationRequired, InvalidCredentialsException {

		return null;
	}

	@Override
	public IGroup<Long> getDefaultGroup(IUser<Long> user) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUser<Long> getUserByEmail(String userEmail) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUser<Long> getUserById(long userId) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInGroup(IGroup<Long> group, IUser<Long> user) throws UserManagementException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IUser<Long> updatePassword(IUser<Long> user, String plainTextPassword) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUser<Long> updateUser(IUser<Long> user) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
