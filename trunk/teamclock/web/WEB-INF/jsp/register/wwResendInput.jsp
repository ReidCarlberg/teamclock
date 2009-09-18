<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>Resend Activation Message</title></head><body>

<div align="center">
<div style="width: 700px; text-align; left;">
<h1>Resend Activation Message</h1>

<p>Return to the <a href="<webwork:url value="'regReg.action'"/>">registration screen</a>.

<webwork:if test="actionErrors.size > 0">
	<ul>
	<webwork:iterator value="actionErrors">
		<li><webwork:property />
	</webwork:iterator>
	</ul>
</webwork:if>

<webwork:form method="'post'" action="'actResend.action'">

<webwork:textfield label="'Contact Email'" name="'email'" value="email" size="'30'" maxlength="'50'"/>

<webwork:submit name="'submit'" value="'Send'" />

</webwork:form>
</div>
</div>

</body>

</html>