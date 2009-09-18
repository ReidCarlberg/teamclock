<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Settings Modify</title>
	</head>
	<body>

		<h1>Settings Modify</h1>
		
		<webwork:form method="'post'" action="'systemOwnerModifyAccountSetting.action'">
		
		<table>

		<tr>
			<td>Max Account Users</td>
			<td><webwork:select theme="'simple'"  name="'modifyParameters.numberOfActiveUsers'" 
				value="modifyParameters.numberOfActiveUsers" list="maxUserOptions" emptyOption="'true'"/>
			</td>
		</tr>
		<tr>
			<td>Free System?</td>
			<td><webwork:checkbox theme="'simple'" label="'free system'" name="'modifyParameters.freeSystem'" 
				value="modifyParameters.freeSystem" fieldValue="'true'"/>
			</td>
		</tr>  
		<tr>
			<td>Account Transactions?</td>
			<td><webwork:checkbox theme="'simple'" label="'account transactions'" name="'modifyParameters.canUseAccountTransactions'" 
				value="modifyParameters.canUseAccountTransactions" fieldValue="'true'"/>
			</td>
		</tr>  
		<tr>
			<td>External Users?</td>
			<td><webwork:checkbox theme="'simple'" label="'external users'" name="'modifyParameters.canHaveExternalUsers'" 
				value="modifyParameters.canHaveExternalUsers" fieldValue="'true'"/>
			</td>
		</tr>
		<tr>
			<td>Beta Features?</td>
			<td><webwork:checkbox theme="'simple'" label="'beta features'" name="'modifyParameters.canUseBetaFeatures'" 
				value="modifyParameters.canUseBetaFeatures" fieldValue="'true'"/>
			</td>
		</tr>  
		
		<tr>
			<td colspan="2" >
				<webwork:submit theme="'simple'" name="'submit'" value="'Save Changes'" /><br>
				<webwork:submit theme="'simple'" name="'cancel'" value="'Cancel'" />
				
				<p><webwork:submit theme="'simple'" name="'delete'" value="'Delete System Owner'" />					
			</td>
		</tr>


				
		</table>
		
		
		
		
		</webwork:form>
		
			

		
	</body>
</html>