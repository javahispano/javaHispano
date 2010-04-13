package org.javahispano.portal.web.view;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.ocpsoft.pretty.time.PrettyTime;

/**
* FreeMarker view implementation to expose additional helpers
*
* @author Sergi Almar i Graupera
*/
public class FreeMarkerHelperView extends FreeMarkerView {
   
   public static final String PRETTY_TIME_HELPER = "prettyTime";
   
   private String prettyTimeHelperParameterName = PRETTY_TIME_HELPER;
      
   /** Creates a new instance of FreeMarkerHelperView */
   public FreeMarkerHelperView() {
       super();
   }
   
   @Override
   protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
	   model.put(prettyTimeHelperParameterName, new PrettyTime(new Locale("es")));    
	   //model.put(prettyTimeHelperParameterName, new PrettyTime(LocaleContextHolder.getLocale()));    
	   super.exposeHelpers(model, request);
   }
}

