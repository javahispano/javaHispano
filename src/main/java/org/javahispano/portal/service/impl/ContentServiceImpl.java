package org.javahispano.portal.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.javahispano.portal.data.ContentDao;
import org.javahispano.portal.domain.content.Content;
import org.javahispano.portal.service.ContentService;
import org.javahispano.portal.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for all the content-related operations
 * 
 * @author Sergi Almar i Graupera
 */

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
	
	private ContentDao contentDao;
	
	@Autowired
	public ContentServiceImpl(ContentDao contentDao) {
		this.contentDao = contentDao;
	}
	
	@Transactional(readOnly=true)
	public Content getContentById(long contentId) {
		return contentDao.getById(contentId);
	}
	
	@Transactional(readOnly=true)
	public Collection<Content> getHighlightedContent() {
		Collection<Content> contents = contentDao.getHighlightedContent();
		return contents != null ? contents : Collections.<Content>emptyList();
	}
	
	@Transactional(readOnly=true)
	public Collection<Content> getAllContent() {
		Collection<Content> contents = contentDao.getAll();
		return contents != null ? contents : Collections.<Content>emptyList();
	}
	
	public void saveContent(Content content) {
		if(content.isNew()) {
			content.setCreationDate(new Date());
		} else {
			content.setModificationDate(new Date());
		}
		
		content.setSluggedTitle(UrlUtils.slug(content.getTitle()));
		contentDao.save(content);
	}
}
