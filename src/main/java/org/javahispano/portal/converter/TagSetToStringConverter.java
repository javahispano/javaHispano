package org.javahispano.portal.converter;

import java.util.Set;

import org.javahispano.portal.domain.content.Tag;
import org.springframework.core.convert.converter.Converter;


/**
 * Converter to convert from Set<Tag> to String
 * 
 * @author Sergi Almar i Graupera
 */
public class TagSetToStringConverter implements Converter<Set<Tag>, String>{
	
	public static final String TAG_SEPARATION = ", "; 
	
	public String convert(Set<Tag> tags) {
		
		StringBuilder tagBuilder = new StringBuilder();;
		
		for(Tag tag : tags) {
			tagBuilder.append(tag.getName());
			tagBuilder.append(TAG_SEPARATION);
		}
		
		return tagBuilder.toString();
	}
}
