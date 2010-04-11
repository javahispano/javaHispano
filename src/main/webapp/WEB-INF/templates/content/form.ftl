[#ftl]
[#include "../inc/includes.ftl"]
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h2></h2>
		<form method="post" action="">
			<div>
				<label for="title">[@spring.messageText "content.field.title", "Título"/]</label>
				[@spring.formInput "content.title", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>
			<div>
				<label for="title">[@spring.messageText "content.field.body", "Contenido"/]</label>
				[@spring.formTextarea "content.body" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>
			<div>
				<label for="title">[@spring.messageText "content.field.tags", "Tags"/]</label>
				[@spring.formInput "content.tags", "maxlength=\"200\"", "text" /]
	            [#list spring.status.errorMessages as error]<br/><span class="inputError">${error}</span>[/#list]	
			</div>
			<div>	
				<button type="submit">[@spring.messageText "common.button.save", "Guardar"/]</button>
			</div>
		</form>
	</body>
</html>
