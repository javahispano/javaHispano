[#ftl]
[#include "../inc/includes.ftl"]
<html>
	<head>
		<title>${account.username}'s profile</title>
	</head>
	<body>
	    [#include "../inc/headbar.ftl"]
	    
		<h2>${account.username}'s profile</h2>
		<img src="http://www.gravatar.com/avatar/${account.gravatar}?d=monsterid" alt="${account.username}'s foto" />
		[#if profileOwner]
			<a href="[@spring.url "/user/profile/edit"/]">
			[@spring.messageText "account.edit", "Editar"/]</a><br/>
		[/#if]<br/>
		
		Username ${account.username}<br/>
	   	Email ${account.email}<br/>
	   	OpenId ${account.openid}<br/>
	   	Name ${account.firstName}<br/>
	   	Lastname ${account.lastName}<br/>
	   	
	   	[#if account.linkedIn??]
	   		LinkeIn url <a href="${account.linkedIn}">${account.linkedIn}</a><br/>
	   	[/#if]
	   	
	   	[#if account.twitter??]
	   		Twitter <a href="http://www.twitter.com/${account.twitter}">${account.twitter}</a><br/>
		[/#if]
	</body>
</html>
