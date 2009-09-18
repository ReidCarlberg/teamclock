<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Task Type Modify</title>
	</head>
	<body>
		<h2>Task Type Modify</h2>
		
		<webwork:form action="'taskmodify.action'" method="'post'">

		<webwork:textfield label="'Task Name'" name="'newTaskName'" value="newTaskName" />
		
		<webwork:submit value="'Save'" name="'submitTask'" />

		<webwork:submit value="'Cancel'" name="'cancelTask'" />
		
		</webwork:form>
		
	</body>
</html>