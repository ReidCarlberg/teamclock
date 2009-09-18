<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Dashboard</title>
	</head>
	<body>
		<h1>Dashboard</h1>
		
		<p>Customer: <webwork:property value="sessionContext.externalUserSessionContext.activeCustomer.name" />
		
		<p><a href="<webwork:url value="'/extTodos.action'" />">Incomplete To Dos</a>
				
		<p><a href="<webwork:url value="'/extAccount.action'" />">Account Activity</a>
		
		<webwork:if test="sessionContext.externalUserSessionContext.relatedCustomers.size > 1">
			<p><a href="<webwork:url value="'/extCustomer.action'" />">Switch Customer</a>
		</webwork:if>
		
		<p><a href="<webwork:url value="'/logout.action'" />">Logout</a>

	</body>
</html>