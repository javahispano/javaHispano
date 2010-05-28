[#ftl]
[#include "../inc/includes.ftl"]
<html>
	<head>
		<title></title>
	</head>
	<body>
		[#include "../inc/headbar.ftl"]
		
		<h2>${content.title?html}</h2>
		<h3>Posted by <a href="[@spring.url "/user/profile/${content.user.username}"/]">${content.user.username}</a></h3> 
<!--		<h3>Posted by ${content.user.username}</h3> -->
		${content.body!}
		<dl>Tags:
			[#list content.tags as tag]
			<dd>${tag.name} x ${tag.timesUsed}</dd>
            [/#list]	
		</dl>
		
		<h3>[@spring.messageText "content.field.comments", "Comentarios"/]</h3>
		[#list content.comments as comment]
			[@jh.prettifyMe comment.creationDate /] - ${comment.body?html}<br/>
        [/#list]
		
		[@spring.messageText "content.comment.new", "Añadir comentario"/]</h3>
		<form action="[@spring.url "/content/${content.id}/comment"/]" method="post">
			<textarea name="comment"></textarea>
			<button type="submit">[@spring.messageText "common.button.send", "Enviar"/]</button>
		</form>
	</body>
</html>
