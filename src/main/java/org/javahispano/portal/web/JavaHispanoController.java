package org.javahispano.portal.web;

import org.javahispano.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main controller
 * 
 * @author Sergi Almar i Graupera
 */
@Controller
public class JavaHispanoController {

	@Autowired
	private ContentService contentService;

	/**
	 * Shows the welcome page
	 * 
	 * @param the model to be rendered
	 */
	@RequestMapping("/index")
	public void index(Model model) {
		model.addAttribute("contentList", contentService.getAllContent());
	}
}

