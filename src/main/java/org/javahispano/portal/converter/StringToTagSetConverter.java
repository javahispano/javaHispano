package org.javahispano.portal.converter;

import java.util.HashSet;
import java.util.Set;

import org.javahispano.portal.domain.content.Tag;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter to convert from String to Set<Tag>
 * 
 * @author Sergi Almar i Graupera
 */
public class StringToTagSetConverter implements Converter<String, Set<Tag>>{
	
	public static final String TAG_SEPARATION = ","; 
	
	public Set<Tag> convert(String tags) {
		Set<Tag> tagSet = new HashSet<Tag>();
		
		String [] tagArray = tags.split(TAG_SEPARATION);
		for(String tag : tagArray) {
			tagSet.add(new Tag(tag.trim()));
		}
		
		return tagSet;
	}
}
