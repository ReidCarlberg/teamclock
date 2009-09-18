<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Project Modify</title>
	</head>
	<body>
		<h2>Project Modify</h2>
		
		<webwork:form action="'projectmodify.action'" method="'post'">

		<webwork:select label="'Customer'" name="'newCustomer'" value="newCustomer"
			list="customers" listKey="id" listValue="name" emptyOption="false"  />	

		<webwork:textfield label="'Long Name'" name="'newLongName'" value="newLongName" />

		<webwork:textfield label="'Short Name'" name="'newShortName'" value="newShortName" />
		 
		<webwork:select label="'Is Active'" 
			name="'stringActive'" value="stringActive"
			list="{'true','false'}"  emptyOption="false" />	

		<webwork:if test="sessionContext.rights.canUseAccountTransactions">
			<webwork:select label="'Account Postable?'" 
				name="'stringPostable'" value="stringPostable"
				list="{'true','false'}" emptyOption="false" />	
		</webwork:if>			
		
		<webwork:select label="'Class'" name="'projectClassId'" value="projectClassId"
			list="projectClassTypes" listKey="id" listValue="fullName" 
			emptyOption="true"   />
					
		<webwork:submit value="'Save'" name="'submitProject'" />
		
		<webwork:if test="projectModifyContext.targetProject != null">
				<webwork:submit value="'Delete'" name="'submitProjectDelete'" />
		</webwork:if>
		
		<webwork:submit value="'Cancel'" name="'cancelProject'" />

		</webwork:form>
		
	</body>
</html>

