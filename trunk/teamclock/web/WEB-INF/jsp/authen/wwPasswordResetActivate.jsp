<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>Password Reset</title></head><body>

<h2>Password Reset - Activate Link</h2>

<p>Please enter the key and new password information.

<p>&nbsp;

<webwork:form name="'activate'" action="'passwordResetActivate.action'" method="'post'">

<webwork:textfield label="'Reset Key'" name="'key'" value="key" size="'50'" maxlength="'100'" />

<webwork:password label="'Password'" name="'password1'" value="password1" size="'20'" maxlength="'20'" />

<webwork:password label="'Password (confirm)'" name="'password2'" value="password2" size="'20'" maxlength="'20'" />

<webwork:submit name="'submitUpdate'" value="'Update My Password'" />

</webwork:form>

<!--validator information-->
<script language="Javascript">
<!--
var frmvalidator  = new Validator("activate");
frmvalidator.addValidation("key","req","Please enter your reset key.");
frmvalidator.addValidation("password1","req","Please enter a password.");
frmvalidator.addValidation("password2","req","Please enter the password again.");
//-->
</script>

</body>
</html>


