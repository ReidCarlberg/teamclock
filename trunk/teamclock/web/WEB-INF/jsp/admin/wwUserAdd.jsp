<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head></head>
<body>

<h2>Add a User</h2>

<webwork:form action="'userAdd.action'" method="'post'">

<webwork:textfield label="'Username'" name="'username'" value="username" />

<webwork:textfield label="'E-mail'" name="'userEmailString'" value="userEmailString" />

<webwork:select label="'User Type'" 
	name="'userTypeString'" 
	value="userTypeString" 
	list="userTypes" 
	listKey="privateName" 
	listValue="publicName" />

<webwork:password label="'Password'" name="'userPassword1'" value="userPassword1" />

<webwork:password label="'Password (confirm)'" name="'userPassword2'" value="userPassword2" />


<webwork:submit name="'submitUser'" value="'Save'" />

<webwork:submit name="'cancelUser'" value="'Cancel'" />


</webwork:form>


</body>
</html>