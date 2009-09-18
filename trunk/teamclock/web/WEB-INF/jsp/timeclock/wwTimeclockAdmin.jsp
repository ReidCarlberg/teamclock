<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Time Clock Admin</title></head>
<body>



<webwork:if test="sessionContext.systemOwner.account != 'Standard'">
<div id="mainColumn">
</webwork:if>

<h2>Time Clock Admin</h2>



<p><a href="<webwork:url value="'/userList.action'" />" />User Management</a></p>

<p><a href="<webwork:url value="'/timeclockActivityByDate.action'" />">Time Clock Activity By Date (Today is default)</a></p>

<p>Show summary time clock report for all users.  
	
	<a href="<webwork:url value="'timeclockPayPeriodAllUsersSummary.action'"><webwork:param name="'targetPeriod'" value="'current'"/></webwork:url>">Current Period</a>
	|
	<a href="<webwork:url value="'timeclockPayPeriodAllUsersSummary.action'"><webwork:param name="'targetPeriod'" value="'previous'"/></webwork:url>">Previous Period</a></p>

<p>Show time clock entries for all users.  
	
	<a href="<webwork:url value="'timeclockPayPeriodAllUsers.action'"><webwork:param name="'targetPeriod'" value="'current'"/></webwork:url>">Current Period</a>
	
	(<a href="<webwork:url value="'timeclockPayPeriodAllUsers.action'"><webwork:param name="'targetPeriod'" value="'current'"/><webwork:param name="'showDetail'" value="'true'"/></webwork:url>">With Detail</a>)
	
	|
	<a href="<webwork:url value="'timeclockPayPeriodAllUsers.action'"><webwork:param name="'targetPeriod'" value="'previous'"/></webwork:url>">Previous Period</a>
	(<a href="<webwork:url value="'timeclockPayPeriodAllUsers.action'"><webwork:param name="'targetPeriod'" value="'previous'"/><webwork:param name="'showDetail'" value="'true'"/></webwork:url>">With Detail</a>)
	</p>

<p>Show time clock entries for a specific user.  (You can also add, delete or modify, through this list).
	
	Select a period *or* a date value.</p>
	
	<webwork:form action="'timeclockUserPayPeriod.action'" method="'post'">
		<webwork:select label="'User'" name="'targetUser'" 
			list="internalUsers" listKey="key" listValue="value" emptyOption="true" />
		<webwork:select label="'Period'" name="'targetPeriod'"
			list="{'Current','Previous','By Date'}" emptyOption="false" />
		

				 <tr>
				<td>Date</td>
				<td>
				<webwork:textfield theme="'simple'" label="'Date'" name="'targetDate'" value="targetDate" />
				<a href="#" onclick="displayDatePicker('targetDate');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a></td></tr>

	
		<webwork:submit value="'Go'" />
		
	</webwork:form>

		


<p><a href="<webwork:url value="'activitySummarizeByClass.action'" />">Summarize Activity By Project Class</a> or 

<a href="<webwork:url value="'activitySummarizeByProject.action'" />">Summarize Activity By Project Name</a></p>

<webwork:if test="sessionContext.systemOwner.account != 'Standard'">
</div>
<div id="rightColumn">

<h2>Know More</h2>

<p>An employee time clock helps you track when your employees are working. But sometimes you need to know what they're working on.</p>

<p>TeamClock.com Standard Edition helps you organize employee time clock activity, to-do lists, activity tracking, contacts and more.  </p>

<p><a href="<webwork:url value="'updateAccount.action'"/>">Upgrade here.</a></p>

<h2>An Integrated Solution</h2>

<p>TeamClock.com Standard Edition is an integrated solution designed to help you track common business information about both customers and employees.  This includes employee activity, customer requests, contacts and more.</p>

<h2>Upgrading is Easy</h2>

<p>Upgrading is easy.  Simply change your <a href="<webwork:url value="'updateAccount.action'"/>">account settings</a>.  And, if you decide you want to cancel, cancelling is just as easy and there's no obligation.</p>

</div>
</webwork:if>



</body>
</html>