<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Add a comment</title>
	</head>
	<body>
		<h1>Add a comment</h1>
		
<p>
		
	<webwork:form method="'post'" action="'/customercomment.action'">

	
	<webwork:textarea rows="'6'" cols="'50'" label="'Comments'" name="'comment'" value="comment" />

	<webwork:submit  value="'Save'" name="'submitComment'" />

	<webwork:submit  value="'Cancel'" name="'cancelComment'" />
	
	</webwork:form>

	</body>
</html>