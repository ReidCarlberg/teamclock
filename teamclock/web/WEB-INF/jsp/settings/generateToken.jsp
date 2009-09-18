<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Generate Token</title></head>
<body>

<h2>Confirm Generate Token</h2>

<p>Notes:

<ul>
<li>Token access must be enabled in settings.</li>
<li>Tokens should be treated like passwords.  Keep in a safe place and do not give out.</li>
</ul>

<p>
<webwork:form theme="'simple'" action="action" method="'post'">

<webwork:submit theme="'simple'" name="'generateConfirm'" value="'Yes, generate.'" />

<webwork:submit theme="'simple'" name="'generateCancel'" value="'Cancel.'" />

</webwork:form>

</body>
</html>