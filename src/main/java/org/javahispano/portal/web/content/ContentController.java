package org.javahispano.portal.web.content;

import org.javahispano.portal.domain.content.Content;
import org.javahispano.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that handles all non-form content-related URLs
 * 
 * @author Sergi Almar i Graupera
 */

@Controller
@RequestMapping("/content")
public class ContentController {

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
}
