<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Team Modify</title>
	</head>
	<body>
		<h2>Team Modify</h2>
		
		<webwork:form method="'post'" action="'/teamModify.action'" >
		
		<webwork:textfield name="'targetTeam.nameShort'" 
			label="'Short Name'" value="targetTeam.nameShort" maxlength="'20'" size="'20'" />

		<webwork:textfield name="'targetTeam.name'" 
			label="'Name'" value="targetTeam.name" maxlength="'100'" size="'50'" />



		<webwork:submit name="'cancelTeam'" value="'Cancel'" />

		<webwork:submit name="'saveTeam'" value="'Save'" />		

		<webwork:if test="modifyContext.target != null">
			<webwork:submit name="'deleteTeam'" value="'Delete'" />		
		</webwork:if>
		
		</webwork:form>		

	</body>
</html>