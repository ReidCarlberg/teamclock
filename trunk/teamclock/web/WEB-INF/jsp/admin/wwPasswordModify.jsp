<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head></head>
<body>

<h2>Modidfy Password</h2>

<p><strong><webwork:property value="passwordModifyContext.username" /></strong>

<webwork:form action="'passwordModify.action'" method="'post'">

<webwork:password label="'Password'" name="'userPassword1'" value="userPassword1" />

<webwork:password label="'Password (confirm)'" name="'userPassword2'" value="userPassword2" />

<webwork:submit name="'submitPassword'" value="'Save'" />

<webwork:submit name="'cancelPassword'" value="'Cancel'" />


</webwork:form>


</body>
</html>