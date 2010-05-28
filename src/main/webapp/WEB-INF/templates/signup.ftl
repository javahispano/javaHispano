[#ftl]
[#include "inc/includes.ftl"]
<html>
	<head>
		<title>JavaHispano account SignUp</title>
	</head>
	<body>
		<div>
			<label>[@spring.messageText "app.signup.disclaimer", 
			    	   "Actualmente no existe ningún usuario con la URI:"/] ${account.openid}
			</label> </ br>
		</div>
		<form method="post" action="">
			<div>	
				<label> Quieres registrarte? </label>
				<div>
					<label for="username">[@spring.messageText "account.field.username", "Username"/]</label>
					[@spring.formInput "account.username", "maxlength=\"200\"", "text" /]
	            	[#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
				</div>
				<div>
					<label for="email">[@spring.messageText "account.field.email", "Email"/]</label>
					[@spring.formInput "account.email", "maxlength=\"200\"", "text" /]
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
					[@spring.formInput "account.openid", "maxlength=\"200\"", "hidden" /]
				</div>
							
				<button type="submit">[@spring.messageText "app.signup", "Registrarme"/]</button>
			</div>
		</form>
	</body>
</html>
