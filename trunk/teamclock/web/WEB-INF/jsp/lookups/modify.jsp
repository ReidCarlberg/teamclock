<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Lookup Modify</title>
	</head>
	<body>
		<h2>Lookup Modify</h2>
		
		<webwork:form method="'post'" action="'/lookupModify.action'" >
		
		<webwork:textfield name="'targetLookup.shortName'" 
			label="'Short Name'" value="targetLookup.shortName" maxlength="'20'" />

		<webwork:textfield name="'targetLookup.fullName'" 
			label="'Full Name'" value="targetLookup.fullName" maxlength="'100'" />


		<webwork:submit name="'cancelLookup'" value="'Cancel'" />

		<webwork:submit name="'saveLookup'" value="'Save'" />		

		<webwork:if test="modifyContext.target != null">
			<webwork:submit name="'copyLookup'" value="'Copy'" />		
			<webwork:submit name="'deleteLookup'" value="'Delete'" />		
		</webwork:if>
		
		</webwork:form>		

	</body>
</html>