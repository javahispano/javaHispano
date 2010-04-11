package org.javahispano.portal.data.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.javahispano.portal.data.AccountDao;
import org.javahispano.portal.domain.account.Account;
import org.springframework.stereotype.Repository;

/**
* iBatis 2 implementation of the account DAO 
*
* @author Sergi Almar i Graupera
*/
@Repository
public class SqlMapAccountDao extends SqlMapGenericDao<Long, Account> implements AccountDao {
	
	public SqlMapAccountDao() {
		super(Account.class);
	}

	public Account getByEmail(String email) {
		return (Account) sqlMapClientTemplate.queryForObject(getNamespace() + "getAccountByEmail", email);
	}
	
	public Account getByUsername(String username) {
		return (Account) sqlMapClientTemplate.queryForObject(getNamespace() + "getAccountByUsername", username);
	}
	
	public void updateLastLogin(long accountId, Date lastLogin) {
		 Map<String,Object> params = new HashMap<String, Object>();
	     params.put("userId", accountId);
	     params.put("lastLogin", lastLogin);
		
		sqlMapClientTemplate.update(getNamespace() + "updateLastLogin", params);
	}
}
