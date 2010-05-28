[#ftl]
[#include "./includes.ftl"]
<div>
	[@security.authorize access="isAnonymous()"]
       <a href="[@spring.url "/login"/]">[@spring.messageText "section.account.login", "Login"/]</a>
    [/@security.authorize]
    
	[@security.authorize access="isAuthenticated()"]
       <a href="[@spring.url "/user/profile"/]">[@security.authentication property="principal.username"/]</a>
       <a href="[@spring.url "/jh_security_logout"/]">[@spring.messageText "app.logout", "Logout"/]</a>
    [/@security.authorize]
</div>        