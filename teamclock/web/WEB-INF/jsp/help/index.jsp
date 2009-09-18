<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="/WEB-INF/tlds/fstxtime.tld" prefix="time" %>


<html>
	<head>
		<title>FSTX Time Help</title>
	</head>
	<body>

<h2>Dashboard</h2>

<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'dashboard/add-a-todo.jsp'" />
</webwork:url>">To Do / Assignment Quick Add</a>


<h2>Time Clock</h2>

<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'general/timeclockAdministration.jsp'" />
</webwork:url>">Time Clock Administration</a>


<h2>Admin</h2>

<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'general/addingCustomersProjectsTasks.jsp'" />
</webwork:url>">Adding Customers, Projects and Tasks</a>

<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'general/userRights.jsp'" />
</webwork:url>">User Rights</a>

<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'general/settings.jsp'" />
</webwork:url>">Settings</a>

<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'general/productivity.jsp'" />
</webwork:url>">Pay Period Productivity Report</a>

<time:userIsOwner>
<h2>Update History</h2>
<p><a href="<webwork:url value="'help.action'">
	<webwork:param name="'back'" value="'help.action'" />
	<webwork:param name="'file'" value="'updates/2004-12-06.jsp'" />
</webwork:url>">Updates by Date</a>

</time:userIsOwner>
		
	</body>
</html>