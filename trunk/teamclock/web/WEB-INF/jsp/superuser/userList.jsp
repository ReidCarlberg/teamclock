<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>User List</title>
	</head>
	<body>
		<h1>User List</h1>

		<p>Owner: <webwork:property value="systemOwnerManagerContext.activeSystemOwner.name" />
		
		<div id="listTable">
			<table width="100%">
			<tr>
				<th>Username</th>
				<th>Type</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
			<webwork:iterator value="systemUsers">
				<tr>
					<td><webwork:property value="user.username" /></td>
					<td><webwork:property value="userTypeEnum.publicName" /></td>
					<td><webwork:property value="user.email" /></td>
					<td>
						<a href="<webwork:url value="'/ownerUserResetPassword.action'"><webwork:param name="'targetUser'" value="user.username" /></webwork:url>">Reset Password</a>					

						<a href="<webwork:url value="'/ownerPasswordModify.action'"><webwork:param name="'targetUser'" value="user.username" /></webwork:url>">Change Password</a>		

						<a href="<webwork:url value="'/ownerUserModify.action'"><webwork:param name="'targetUser'" value="user.username" /></webwork:url>">Modify User</a>		
					</td>
				</tr>		
			</webwork:iterator>
			</table>
		</div>
	</body>
</html>