<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Team Members</title>
	</head>
	<body>
		<h2>Team Members: <webwork:property value="modifyContext.targetTeam.name" /></h2>
		

		

		<webwork:form method="'post'" action="'teamModifyMembers.action'" >
			
				<webwork:checkboxlist name="'selectedMembers'" 
					value="selectedMembers"
					list="internalUsers"
					listKey="key"
					listValue="value" />
					
			<webwork:submit name="'saveTeamMembers'" value="'Save'" />
		
		</webwork:form>		

		

	</body>
</html>