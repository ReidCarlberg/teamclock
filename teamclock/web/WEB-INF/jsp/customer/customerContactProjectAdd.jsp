<%@ taglib uri="webwork" prefix="webwork"%>



<html>
<head>
<title>Add a customer, contact and project</title>
</head>
<body>


<h2>Add a customer, contact and project</h2>


<webwork:form method="'post'"
	action="'customerContactProjectAdd.action'">

	<webwork:textfield label="'First Name'" name="'contactV2.nameFirst'"
		value="contact.nameFirst" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'Last Name'" name="'contactV2.nameLast'"
		value="contact.nameLast" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'Company'" name="'customer.name'"
		value="customer.name" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'Street (1)'" name="'customer.street1'"
		value="customer.street1" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'Street (2)'" name="'customer.street2'"
		value="customer.street2" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'City'" name="'customer.city'"
		value="customer.city" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'State'" name="'customer.state'"
		value="customer.state" size="'10'" maxlength="'10'" />

	<webwork:textfield label="'Zip'" name="'customer.zip'"
		value="customer.zip" size="'10'" maxlength="'10'" />

	<webwork:select label="'County:'" name="'customer.country'"
		listKey="shortName" listValue="longName" list="countries"
		emptyOption="true" />

	<webwork:textfield label="'Phone (Work)'" name="'contactV2.organizationPhone'"
		value="contactV2.organizationPhone" size="'20'" maxlength="'20'" />

	<webwork:textfield label="'Phone (Cell)'" name="'contactV2.miscPhoneMobile'"
		value="contactV2.miscPhoneMobile" size="'20'" maxlength="'20'" />

	<webwork:textfield label="'Email'" name="'contactV2.organizationEmail'"
		value="contactV2.homeEmail" size="'50'" maxlength="'100'" />

	<webwork:textfield label="'Project Name'" name="'project.longName'"
		value="project.longName" size="'50'" maxlength="'50'" />

	<webwork:textfield label="'Project Short Name'"
		name="'project.shortName'" value="project.shortName" size="'25'"
		maxlength="'50'" />

		<webwork:select label="'Project Class'" name="'project.projectClassId'" value="project.projectClassId"
			list="projectClassTypes" listKey="id" listValue="fullName" 
			emptyOption="true"   />

	<webwork:textfield label="'Opening Balance'" name="'time.amount'"
		value="time.amount" size="'10'" maxlength="'10'" />

	<webwork:textfield label="'Opening Balance Comment'"
		name="'time.comments'" value="time.comments" size="'50'"
		maxlength="'100'" />

	<webwork:select label="'Customer Status'" name="'customer.status'"
		value="customer.status" list="statusLookups" listKey="id"
		listValue="fullName" emptyOption="true" />

	<webwork:submit name="'saveCustomer'" value="'Save'" />

	<webwork:submit name="'cancelCustomer'" value="'Cancel'" />


</webwork:form>

</body>
</html>
