<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Time Clock Activity by Date</title></head>
<body>


<h2>Time Clock Activity by Date</h2>

<webwork:form theme="'simple'" method="'post'" action="'timeclockActivityByDate.action'">

<webwork:textfield theme="'simple'" name="'targetDate'" value="targetDate" /> 

<a href="#" onclick="displayDatePicker('targetDate');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>

<webwork:submit theme="'simple'" name="'submit'" value="'Go'" />

</webwork:form>

<div id="generalTable">
<table >
<tr>
	<th>User</th>
	<th>Status</th>
	<th>Activity</th>
	<th>Hours</th>
</tr>
<webwork:iterator value="shifts" >
	<tr>
		<td><webwork:property value="current.user.username" /></td>
		<td><webwork:property value="status" /></td>
		<td>
		<a href="<webwork:url value="'timeclockEventModifySetup.action'">
			<webwork:param name="'targetUser'" value="current.user.username" />
			<webwork:param name="'targetDate'" value="targetDateSimple.mmddyyyy" />
			</webwork:url>">Add</a>
		<ul>
		<webwork:iterator value="displayableEvents">			
			<li><a href="<webwork:url value="'timeclockEventModifySetup.action'" ><webwork:param name="'target'" value="raw.id" /></webwork:url>"><webwork:property value="formattedEvent" />
			<webwork:property value="formattedEventTimestamp" /></a> <webwork:if test="raw.sourceip != null">(<webwork:property value="raw.sourceip" />)</webwork:if></li>
		</webwork:iterator>
		</ul>
		</td>
		<td><webwork:property value="hoursAndMinutes" /></td>
	<tr>
</webwork:iterator>

</table>
</div>


</body>
</html>