<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Contact Modify</title>
	</head>
	<body>
		<h2>Contact Modify</h2>
		
		<webwork:form action="'contactmodify.action'" method="'post'">

		<webwork:textfield label="'First Name'" name="'contact.nameFirst'" value="contact.nameFirst" size="40" />
		
		<webwork:textfield label="'Last Name'" name="'contact.nameLast'" value="contact.nameLast" size="40" />
		
			<webwork:textfield label="'Primary Phone'" name="'contact.primaryPhone'" value="contact.primaryPhone" />
		
			<webwork:textfield label="'Alternate Phone'" name="'contact.alternatePhone'" value="contact.alternatePhone" />
		
			<webwork:textfield label="'Primary Email'" name="'contact.primaryEmail'" value="contact.primaryEmail" size="40" />
		
			<webwork:textfield label="'Alternate Email 1'" name="'contact.alternateEmail1'" value="contact.alternateEmail1" size="40" />
		
			<webwork:textfield label="'Alternate Email 2'" name="'contact.alternateEmail2'" value="contact.alternateEmail2" size="40" />
			
			<webwork:textfield label="'Alternate Email 3'" name="'contact.alternateEmail3'" value="contact.alternateEmail3" size="40" />
		
			<webwork:textfield label="'Alternate Email 4'" name="'contact.alternateEmail4'" value="contact.alternateEmail4" size="40" />
		

    
		<webwork:submit value="'Save'" name="'contactSubmit'" />
	    <webwork:submit value="'Cancel'" name="'contactCancel'" />

		<webwork:if test="contactModifyContext.target != null">
			<webwork:submit value="'Delete'" name="'contactDelete'" />
		</webwork:if>
		
				
		</webwork:form>
		
		
		
	</body>
</html>