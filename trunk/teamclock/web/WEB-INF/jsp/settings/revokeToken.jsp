<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Revoke Token</title></head>
<body>

<h2>Confirm Revoke Token</h2>

<p>Notes:

<ul>
<li>Revocation is permanent.  Revoked tokens cannot be reclaimed or reused.</li>
<li>Any services that depend on this token will cease to function once it is revoked.</li>
</ul>

<p>
<webwork:form theme="'simple'" action="action" method="'post'">

<webwork:submit theme="'simple'" name="'revokeConfirm'" value="'Yes, revoke.'" />

<webwork:submit theme="'simple'" name="'revokeCancel'" value="'Cancel.'" />

</webwork:form>

</body>
</html>