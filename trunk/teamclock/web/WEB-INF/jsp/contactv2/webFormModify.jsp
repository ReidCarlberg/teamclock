<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Web Form Modify</title>



	</head>
	<body>
		<h2>Web Form Modify</h2>
		

<webwork:form method="'post'" action="'webformmodify.action'">

	<webwork:textfield label="'Name'" name="'webForm.name'" value="webForm.name" size="'50'" maxlength="'100'" />

	<tr><td align="right">Redirect Response:</td><td>
	<webwork:checkbox theme="'simple'" label="Send Notification" 
		name="'webForm.redirectResponse'" value="webForm.redirectResponse" fieldValue="'true'" /></td></tr>


	<webwork:textfield label="'Success URL'" name="'webForm.successURL'" value="webForm.successURL" size="'50'" maxlength="'255'"/>

	<webwork:textfield label="'Failure URL'" name="'webForm.failureURL'" value="webForm.failureURL" size="'50'" maxlength="'255'"/>

	<webwork:select label="'Assigned To'" 
		name="'webForm.assignToUsername'" value="webForm.assignToUsername" 
		list="internalUsers" listKey="key" listValue="value" emptyOption="true"  />

	<webwork:select label="'Contact Class'" 
		name="'webForm.contactClass'" value="webForm.contactClass" 
		list="contactClassifications" listKey="id" listValue="fullName" emptyOption="true"  />
		
	<webwork:select label="'Contact Interest'" 
		name="'webForm.contactInterest'" value="webForm.contactInterest" 
		list="contactInterests" listKey="id" listValue="fullName" emptyOption="true"  />
		
	<webwork:select label="'Contact Source'" 
		name="'webForm.contactSource'" value="webForm.contactSource" 
		list="contactSources" listKey="id" listValue="fullName" emptyOption="true"  />
		
	<tr><td align="right">Send Notification:</td><td>
	<webwork:checkbox theme="'simple'" label="Send Notification" 
		name="'webForm.sendNotification'" value="webForm.sendNotification" fieldValue="'true'" /></td></tr>
		
	<webwork:select label="'Notification Message'" 
		name="'webForm.notificationMessageId'" value="webForm.notificationMessageId" 
		list="messages" listKey="id" listValue="name" emptyOption="true"  />		
								
	<webwork:textfield label="'Notification Recipient'" name="'webForm.notificationMessageRecipient'" 
		value="webForm.notificationMessageRecipient"  size="'50'" maxlength="'255'" />

	<tr><td align="right">Send Thank You:</td><td>
	<webwork:checkbox theme="'simple'" label="Send Thank You" 
		name="'webForm.sendThankYouMessage'" value="webForm.sendThankYouMessage" fieldValue="'true'" /></td></tr>
		
	<webwork:select label="'Thank You Message'" 
		name="'webForm.thankYouMessageId'" value="webForm.thankYouMessageId" 
		list="messages" listKey="id" listValue="name" emptyOption="true"  />		

	<tr><td align="right">Form Is Active:</td><td>
	<webwork:checkbox theme="'simple'" label="Form Is Active" 
		name="'webForm.active'" value="webForm.active" fieldValue="'true'" /></td></tr>

<webwork:submit name="'saveForm'" value="'Save'" />
<webwork:submit name="'cancelForm'" value="'Cancel'" />

		<webwork:if test="webFormModifyContext.target != null">
			<webwork:submit name="'deleteForm'" value="'Delete'" />
		</webwork:if>

</webwork:form>


<h2>Notes</h2>

<ul>
<li>The user who gets the assignment will be notified as long as the send notification box is checked.</li>
<li>The notifcation addresses field is for extra addresses.  Separate multiple addresses with a semi-colon.</li>
<li>If the nameFormatted field is used, TeamClock will try to parse to it's components.  If it's blank but nameFirst and nameLast exist, TeamClock will try to build the formatted name.</li>
<li>"Detail" field will go into the resulting to do detail.</li>
</ul>
		
		
	</body>
</html>