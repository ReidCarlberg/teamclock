<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>TeamClock New User Registration</title>
	</head>
<body>

<h2>TeamClock New User Registration (Step 2)</h2>

<p>All fields are required.  When you register, we'll send an activation message and password to your email address.  You
must activate your registration prior to using the system.  

<p>Do you need to have your <a href="<webwork:url value="'actResend.action'" />">activation message resent</a>?  Existing users can <a href="<webwork:url action="'login.action'" />">login here</a>.

<webwork:form action="'regReg.action'" method="'post'" name="'regform'">
<webwork:textfield label="'Your Name'" name="'systemOwner.contactName'" value="systemOwner.contactName" />
<webwork:textfield label="'Your Email'" name="'systemOwner.contactEmail'" value="systemOwner.contactEmail" />
<webwork:textfield label="'Email (Confirm)'" name="'email2'" value="email2" />
<webwork:textfield label="'Phone'" name="'systemOwner.contactPhone'" value="systemOwner.contactPhone" />
<webwork:textfield label="'Organization Name'" name="'systemOwner.name'" value="systemOwner.name" />
<webwork:textfield label="'Address'" name="'systemOwner.address1'" value="systemOwner.address1" />
<webwork:textfield label="'City'" name="'systemOwner.city'" value="systemOwner.city" />
<webwork:textfield label="'State/Provinice'" name="'systemOwner.state'" value="systemOwner.state" />
<webwork:textfield label="'Zip/Postal'" name="'systemOwner.postalCode'" value="systemOwner.postalCode" />


<webwork:select  label="'Your Time Zone:'" 
			name="'timezone'" listKey="id" listValue="displayNameFull"
			list="timeZones" emptyOption="true" />
			
		
<webwork:select   label="'County:'"
			name="'systemOwner.country'" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />
		

	<webwork:textfield label="'Username'" name="'user.username'" value="user.username" />
	
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