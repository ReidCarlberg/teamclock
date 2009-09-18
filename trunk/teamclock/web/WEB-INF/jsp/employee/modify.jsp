<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Employee </title>
	</head>
	<body>
		<h2>Employee <webwork:if test="modifyContext.target != null">Modify</webwork:if><webwork:else>Add</webwork:else></h2>
		
		<webwork:form method="'post'" action="'/employeeModify.action'" >

		<webwork:select label="'Username'" name="'targetEmployee.username'" value="targetEmployee.username"
			list="internalUsers" listKey="key" listValue="value" emptyOption="true"  />
		
		<webwork:textfield name="'targetEmployee.code'" 
			label="'Code'" value="targetEmployee.code" maxlength="'50'" size="'50'" />

		<webwork:textfield name="'targetEmployee.nameLast'" 
			label="'Last Name'" value="targetEmployee.nameLast" maxlength="'50'" size="'50'" />

		<webwork:textfield name="'targetEmployee.nameFirst'" 
			label="'First Name'" value="targetEmployee.nameFirst" maxlength="'50'" size="'50'" />

		<webwork:textfield name="'targetEmployee.hourlyRate'" 
			label="'Hourly Rate'" value="targetEmployee.hourlyRate" maxlength="'20'" size="'20'" />


		<webwork:submit name="'cancelEmployee'" value="'Cancel'" />

		<webwork:submit name="'saveEmployee'" value="'Save'" />		

		<webwork:if test="modifyContext.target != null">
			<webwork:submit name="'deleteEmployee'" value="'Delete'" />		
		</webwork:if>
		
		</webwork:form>		

	</body>
</html>