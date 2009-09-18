<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>E-Mail All System Owners</title>
	</head>
	<body>
		
	<webwork:form action="'superUserEmailToAllSystemOwners.action'" method="'post'" name="'emailSystemOwners'">
		<webwork:textfield label="'Subject'" name="'subject'" value="subject" />
		<webwork:textarea label="'Message'" name="'message'" value="message" cols="30" rows="8" />

		<webwork:submit name="'cancel'" value="'Cancel'" />
		<webwork:submit name="'submit'" value="'Send'" />
	</webwork:form>
		
	</body>
</html>