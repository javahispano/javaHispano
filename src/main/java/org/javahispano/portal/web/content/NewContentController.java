package org.javahispano.portal.web.content;

import org.javahispano.portal.domain.content.Content;
import org.javahispano.portal.service.AccountService;
import org.javahispano.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Form controller used to add / edit <code>Content</code>
 * 
 * @author Sergi Almar i Graupera
 */
@Controller
@RequestMapping(value = { "/content/new", "/content/edit" })
public class NewContentController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ContentService contentService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Exposes in the model the form object to be rendered. In case the
	 * parameter 'id' is provided in the request, the content with the specified
	 * id will be rendered (if found), otherwise a new content will be provided
	 * 
	 * @param contentId
	 *            the id of the content to display
	 * @return the content to be rendered to the view
	 */
	@ModelAttribute
	public Content exposeContent(
			@RequestParam(value = "id", required = false) Long contentId) {
		Content content = null;

		if (contentId != null) {
			content = contentService.getContentById(contentId);
		}

		return content != null ? content : new Content();
	}

	/**
	 * Custom handler for displaying the content form (new / edit).
	 * 
	 * @return the logical view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showContentForm() {
		return "content/form";
	}

	/**
	 * Custom handler the processing of the content form submission.
	 * 
	 * @return a string with a redirection to the new content
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processContentSubmit(@ModelAttribute Content content,
			BindingResult result) {

		if (result.hasErrors()) {
			return "content/form";
		} else {
			content.setUser(accountService.getCurrentUser());
			this.contentService.saveContent(content);
			return "redirect:/content/" + content.getId() + "/"
					+ content.getSluggedTitle();
		}
	}
}
