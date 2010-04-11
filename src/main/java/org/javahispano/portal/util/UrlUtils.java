package org.javahispano.portal.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

/**
 * Url utils
 * 
 * @author Sergi Almar i Graupera
 */
public class UrlUtils {
	   
	   private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	   private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	   /**
	    * Converts an input URL string representation to a pretty URL string 
	    * 
	    * @param input the url to transform
	    * @return the pretty url string representation 
	    */
	   public static String slug(String input) {
	     String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	     String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	     String slug = NONLATIN.matcher(normalized).replaceAll("");
	     return slug.toLowerCase();
	   }

}
