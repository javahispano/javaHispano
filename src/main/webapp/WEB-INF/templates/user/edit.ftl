[#ftl]
[#include "../inc/includes.ftl"]
<html>
	<head>
		<title>Edit ${account.username}'s profile - Edit</title>
	</head>
	<body>
	    [#include "../inc/headbar.ftl"]
	    
		<h2>${account.username}'s profile - Edit</h2><br/>
		<form method="post" action="">
			<div>
				<label for="username">[@spring.messageText "account.field.username", "Username"/]</label>
				[@spring.formInput "account.username", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>

			<div>
				<label for="email">[@spring.messageText "account.field.email", "email"/]</label>
				[@spring.formInput "account.email", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>	   	
			
		   	<div>
				<label for="openId">[@spring.messageText "account.field.openid", "OpenId"/]</label>
				[@spring.formInput "account.openid", "maxlength=\"300\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>	   	

		   	<div>
				<label for="firstName">[@spring.messageText "account.field.firstname", "FirstName"/]</label>
				[@spring.formInput "account.firstName", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>	   	

		   	<div>
				<label for="lastName">[@spring.messageText "account.field.lastname", "LastName"/]</label>
				[@spring.formInput "account.lastName", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>		   	

		   	<div>
				<label for="linkedIn">[@spring.messageText "account.field.linkedIn", "LinkedIn"/]</label>
				[@spring.formInput "account.linkedIn", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>	   	

		   	<div>
				<label for="twitter">[@spring.messageText "account.field.twitter", "Twitter"/]</label>
				[@spring.formInput "account.twitter", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>  	

			<div> Visibilidad
				<select>
  					<option value="public" selected="selected">Perfil público</option>
  					<option value="registered">Solo usuarios registrados</option>
					<option value="private">Perfil privado</option>
				</select>
			</div>
			<div>	
				<button type="submit">[@spring.messageText "common.button.save", "Guardar"/]</button>
			</div>
	</body>
</html>
