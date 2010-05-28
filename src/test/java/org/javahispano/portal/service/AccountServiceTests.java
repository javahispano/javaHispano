package org.javahispano.portal.service;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.domain.account.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/app-config.xml",
		 "file:src/main/webapp/WEB-INF/spring/dao-config.xml",
		 "file:src/main/webapp/WEB-INF/spring/ibatis-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AccountServiceTests {
	
	@Autowired
	private AccountService accountService;
	private Account testAccount;
	
	// Roles constants
	private String ROLE_ADMIN = "ROLE_ADMIN";
	private String ROLE_USER  = "ROLE_USER";
	
	@Before
	public void setUp() throws Exception {
		testAccount = new Account();
		testAccount.setUsername("JohnDoe");
		testAccount.setFirstName("John");
		testAccount.setLastName("Doe");
		testAccount.setEmail("johndoe@javahispano.org");
		testAccount.setOpenid("http://www.openidurl.com");
		
		Role role1 = new Role();
		role1.setName(ROLE_ADMIN);
		role1.setDescription("Role for administrating features.");
		role1.setId(Long.valueOf(2));
		Role role2 = new Role();
		role2.setName(ROLE_USER);
		role2.setDescription("Role to interact with the system.");
		role2.setId(Long.valueOf(1));
		
		
		testAccount.setRoles(new HashSet<Role>(Arrays.asList(role1,role2)));
	}


	@Test
	public final void testGetAccountByEmail() {
		accountService.saveAccount(testAccount);
		
		Account account = accountService.getAccountByEmail(testAccount.getEmail());
		assertNotNull(account);
	}

	@Test
	public final void testGetAccountById() {
		accountService.saveAccount(testAccount);
		
		Account account = accountService.getAccountById(testAccount.getId());
		assertNotNull(account);
	}

	@Test
	public final void testGetAccountByOpenId() {
		accountService.saveAccount(testAccount);
		
		Account account = accountService.getAccountByOpenId(testAccount.getOpenid());
		assertNotNull(account);
	}

	@Test
	public final void testGetAccountByUsername() {
		accountService.saveAccount(testAccount);
		
		Account account = accountService.getAccountByUsername(testAccount.getUsername());
		assertNotNull(account);
	}

	@Test
	public final void testUpdateLastLogin() {
		accountService.updateLastLogin(testAccount, Calendar.getInstance().getTime());
		Date date1 = testAccount.getLastLogin();
		
		accountService.updateLastLogin(testAccount, Calendar.getInstance().getTime());
		Date date2 = testAccount.getLastLogin();
		
		assertTrue(date2.after(date1));
	}

	@Test
	public final void testSaveAccount() {
		testAccount.setFirstName("Johnny");
		testAccount.setLastName("Melenas");
		accountService.saveAccount(testAccount);
		
		assertNotNull(testAccount.getId());
		assertFalse(testAccount.isNew());
		assertTrue(testAccount.getId() instanceof Long);
		
		Account account = accountService.getAccountById(testAccount.getId());
		account.setFirstName("Pepito");
		accountService.saveAccount(account);
		account = accountService.getAccountById(account.getId());
		assertTrue((testAccount.getFirstName()).equals(account.getFirstName()));
	}

}
