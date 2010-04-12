package org.javahispano.portal.service;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;

import org.javahispano.portal.domain.account.Account;
import org.javahispano.portal.domain.content.Comment;
import org.javahispano.portal.domain.content.Content;
import org.javahispano.portal.domain.content.Tag;
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
public class ContentServiceTests {

	@Autowired
	private ContentService contentService;
	private Content testContent;
	
	@Before
	public void setUp() {
		testContent = new Content();
		testContent.setTitle("Spring 2GX Day, 19 de Febrero - Madrid 2010");
		testContent.setBody("El próximo 19 de Febrero se va a celebrar el Spring 2GX Day en Madrid...");
		
		Tag tag1 = new Tag("evento");
		Tag tag2 = new Tag("spring");
		
		testContent.setTags(new HashSet<Tag>(Arrays.asList(tag1,tag2)));
	}
	
	@Test
	public void testGetContentById() {
		contentService.saveContent(testContent);
		
		Content content = contentService.getContentById(testContent.getId());
		assertNotNull(content);
	}
	
	@Test
	public void testSaveContent() {
		contentService.saveContent(testContent);
		assertNotNull(testContent.getId());
	}
	
	@Test
	public void testSaveAndModifyContent() {
		contentService.saveContent(testContent);
		assertNotNull(testContent.getId());
		contentService.saveContent(testContent);
		assertNotNull(testContent.getModificationDate());
	}
	
	@Test
	public void testSaveComment() {
		contentService.saveContent(testContent);
		
		Comment comment = new Comment();
		comment.setBody("Me apunto al evento!");
		comment.setContent(testContent);
		comment.setUser(new Account());
		
		contentService.saveComment(comment);
		assertNotNull(comment.getId());
	}
}
