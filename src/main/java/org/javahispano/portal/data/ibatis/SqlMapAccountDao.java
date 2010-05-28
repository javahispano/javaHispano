package org.javahispano.portal.data.ibatis;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.javahispano.portal.data.AccountDao;
import org.javahispano.portal.data.RoleDao;
import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.domain.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * iBatis 2 implementation of the account DAO
 * 
 * @author Sergi Almar i Graupera
 * @author Xavier Lluch
 */
@Repository("accountDao")
public class SqlMapAccountDao extends SqlMapGenericDao<Long, Account> implements
		AccountDao, UserDetailsService {

	@Autowired
	private RoleDao roleDao;

	public SqlMapAccountDao() {
		super(Account.class);
	}

	public Account getByEmail(String email) {
		return (Account) sqlMapClientTemplate.queryForObject(getNamespace()
				+ "getAccountByEmail", email);
	}

	public Account getByUsername(String username) {
		return (Account) sqlMapClientTemplate.queryForObject(getNamespace()
				+ "getAccountByUsername", username);
	}

	public Account getByOpenId(String openId) {
		Account account = (Account) sqlMapClientTemplate.queryForObject(
				getNamespace() + "getAccountByOpenId", openId);

		// Retrieve user roles
		if (account != null) {
			account.setRoles(new HashSet<Role>(roleDao.getByAccountId(account
					.getId())));
		}

		return account;
	}
	
	@Override
	public void save(Account account) {
		
		// If it's an existing account, let's delete all previous roles
		if(!account.isNew()) {
			this.unbindAccountRoles(account);
		}
		
		// Save/Update the user account
		super.save(account);
		
		// Bind roles to account
		bindAccountRoles(account);
	}

	public void updateLastLogin(long accountId, Date lastLogin) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", accountId);
		params.put("lastLogin", lastLogin);

		sqlMapClientTemplate.update(getNamespace() + "updateLastLogin", params);
	}

	public UserDetails loadUserByUsername(String openId)
			throws UsernameNotFoundException, DataAccessException {
		Account account = this.getByOpenId(openId);
		if (account == null) {
			throw new UsernameNotFoundException(openId);
		}
		return (UserDetails) account;
	}
	
	@SuppressWarnings("unchecked")
	private void bindAccountRoles(final Account account) {
		sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                //Start batch
                executor.startBatch();
         
                Map<String, Long> params = new HashMap<String, Long>();
                params.put("accountId", account.getId());
                
                for(Role role : account.getRoles()) {
                	params.put("roleId", role.getId());
                    executor.insert(getNamespace() + "bindAccountRole", params);
                }
                
                // Execute batch
                int rowsaffected = executor.executeBatch();
                return new Integer(rowsaffected);
            }
        });
	}
	
	private void unbindAccountRoles(final Account account) {
		sqlMapClientTemplate.delete(this.getNamespace() + "unbindAccountRoles", account.getId());
	}
}
