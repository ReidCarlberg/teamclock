<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Add a comment</title>
	</head>
	<body>
	
	<h2><webwork:property value="contactV2ModifyContext.target.nameFormatted" /></h2>
	
		<h3>Add a comment</h3>
		

		
	<webwork:form method="'post'" action="'/contactv2comment.action'">

	
	<webwork:textarea rows="'6'" cols="'50'" label="'Comments'" name="'comment'" value="comment" />

	<webwork:submit  value="'Save'" name="'submitComment'" />

	<webwork:submit  value="'Cancel'" name="'cancelComment'" />
	
	</webwork:form>

	</body>
</html>