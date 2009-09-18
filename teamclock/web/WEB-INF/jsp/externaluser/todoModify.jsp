<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Create a To Do</title>
	</head>
	<body>
		<h1>Create a To Do</h1>
		
<p>
		
	<webwork:form method="'post'" action="'/extTodoModify.action'">

	<webwork:select label="'Project'" name="'projectId'" value="projectId"
		list="projects" listKey="id" listValue="shortName" emptyOption="true"  />
	
	<webwork:textarea rows="'8'" cols="'50'" label="'Details'" name="'details'" value="details" />

	<webwork:submit  value="'Save'" name="'submitToDo'" />

	<webwork:submit  value="'Cancel'" name="'cancelToDo'" />
	
	</webwork:form>

	</body>
</html>