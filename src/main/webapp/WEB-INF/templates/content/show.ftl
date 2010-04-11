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
	</body>
</html>
