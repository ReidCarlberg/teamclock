<%@ taglib uri="webwork" prefix="webwork" %>



<html><head><title>Customer User Add</title></head><body>


<p>Customer: <webwork:property value="customerModifyContext.targetCustomer.name" />

<p>&nbsp;

<webwork:form method="'post'" action="'/customerUserAdd.action'"	>

<webwork:textfield label="'Username'" name="'username'" value="username" maxlength="20" />

<webwork:textfield label="'Email Address'" name="'email'" value="email" maxlength="100" />

<webwork:submit name="'submitUser'" value="'Add User'" />

<webwork:submit name="'cancelUser'" value="'Cancel'" />

</webwork:form>



</body>
</html>

