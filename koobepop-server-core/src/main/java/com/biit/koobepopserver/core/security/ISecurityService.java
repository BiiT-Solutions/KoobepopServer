package com.biit.koobepopserver.core.security;

import java.util.List;
import java.util.Set;

import com.biit.usermanager.entity.IGroup;
import com.biit.usermanager.entity.IRole;
import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IActivity;
import com.biit.usermanager.security.IAuthenticationService;
import com.biit.usermanager.security.IAuthorizationService;
import com.biit.usermanager.security.exceptions.UserManagementException;

public interface ISecurityService {

	IGroup<Long> getOrganization(IUser<Long> user, Long organizationId);

	Set<IGroup<Long>> getUserOrganizations(IUser<Long> user) throws UserManagementException;

	IAuthorizationService<Long, Long, Long> getAuthorizationService();

	IAuthenticationService<Long, Long> getAuthenticationService();

	void reset();

	boolean isUserAuthorizedInAnyOrganization(IUser<Long> user, IActivity activity) throws UserManagementException;

	Set<IActivity> getActivitiesOfRoles(List<IRole<Long>> roles);

	Set<IGroup<Long>> getUserOrganizationsWhereIsAuthorized(IUser<Long> user, IActivity activity);

	boolean isAuthorizedActivity(IUser<Long> user, IGroup<Long> organization, IActivity activity)
			throws UserManagementException;

	IUser<Long> getUserById(Long userId);

	boolean isAuthorizedActivity(IUser<Long> user, IActivity activity) throws UserManagementException;

	Set<IUser<Long>> getUsers(IRole<Long> role, IGroup<Long> organization);

	IUser<Long> getUserByEmail(String email) throws UserManagementException;

	IGroup<Long> getOrganization(String organizationName) throws UserManagementException;

	IGroup<Long> getOrganization(Long organizationId) throws UserManagementException;

	Set<IGroup<Long>> getAllOrganizations() throws UserManagementException;

	IUser<Long> updatePassword(IUser<Long> user, String newPassword) throws UserManagementException;
	
	IUser<Long> updateUser(IUser<Long> user) throws UserManagementException;

	Set<IUser<Long>> getAllUsers(IGroup<Long> organization) throws UserManagementException;

}

