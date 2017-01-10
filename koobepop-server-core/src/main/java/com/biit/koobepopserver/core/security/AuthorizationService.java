package com.biit.koobepopserver.core.security;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.biit.usermanager.entity.IGroup;
import com.biit.usermanager.entity.IRole;
import com.biit.usermanager.entity.IUser;
import com.biit.usermanager.security.IActivity;
import com.biit.usermanager.security.IAuthorizationService;
import com.biit.usermanager.security.exceptions.UserManagementException;

@Component
public class AuthorizationService implements IAuthorizationService<Long, Long, Long> {

	@Override
	public Set<IUser<Long>> getAllUsers() throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IUser<Long>> getAllUsers(IGroup<Long> group) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGroup<Long> getOrganization(Long organizationId) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGroup<Long> getOrganization(String organizationName) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IGroup<Long>> getAllAvailableOrganizations() throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRole<Long> getRole(Long roleId) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRole<Long> getRole(String roleName) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IActivity> getRoleActivities(IRole<Long> role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IRole<Long>> getUserGroupRoles(IGroup<Long> group) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IGroup<Long>> getUserGroups(IUser<Long> user) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IGroup<Long>> getUserOrganizations(IUser<Long> user) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IGroup<Long>> getUserOrganizations(IUser<Long> user, IGroup<Long> site) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IRole<Long>> getUserRoles(IUser<Long> user) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IRole<Long>> getUserRoles(IUser<Long> user, IGroup<Long> organization) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IRole<Long>> getAllRoles(IGroup<Long> organization) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthorizedActivity(IUser<Long> user, IActivity activity) throws UserManagementException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAuthorizedActivity(IUser<Long> user, IGroup<Long> organization, IActivity activity) throws UserManagementException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<IUser<Long>> getUsers(IRole<Long> role, IGroup<Long> organization) throws UserManagementException {
		// TODO Auto-generated method stub
		return null;
	}

}
