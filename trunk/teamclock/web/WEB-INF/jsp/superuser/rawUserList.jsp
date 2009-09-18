<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Raw User List</title>
	</head>
	<body>
		<h1>Raw User List</h1>

<div id="listTable" >
		<table >
		<tr>
			<th>Username</th>
			<th>Email</th>
			<th>Actions</th>
		</tr>
		<webwork:iterator value="users">
			<tr>
				<td><webwork:property value="username" /></td>
				<td><webwork:property value="email" /></td>
				
				<td>
					<a href="<webwork:url value="'/ownerUserResetPassword.action'"><webwork:param name="'targetUser'" value="username" /></webwork:url>">Reset Password</a>					
				</td>
			</tr>		
		</webwork:iterator>
		</table>
</div>		

	</body>
</html>