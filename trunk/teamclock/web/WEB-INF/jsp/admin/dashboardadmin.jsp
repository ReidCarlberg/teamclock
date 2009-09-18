<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Administrator Dashboard</title>
	</head>
	<body>

		<h1>Administrator Dashboard</h1>
		

		<p><a href="<webwork:url value="'/timeclockAdmin.action'" />">Time Clock Admin</a>

		<p><a href="<webwork:url value="'/tasklist.action'" />">Tasks</a>
		
		<p><a href="<webwork:url value="'/customerlist.action'" />">Customers</a>
		
		<p><a href="<webwork:url value="'/projectlist.action'" />">Projects</a>
		
		
		<time:userIsOwner>

			<p><a href="<webwork:url value="'/settingsModify.action'" />">System Settings</a></p>
	
			<p><a href="<webwork:url value="'/userList.action'" />">Users</a></p>
			
			<p><a href="<webwork:url value="'/employeeList.action'" />">Employees</a></p>

			<p><a href="<webwork:url value="'/teamList.action'" />">Teams</a></p>

			<p><a href="<webwork:url value="'/lookupsList.action'" />">Lookups</a></p>

			<p><a href="<webwork:url value="'/webformlist.action'" />">WebForms</a></p>
			
			<p><a href="<webwork:url value="'/messageList.action'" />">Messages</a></p>
			
			<p><a href="<webwork:url value="'/sendCustomerMessage.action'" />">Send Message To Customer</a></p>
		
			
		</time:userIsOwner>

		<p><a href="<webwork:url value="'/loginHistoryList.action'" />">Login History</a>
		
		<webwork:if test="sessionContext.rights.canUseAccountTransactions">
			<p><a href="<webwork:url value="'/unpostedActivityList.action'" />">Show Unposted Activity</a>
		</webwork:if>
		
		<%-- 2006-07-05 reid doesn't work
		<webwork:if test="sessionContext.featureComplete">
			<p><a href="<webwork:url value="'/qmList.action'" />">List Unsent Queued Messages</a>
		</webwork:if>
		--%>

		<webwork:if test="sessionContext.featureSuperUser">
			<p><a href="<webwork:url value="'/superUserDashboard.action'" />">Super User Dashboard</a>
		</webwork:if>


		
	</body>
</html>