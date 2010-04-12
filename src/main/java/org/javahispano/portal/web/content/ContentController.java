package org.javahispano.portal.web.content;

import org.javahispano.portal.domain.content.Comment;
import org.javahispano.portal.domain.content.Content;
import org.javahispano.portal.service.AccountService;
import org.javahispano.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller that handles all non-form content-related URLs
 * 
 * @author Sergi Almar i Graupera
 */

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private ContentService contentService;
	
	/**
	 * Custom handler for displaying a content.
	 *
	 * @param contentId the id of the content to display
	 * @param Model the model to add attributes for the view
	 * @return the logical view name
	 */
	@RequestMapping("/{contentId}/**/*")
	public String displayContent(@PathVariable("contentId") long contentId,
						      	 Model model) {
		
		Content content = contentService.getContentById(contentId);
		model.addAttribute(content);
		
		return "content/show";
	}
	
	/**
	 * Custom handler for adding a new comment to a content
	 *
	 * @param contentId the id of the content to add the comment
	 * @param Model the model to add attributes for the view
	 * @return the logical view name
	 */
	@RequestMapping(value="/{contentId}/comment", method = RequestMethod.POST)
	public String addNewComment(@PathVariable("contentId") long contentId,
								@RequestParam("comment") String commentBody,
						      	 Model model) {
		
		Content content = contentService.getContentById(contentId);
		
		// Check that content really exists
		if(content != null) {
			Comment c = new Comment();
			c.setUser(accountService.getCurrentUser());
			c.setContent(content);
			c.setBody(commentBody);
			
			// Save the comment
			contentService.saveComment(c);
		}
				
		return "redirect:/content/" + content.getId() + "/" + content.getSluggedTitle();
	}
}
