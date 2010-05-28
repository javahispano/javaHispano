[#ftl]
[#include "inc/includes.ftl"]
<html>
	<head>
		<title></title>
	</head>
	<body>
	    [#include "inc/headbar.ftl"]
	    
		<h1>javaHispano v3.0 - News</h1>
		<a href="[@spring.url "/content/new"/]">[@spring.messageText "section.content.new", "Publicar contenido"/]</a>
		
		<h2>Contenido</h2>
		
		[#list contentList as content]
			<a href="[@spring.url "/content/${content.id?c}/${content.sluggedTitle!}"/]">${content.title?html}</a>
        [/#list]						
	</body>
</html>