<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Employees</title>
	</head>
	<body>
		<h2>Employees</h2>
		

		<p><a href="<webwork:url value="'/employeeModify.action'" />">Add</a>

<div id="listTable" >		
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<th>Code</th>
				<th>Last Name</th>
				<th>First Name</th>
				<th>Username</th>
				<th>&nbsp;</th>
				
			</tr>
			<webwork:iterator value="employees">
				<tr>
					<td><webwork:property value="code" /></td>
					<td><webwork:property value="nameLast" /></td>
					<td><webwork:property value="nameFirst" /></td>
					<td><webwork:property value="username" /></td>
					<td>
						<a href="<webwork:url value="'/employeeView.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">View</a>
					</td>
				</tr>
			</webwork:iterator>
		</table>
</div>		

	</body>
</html>