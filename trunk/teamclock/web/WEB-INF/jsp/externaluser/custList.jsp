<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Select Active Customer</title>
	</head>
	<body>
		<h1>Select Active Customer</h1>
		
		<p>Select the customer you want to work with.
		
		<table>
		<webwork:iterator value="sessionContext.externalUserSessionContext.relatedCustomers" >
			<tr>
				<td>
					<a href="<webwork:url value="'/extCustomer.action'">
						<webwork:param name="'target'" value="id" />
						</webwork:url>"><webwork:property value="name" /></a>
				</td>
			</tr>
		</webwork:iterator>
		
		</table>

	</body>
</html>