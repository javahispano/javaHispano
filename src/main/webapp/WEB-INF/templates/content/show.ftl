[#ftl]
[#include "../inc/includes.ftl"]
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h2>${content.title?html}</h2>
		${content.body!}
		<dl>Tags:
			[#list content.tags as tag]
			<dd>${tag.name} x ${tag.timesUsed}</dd>
            [/#list]	
		</dl>
		
		<h3>[@spring.messageText "content.field.comments", "Comentarios"/]</h3>
		[#list content.comments as comment]
			${comment.creationDate?datetime} - ${comment.body?html}<br/>
        [/#list]
		
		<form action="[@spring.url "/content/${content.id}/comment"/]" method="post">
			<textarea name="comment"></textarea>
			<button type="submit">[@spring.messageText "common.button.send", "Enviar"/]</button>
		</form>
	</body>
</html>
