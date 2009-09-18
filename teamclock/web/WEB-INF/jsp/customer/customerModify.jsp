<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Customer Modify</title>
	</head>
	<body>
		<h2>Customer Modify</h2>
		
		<webwork:form action="'customermodify.action'" method="'post'">

		
		<webwork:textfield label="'Name'" name="'targetCust.name'" value="targetCust.name" />
		<webwork:textfield label="'Street1'" name="'targetCust.street1'" value="targetCust.street1" />
		<webwork:textfield label="'Street2'" name="'targetCust.street2'" value="targetCust.street2" />
		<webwork:textfield label="'City'" name="'targetCust.city'" value="targetCust.city" />
	    <webwork:textfield label="'State/Province'" name="'targetCust.state'" value="targetCust.state" />
	    <webwork:textfield label="'Zip/Postal'" name="'targetCust.zip'" value="targetCust.zip" />
		<webwork:select   label="'Country'"
			name="'targetCust.country'" value="targetCust.country" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />
		
		
		
		<webwork:select label="'Status'" name="'targetCust.status'" value="targetCust.status"
			list="statusLookups" listKey="id" listValue="fullName" 
			emptyOption="true"   />
		
		<webwork:checkbox label="'Hidden'" name="'targetCust.hidden'" value="targetCust.hidden" fieldValue="'true'" />
		
		<webwork:textfield label="'Budget'" name="'targetCust.budget'" value="targetCust.budget" />
		
		<webwork:textfield label="'Budget Progress'" name="'targetCust.budgetProgress'" value="targetCust.budgetProgress" />
		
		<webwork:textfield label="'Pay Method'" name="'targetCust.payMethod'" value="targetCust.payMethod" />		
		
		<webwork:textfield label="'Number'" name="'targetCust.payNumber'" value="targetCust.payNumber" />		
		
		<webwork:textfield label="'Exp Month'" name="'targetCust.payMonth'" value="targetCust.payMonth" />		
		
		<webwork:textfield label="'Exp Year'" name="'targetCust.payYear'" value="targetCust.payYear" />			
		
		<webwork:textfield label="'CVV2'" name="'targetCust.payCode'" value="targetCust.payCode" />		
		
		<webwork:textfield label="'Name'" name="'targetCust.payName'" value="targetCust.payName" />		
		
		<webwork:textfield label="'Street'" name="'targetCust.payStreet'" value="targetCust.payStreet" />		
		
		<webwork:textfield label="'Zip'" name="'targetCust.payZip'" value="targetCust.payZip" />		
		
		<webwork:select label="'Notify'" name="'targetCust.payNotifyContactId'" value="targetCust.payNotifyContactId"
			list="customerContacts" listKey="id" listValue="nameFormatted" 
			emptyOption="true"   />
		
		<webwork:submit value="'Save'" name="'customerSubmit'" />
		
		<webwork:if test="customerModifyContext.targetCustomer != null">
			<webwork:submit value="'Delete'" name="'customerSubmitDelete'" />		
		</webwork:if>
		
	    <webwork:submit value="'Cancel'" name="'customerCancel'" />
		
		</webwork:form>
		
		
	</body>
</html>
