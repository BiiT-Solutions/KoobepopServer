package com.biit.koobepopserver.core.security;

import java.util.List;
import java.util.Set;

import com.biit.usermanager.entity.IGroup;
import com.biit.usermanager.entity.IRole;
import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IActivity;
import com.biit.usermanager.security.IAuthenticationService;
import com.biit.usermanager.security.IAuthorizationService;
import com.biit.usermanager.security.exceptions.InvalidCredentialsException;
import com.biit.usermanager.security.exceptions.OrganizationDoesNotExistException;
import com.biit.usermanager.security.exceptions.UserDoesNotExistException;
import com.biit.usermanager.security.exceptions.UserManagementException;

public interface ISecurityService {

	IGroup<Long> getOrganization(IUser<Long> user, Long organizationId);

	Set<IGroup<Long>> getUserOrganizations(IUser<Long> user) throws UserManagementException, InvalidCredentialsException, UserDoesNotExistException;

	IAuthorizationService<Long, Long, Long> getAuthorizationService();

	IAuthenticationService<Long, Long> getAuthenticationService();

	void reset();

	boolean isUserAuthorizedInAnyOrganization(IUser<Long> user, IActivity activity) throws UserManagementException, InvalidCredentialsException, UserDoesNotExistException, OrganizationDoesNotExistException;

	Set<IActivity> getActivitiesOfRoles(List<IRole<Long>> roles) throws InvalidCredentialsException;

	Set<IGroup<Long>> getUserOrganizationsWhereIsAuthorized(IUser<Long> user, IActivity activity);

	boolean isAuthorizedActivity(IUser<Long> user, IGroup<Long> organization, IActivity activity)
			throws UserManagementException, OrganizationDoesNotExistException, InvalidCredentialsException, UserDoesNotExistException;

	IUser<Long> getUserById(Long userId);

	boolean isAuthorizedActivity(IUser<Long> user, IActivity activity) throws UserManagementException, InvalidCredentialsException, UserDoesNotExistException;

	Set<IUser<Long>> getUsers(IRole<Long> role, IGroup<Long> organization);

	IUser<Long> getUserByEmail(String email) throws UserManagementException, InvalidCredentialsException, UserDoesNotExistException;

	IGroup<Long> getOrganization(String organizationName) throws UserManagementException, OrganizationDoesNotExistException, InvalidCredentialsException;

	IGroup<Long> getOrganization(Long organizationId) throws UserManagementException, OrganizationDoesNotExistException, InvalidCredentialsException;

	Set<IGroup<Long>> getAllOrganizations() throws UserManagementException, InvalidCredentialsException;

	IUser<Long> updatePassword(IUser<Long> user, String newPassword) throws UserManagementException, InvalidCredentialsException, UserDoesNotExistException;
	
	IUser<Long> updateUser(IUser<Long> user) throws UserManagementException, InvalidCredentialsException, UserDoesNotExistException;

	Set<IUser<Long>> getAllUsers(IGroup<Long> organization) throws UserManagementException, OrganizationDoesNotExistException, InvalidCredentialsException;

}

