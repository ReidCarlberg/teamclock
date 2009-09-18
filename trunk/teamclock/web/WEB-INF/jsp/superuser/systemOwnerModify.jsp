<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>System Owner Modify</title>
	</head>
<body>

<h2>System Owner Modify</h2>


<webwork:form action="'systemOwnerModify.action'" method="'post'" name="'regform'">
<webwork:textfield label="'Contact Name'" name="'targetOwner.contactName'" value="targetOwner.contactName" size="'100'" maxlength="'100'" />
<webwork:textfield label="'Contact Email'" name="'targetOwner.contactEmail'" value="targetOwner.contactEmail" size="'100'" maxlength="'100'" />
<webwork:textfield label="'Phone'" name="'targetOwner.contactPhone'" value="targetOwner.contactPhone" size="'100'" maxlength="'100'" />
<webwork:textfield label="'Organization Name'" name="'targetOwner.name'" value="targetOwner.name" size="'100'" maxlength="'100'" />
<webwork:textfield label="'Address'" name="'targetOwner.address1'" value="targetOwner.address1" size="'100'" maxlength="'100'" />
<webwork:textfield label="'City'" name="'targetOwner.city'" value="targetOwner.city" size="'100'" maxlength="'100'" />
<webwork:textfield label="'State'" name="'targetOwner.state'" value="targetOwner.state" size="'100'" maxlength="'100'" />
<webwork:textfield label="'Zip/Postal'" name="'targetOwner.postalCode'" value="targetOwner.postalCode" size="'100'" maxlength="'100'" />
<webwork:select   label="'County'"
			name="'targetOwner.country'" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />

<webwork:textfield label="'Expiration Date'" name="'targetOwner.expirationDate'" value="targetOwner.expirationDate" />			

<webwork:textfield label="'Account Type'" name="'targetOwner.account'" value="targetOwner.account" />

<webwork:textfield label="'Billing Frequency'" name="'targetOwner.billingFrequency'" value="targetOwner.billingFrequency" />

<webwork:checkbox label="'Requires Account Update?'" name="'requiresAccountUpdate'" value="requiresAccountUpdate" fieldValue="'true'" />		

<webwork:textfield label="'Account Update Reason'" name="'targetOwner.requiresAccountUpdateReason'" value="targetOwner.requiresAccountUpdateReason" />

<webwork:submit name="'cancelOwner'" value="'Cancel'" />
<webwork:submit name="'saveOwner'" value="'Save'" />
</webwork:form>

<p>Account update reasons:</p>

<ul>
<li>Demo Over - Demo period is over.</li>
<li>Others</li>
</ul>

</body>

</html>