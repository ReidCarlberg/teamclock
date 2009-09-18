<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>TeamClock New User Registration</title>
	</head>
<body>


<h2>TeamClock New User Registration - Step 4 - Confirm Your Registration</h2>

<p>All fields are required.  When you register, we'll send an activation message and password to your email address.  You
must activate your registration prior to using the system.  


<p>Plan
<webwork:property value="registrationContext.plan.name" />

<p>Company Information
<webwork:property value="registrationContext.systemOwner.name" />

<p>Payment Information
<webwork:property value="registrationContext.paymentInformation.type" />

<p>Agree to terms and confirm.

<webwork:form>

<tr>
<td align="right"><webwork:checkbox theme="'simple'"
	label="''" name="'agreeToTerms'" 
	value="agreeToTerms" fieldValue="'true'"/>
</td>
<td>
I agree to the  <a href='http://www2.fstxtime.com/terms.php' target='_blank'>terms of service</a>.

</td>
	</tr>

	<webwork:submit name="'submitRegistration'" value="Register" />
</webwork:form>


</body>

</html>