package org.javahispano.portal.web.account;

import javax.servlet.http.HttpServletRequest;

import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Form controller used to edit Account
 * 
 * @author Xavier Lluch Urrutia
 */
@Controller
@RequestMapping("/signup")
public class SignupAccountController {

	@Autowired
	private AccountService accountService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Custom handler for displaying the user profile form (new / edit).
	 * 
	 * @return the logical view name
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String showAccountForm(Model model, HttpServletRequest request) {
//		Account signupAccount = (Account) request.getAttribute("signupaccount");
//		model.addAttribute("account", signupAccount);
		return "/signup";
	}

	/**
	 * Custom handler the processing of the user profile form submission.
	 * 
	 * @return a string with a redirection to the edited profile
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processContentSubmit(@ModelAttribute Account account, 
			BindingResult result) {
		if (result.hasErrors()) {
			return "/signup";
		} else {
			accountService.saveAccount(account);
			return "redirect:/user/profile";
		}
	}
}
