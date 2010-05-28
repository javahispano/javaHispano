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
 * @author Xavier Lluch Urrutia
 */
@Controller
@RequestMapping(value = { "/user" , "/admin"})
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * Custom handler for displaying current user's profile.
	 *
	 * @param model the model to add attributes for the view
	 * @return the logical view name
	 */
	@RequestMapping("/profile")
	public String showPublicUserProfile(Model model) {
		Account account = accountService.getCurrentUser();

		if (account != null) {
			model.addAttribute(account);
			model.addAttribute("profileOwner", true);
		}
		
		return "user/profile";
	}
	
	/**
	 * Custom handler for displaying a user profile.
	 *
	 * @param userName the userName of the user to display
	 * @param Model the model to add attributes for the view
	 * @return the logical view name
	 */
	@RequestMapping("/profile/{userName}")
	public String showPublicUserProfile(@PathVariable String userName, Model model) {
		
		Account account;
		boolean profileOwner = false;
		if (userName != null) {
			account = accountService.getAccountByUsername(userName);
			if (account != null) {
				Account currentUser = accountService.getCurrentUser();
				if (currentUser != null && account.getId().equals(currentUser.getId())) {
					profileOwner = true;
				}
				model.addAttribute(account);
				model.addAttribute("profileOwner", profileOwner);
			}
		}
		return "user/profile";
	}
}