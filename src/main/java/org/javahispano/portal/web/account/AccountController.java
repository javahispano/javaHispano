package org.javahispano.portal.web.account;

import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that handles all non-form account-related URLs
 * 
 * @author Sergi Almar i Graupera
 */
@Controller
@RequestMapping("/user")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * Custom handler for displaying the profile of a user.
	 *
	 * @param username the username of the user to display
	 * @param Model the model to add attributes for the view
	 * @return the logical view name
	 */
	@RequestMapping("/{username}")
	public String showPublicUserProfile(@PathVariable("username") String username,
										Model model) {
		
		Account account = accountService.getAccountByUsername(username);
		
		model.addAttribute(account);
		
		return "user/profile";
	}
	
}
