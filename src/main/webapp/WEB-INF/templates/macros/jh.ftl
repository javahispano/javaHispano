[#ftl]
<#--
 * jh.ftl
 *
 * Spring's FreeMarker support will automatically make this file and therefore
 * all macros within it available to any application using Spring's
 * FreeMarkerConfigurer.
 *
 * To take advantage of these macros, the "exposeSpringMacroHelpers" property
 * of the FreeMarker class needs to be set to "true". 
 *
 * @author Sergi Almar i Graupera 
 *
 -->
 
 <#--
 * prettifyMe
 *
 * Macro to display a pretty date (like 20 hours ago)
 * @param date the date to prettify
 -->
 
[#macro prettifyMe date]
    [#if date??]
    	${prettyTime.format(date)}
    [/#if]
[/#macro]