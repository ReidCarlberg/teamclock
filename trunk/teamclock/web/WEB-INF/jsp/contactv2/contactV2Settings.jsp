<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Contact Settings (<webwork:property value="lookupType.friendlyName" />)</title>
	</head>
	<body>
		<h2><webwork:property value="contactV2ModifyContext.target.nameFormatted" /> Settings</h2>
		
		<h3><webwork:property value="lookupType.friendlyName" /></h3>
		

		<webwork:form action="'contactsettingsv2.action'" method="'post'" >
			<p>
				<webwork:checkboxlist name="'selectedMembers'" 
					value="selectedMembers"
					list="settings"
					listKey="idAsString"
					listValue="fullName" />
					
			<p><webwork:submit name="'saveSettings'" value="'Save'" /></p>
		
		</webwork:form>		

		

	</body>
</html>