[#ftl]
[#include "inc/includes.ftl"]
<html>
	<head>
		<title></title>
	</head>
	<body>
	<form action="[@spring.url "/j_spring_security_check"/]" method="post">
		<fieldset id="login">
			<dl>
				<dt><label>[@spring.messageText "account.field.username", "Nombre de usuario" /]</label></dt>
				<dd>
            		<input type="text" class="title" name="j_username" id="j_username" maxlength="40" size="20" />
            	</dd>
            	<dt><label>[@spring.messageText "account.field.password", "Contraseña" /]</label></dt>
            	<dd>
            		<input type="password" class="title" name="j_password" id="j_password" maxlength="16" size="20"/>
            	<dd>
            </dl>
        </fieldset>
    </form>

	</body>
</html>