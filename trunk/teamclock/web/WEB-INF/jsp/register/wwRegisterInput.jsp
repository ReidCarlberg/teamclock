<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>TeamClock.com Registration</title>

<script langugage="javascript">

function verifyForm() {

	var cb = dojo.byId("register_agreeToTerms");
	var ret = false;
	if (!cb || !cb.checked) {
		alert("You must agree to the TeamClock.com terms of service to continue.");
	} else {
		ret = true;
	}
	return ret;
}

</script>
	</head>
<body>

<div id="narrowcontent">


<h1>TeamClock.com New User Registration</h1>

<h3>Tell Us Five Things & You're In</h3>

<webwork:form action="'register.action'" method="'post'" name="'regform'" onsubmit="'if (!verifyForm()) { return false; }'">
<webwork:textfield label="'Your Name'" name="'systemOwner.contactName'" value="systemOwner.contactName" size="'40'" maxlength="'100'" />
<webwork:textfield label="'Organization Name'" name="'systemOwner.name'" value="systemOwner.name" size="'40'" maxlength="'100'" />
<webwork:textfield label="'Your Email'" name="'systemOwner.contactEmail'" value="systemOwner.contactEmail" size="'30'" maxlength="'100'"/>
<webwork:textfield label="'Email (Confirm)'" name="'email2'" value="email2" size="'30'" maxlength="'100'"/>


<webwork:select  label="'Your Time Zone'" 
			name="'timezone'" listKey="id" listValue="displayNameFull"
			list="timeZones" emptyOption="true" />
			
<webwork:textfield label="'Username'" name="'user.username'" value="user.username" size="'20'" maxlength="'20'"/>


<tr><td >&nbsp;</td><td>

<webwork:checkbox theme="'simple'"
	label="'I agree to the TeamClock.com terms of service'" name="'agreeToTerms'" 
	value="agreeToTerms" fieldValue="'true'"/> 

I agree to the TeamClock.com <a href='http://www.teamclock.com/terms.php' target='_blank'>terms of service</a> (opens in a new window).
</td></tr>
<tr><td colspan="2" align="center">&nbsp;</td></tr>
<tr><td colspan="2" align="center">
	<webwork:submit theme="'simple'" name="'submitRegister'" value="'Register & Continue to Email Verification'" />
</td></tr>

</webwork:form>



</div>

<div id="loginGreetings">

<h2 align="center">TeamClock.com Registration <br/> Quick &amp; Easy</h2>

<p>Why only five?  Because that's all we need.</p>
<p>When you register, we'll send an activation message to your email address.  You
must activate your registration prior to using the system.  Once you have activated your account, you'll
be able to select a password and configure your time clock.</p>

<p><strong>Do you need to have your <a href="<webwork:url value="'actResend.action'" />">activation message resent</a>?</strong></p>  

<p><strong>Existing users can <a href="<webwork:url value="'login.action'" />">login here</a>.</strong></p>

</div>

</body>

</html>