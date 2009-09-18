<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Employee</title>
	</head>
	<body>
		<h2>Employee </h2>
		
		<p><a href="<webwork:url value="'/employeeModify.action'" ><webwork:param name="'target'" value="modifyContext.target.id" /></webwork:url>">Modify</a>
| Back to the <a href="<webwork:url value="'/employeeList.action'" />">employee list</a>.</p>
</p>
		
<div id="generalTable">
		<table >
			<tr>
				<td>Username</td>
				<td><webwork:property value="modifyContext.target.username" /></td>
			</tr>
			<tr>
				<td>Code</td>
				<td><webwork:property value="modifyContext.target.code" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><webwork:property value="modifyContext.target.nameLast" /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><webwork:property value="modifyContext.target.nameFirst" /></td>
			</tr>
			<tr>
				<td>Hourly Rate</td>
				<td><webwork:property value="modifyContext.target.hourlyRate" /></td>
			</tr>

		</table>
</div>
		
		<h2>Account Transactions</h2>
		
		<p><a href="<webwork:url value="'employeeViewTransactions.action'"><webwork:param name="'target'" value="'PTO'"/></webwork:url>">Paid Time Off</a>

		<h3>Legacy</h3>
						
		<p><a href="<webwork:url value="'employeeViewTransactions.action'"><webwork:param name="'target'" value="'SICK'"/></webwork:url>">Sick Time</a>
		
		<p><a href="<webwork:url value="'employeeViewTransactions.action'"><webwork:param name="'target'" value="'PERSONAL'"/></webwork:url>">Personal Time</a>
		
		<p><a href="<webwork:url value="'employeeViewTransactions.action'"><webwork:param name="'target'" value="'VACATION'"/></webwork:url>">Vacation Time</a>


	</body>
</html>