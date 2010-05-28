package org.javahispano.portal.data.ibatis;

import java.util.List;

import org.javahispano.portal.data.RoleDao;
import org.javahispano.portal.domain.account.Role;
import org.springframework.stereotype.Repository;

/**
* iBatis 2 implementation of the role DAO 
*
* @author Xavier Lluch
*/
@Repository
public class SqlMapRoleDao extends SqlMapGenericDao<Long, Role> implements RoleDao {

	public SqlMapRoleDao() {
		super(Role.class);
	}
	
	public Role getByDescription(String description) {
		return (Role) sqlMapClientTemplate.queryForObject(getNamespace() + "getRoleByDescription", description);
	}

	public Role getByName(String name) {
		return (Role) sqlMapClientTemplate.queryForObject(getNamespace() + "getRoleByName", name);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getByAccountId(long accountId) {
		return sqlMapClientTemplate.queryForList(this.getNamespace() + "getRolesByAccountId" , accountId);
	}	
}
