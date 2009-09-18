<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>TeamClock New User Registration</title>
	</head>
<body>


<h2>TeamClock New User Registration (Step 1)</h2>

<p>Start by selecting your plan.

<webwork:form action="'regRegPlan.action'" method="'post'" name="'regformPlan'">
<table>
<tr>
<td>
	<p><input type="radio" name="selectedPlan" value="BASIC" >Basic
	<br/>
	Free, no CC required for registration.
</td>
<td>
	<p><input type="radio" name="selectedPlan" value="SIMPLE" >SIMPLE</td>
	30 Day Free Trial
</tr>
<td>
	<input type="radio" name="selectedPlan" value="STARTER" >STARTER</td>
</tr>
<td>
	<input type="radio" name="selectedPlan" value="BUSINESS1" >BIZ1</td>
</tr>
<td>
	<input type="radio" name="selectedPlan" value="BUSINESS2" >BIZ2</td>
</tr>
<td>
	<input type="radio" name="selectedPlan" value="BUSINESS3" >BIZ3</td>
</tr>

</table>

	<webwork:submit name="'submitRegister'" value="'Next &gt; &gt;'" />
</webwork:form>

<!--validator information-->
<script language="Javascript">
<!--
var frmvalidator  = new Validator("regformPlan");
//frmvalidator.addValidation("systemOwner.contactName","req","Please enter your name");
//-->
</script>




<p>Do you need to have your <a href="<webwork:url value="'actResend.action'" />">activation message resent</a>?  Existing users can <a href="<webwork:url value="'login.action'" />">login here</a>.

</body>

</html>