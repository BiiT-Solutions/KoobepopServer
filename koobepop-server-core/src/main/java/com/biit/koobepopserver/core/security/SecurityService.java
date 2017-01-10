package com.biit.koobepopserver.core.security;

import java.text.Normalizer.Form;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biit.koobepopserver.logger.KoobepopLogger;
import com.biit.usermanager.entity.IGroup;
import com.biit.usermanager.entity.IRole;
import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IActivity;
import com.biit.usermanager.security.IAuthenticationService;
import com.biit.usermanager.security.IAuthorizationService;
import com.biit.usermanager.security.exceptions.UserManagementException;

@Component
public class SecurityService implements ISecurityService {

	@Autowired
	private IAuthenticationService<Long, Long> authenticationService;

	@Autowired
	private IAuthorizationService<Long, Long, Long> authorizationService;

	@Override
	public void reset() {
		getAuthorizationService().reset();
		getAuthenticationService().reset();
	}

	public IGroup<Long> getDefaultGroup(IUser<Long> user) {
		try {
			return getAuthenticationService().getDefaultGroup(user);
		} catch (UserManagementException e) {
			KoobepopLogger.errorMessage(this.getClass().getName(), e);
		}
		return null;
	}

	public boolean isEditable(Form form) {
		return false;
	}

	@Override
	public Set<IActivity> getActivitiesOfRoles(List<IRole<Long>> roles) {
		Set<IActivity> activities = new HashSet<>();
		for (IRole<Long> role : roles) {
			activities.addAll(getAuthorizationService().getRoleActivities(role));
		}
		return activities;
	}

	@Override
	public boolean isUserAuthorizedInAnyOrganization(IUser<Long> user, IActivity activity) throws UserManagementException {
		// Check isUserAuthorizedActivity (own permissions)
		if (getAuthorizationService().isAuthorizedActivity(user, activity)) {
			return true;
		}

		// Get all organizations of user
		Set<IGroup<Long>> organizations = getUserOrganizations(user);
		for (IGroup<Long> organization : organizations) {
			if (isAuthorizedActivity(user, organization, activity)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IUser<Long> getUserById(Long userId) {
		if (userId != null) {
			try {
				return getAuthenticationService().getUserById(userId);
			} catch (UserManagementException e) {
				KoobepopLogger.errorMessage(this.getClass().getName(), e);
			}
		}
		return null;
	}

	@Override
	public IGroup<Long> getOrganization(IUser<Long> user, Long organizationId) {
		try {
			Set<IGroup<Long>> organizations = getUserOrganizations(user);
			for (IGroup<Long> organization : organizations) {
				if (organization.getId().equals(organizationId)) {
					return organization;
				}
			}
		} catch (UserManagementException e) {
			KoobepopLogger.errorMessage(this.getClass().getName(), e);
		}

		return null;
	}

	@Override
	public Set<IGroup<Long>> getAllOrganizations() throws UserManagementException {
		return getAuthorizationService().getAllAvailableOrganizations();
	}

	@Override
	public Set<IGroup<Long>> getUserOrganizations(IUser<Long> user) throws UserManagementException {
		return getAuthorizationService().getUserOrganizations(user);
	}

	@Override
	public Set<IGroup<Long>> getUserOrganizationsWhereIsAuthorized(IUser<Long> user, IActivity activity) {
		Set<IGroup<Long>> organizations = new HashSet<>();
		try {
			organizations = getUserOrganizations(user);
			Iterator<IGroup<Long>> itr = organizations.iterator();
			while (itr.hasNext()) {
				IGroup<Long> organization = itr.next();
				if (!isAuthorizedActivity(user, organization, activity)) {
					itr.remove();
				}
			}
		} catch (UserManagementException e) {
			KoobepopLogger.errorMessage(this.getClass().getName(), e);
		}
		return organizations;
	}

	@Override
	public boolean isAuthorizedActivity(IUser<Long> user, IGroup<Long> organization, IActivity activity) throws UserManagementException {
		return getAuthorizationService().isAuthorizedActivity(user, organization, activity);
	}

	@Override
	public boolean isAuthorizedActivity(IUser<Long> user, IActivity activity) throws UserManagementException {
		return getAuthorizationService().isAuthorizedActivity(user, activity);
	}

	@Override
	public IAuthorizationService<Long, Long, Long> getAuthorizationService() {
		return authorizationService;
	}

	public void setAuthenticationService(IAuthenticationService<Long, Long> authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public IAuthenticationService<Long, Long> getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthorizationService(IAuthorizationService<Long, Long, Long> authorizationService) {
		this.authorizationService = authorizationService;
	}

	@Override
	public Set<IUser<Long>> getUsers(IRole<Long> role, IGroup<Long> organization) {
		try {
			return getAuthorizationService().getUsers(role, organization);
		} catch (UserManagementException e) {
			KoobepopLogger.errorMessage(this.getClass().getName(), e);
		}
		return new HashSet<>();
	}

	@Override
	public IUser<Long> getUserByEmail(String email) throws UserManagementException {
		return authenticationService.getUserByEmail(email);
	}

	@Override
	public IGroup<Long> getOrganization(Long organizationId) throws UserManagementException {
		return authorizationService.getOrganization(organizationId);
	}

	@Override
	public IGroup<Long> getOrganization(String organizationName) throws UserManagementException {
		return authorizationService.getOrganization(organizationName);
	}

	@Override
	public IUser<Long> updatePassword(IUser<Long> user, String newPassword) throws UserManagementException {
		IUser<Long> userChanged = authenticationService.updatePassword(user, newPassword);
		return userChanged;
	}

	@Override
	public IUser<Long> updateUser(IUser<Long> user) throws UserManagementException {
		return authenticationService.updateUser(user);
	}

	@Override
	public Set<IUser<Long>> getAllUsers(IGroup<Long> organization) throws UserManagementException {
		if (organization != null) {
			return getAuthorizationService().getAllUsers(organization);
		}
		return new HashSet<>();
	}

}