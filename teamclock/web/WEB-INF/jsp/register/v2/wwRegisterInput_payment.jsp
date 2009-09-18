<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>TeamClock New User Registration</title>
	</head>
<body>

<h2>TeamClock New User Registration (Step 3)</h2>

<p>Payment information.  Please enter your Visa, MasterCard, Amex or Discover card number.

<webwork:form action="'regRegPayment.action'" method="'post'" name="'regformPayment'">

<webwork:textfield label="'Credit Card Number'" name="'registrationContext.paymentInformation.number'" 
	value="registrationContext.paymentInformation.number" />

<webwork:textfield label="'CVV'" name="'registrationContext.paymentInformation.cvv'" 
	value="registrationContext.paymentInformation.cvv" />

<webwork:textfield label="'Expires In Month'" name="'registrationContext.paymentInformation.expiresMonth'" 
	value="registrationContext.paymentInformation.expiresMonth" />

<webwork:textfield label="'Expires In Year'" name="'registrationContext.paymentInformation.expiresYear'" 
	value="registrationContext.paymentInformation.expiresYear" />


<webwork:textfield label="'First Name'" name="'registrationContext.paymentInformation.nameFirst'" 
	value="registrationContext.paymentInformation.nameFirst" />

<webwork:textfield label="'Last Name'" name="'registrationContext.paymentInformation.nameLast'" 
	value="registrationContext.paymentInformation.nameLast" />

<webwork:textfield label="'Street'" name="'registrationContext.paymentInformation.street'" 
	value="registrationContext.paymentInformation.street" />

<webwork:textfield label="'City'" name="'registrationContext.paymentInformation.city'" 
	value="registrationContext.paymentInformation.city" />

<webwork:textfield label="'State/Province'" name="'registrationContext.paymentInformation.state'" 
	value="registrationContext.paymentInformation.state" />

<webwork:textfield label="'Zip/Postal Code'" name="'registrationContext.paymentInformation.zip'" 
	value="registrationContext.paymentInformation.zip" />

<webwork:textfield label="'Country'" name="'registrationContext.paymentInformation.country'" 
	value="registrationContext.paymentInformation.country" />



</td>
	</tr>
	<webwork:submit name="'submitRegister'" value="'Register'" />
</webwork:form>

<!--validator information-->
<script language="Javascript">
<!--
var frmvalidator  = new Validator("regform");
frmvalidator.addValidation("systemOwner.contactName","req","Please enter your name");
frmvalidator.addValidation("systemOwner.contactEmail","req","Please enter your email address");
frmvalidator.addValidation("systemOwner.contactEmail","email");
frmvalidator.addValidation("email2","req","Please enter your email address again to confirm.");
frmvalidator.addValidation("email2","email");
frmvalidator.addValidation("systemOwner.contactPhone","req","Please enter your contact phone.");
frmvalidator.addValidation("systemOwner.name","req","Please enter your name.");
frmvalidator.addValidation("systemOwner.address1","req","Please enter your address1.");
frmvalidator.addValidation("systemOwner.city","req","Please enter your city.");
frmvalidator.addValidation("systemOwner.state","req","Please enter your state.");
frmvalidator.addValidation("systemOwner.postalCode","req","Please enter your zip/postal code.");
//-->
</script>




</body>

</html>