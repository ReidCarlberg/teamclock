<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head></head>
<body>

<h2>User Modify</h2>

<p><strong><webwork:property value="userAndType.user.username" /></strong>

<p><a href="<webwork:url value="'passwordModify.action'"><webwork:param name="'targetUser'" value="userModifyContext.target.user.username" /></webwork:url>">Change Password</a>

<p>&nbsp;

<webwork:form action="'userModify.action'" method="'post'">

<webwork:textfield label="'E-mail'" name="'userEmailString'" value="userEmailString" />

<webwork:select label="'User Type'" 
	name="'userTypeString'" 
	value="userTypeString" 
	list="userTypes" 
	listKey="privateName" 
	listValue="publicName" />


<webwork:submit name="'submitUser'" value="'Save'" />

<webwork:submit name="'cancelUser'" value="'Cancel'" />

<webwork:submit name="'deleteUser'" value="'Delete'" />

</webwork:form>


</body>
</html>