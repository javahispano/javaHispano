package org.javahispano.portal.converter;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.javahispano.portal.domain.content.Tag;
import org.junit.Before;
import org.junit.Test;

public class TagSetConverterTests {

	private StringToTagSetConverter tagSetConverter;
	private String tags;
	
	@Before
	public void setUp() {
		tagSetConverter = new StringToTagSetConverter();
		tags = "spring, javahispano, novedad";
	}
	
	@Test
	public void testTagSetConversion() {
		Set<Tag> tagSet = tagSetConverter.convert(tags);
		assertEquals(tagSet.size(), 3);
	}
}
