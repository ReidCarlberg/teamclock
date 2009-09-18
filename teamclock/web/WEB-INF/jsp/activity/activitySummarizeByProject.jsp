<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
 
<html>
<head>
<title>Activity - Summarize By Project</title>
</head>
<body>


<h2>Activity - Summarize By Project</h2>


<div id="listFilter" >	
	<webwork:form theme="'simple'" action="'activitySummarizeByProject.action'" method="'post'">
		<table >
			<tr>
				<time:userIsPrivileged>
					<td>User</td>
				</time:userIsPrivileged>
				<td>Start</td>
				<td>End</td>
				<td>&nbsp</td>
			</tr>
			<tr>
				<time:userIsPrivileged>
					<td><webwork:select theme="'simple'" label="'User'" name="'params.username'" value="params.username"
					list="userListProvider.internalUsersAll" listKey="key" listValue="value" emptyOption="true"  /></td>
				</time:userIsPrivileged>
								
				<td><webwork:textfield size="'8'" theme="'simple'" label="'Start'" name="'params.start'" value="params.start" />
<a href="#" onclick="displayDatePicker('params.start');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td>
				
				<td><webwork:textfield size="'8'" theme="'simple'" label="'Stop'" name="'params.stop'" value="params.stop" />
<a href="#" onclick="displayDatePicker('params.stop');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td>
				<td>
					<webwork:submit theme="'simple'" value="'Search'" name="'searchActivities'" />
				</td>
			</tr>
		</table>
	</webwork:form>
</div>

<webwork:if test="summary != null">
 <div id="listTable" >
	<table>
		<tr>
			<th>Project Key (Name)</th>	
			<th>Count</th>
			<th>Elapsed Hours</th>
			<th>Chargeable Hours</th>
			<th>Chargeable Percent</th>
			<th>Percent of Shift (Elapsed)</th>
		</tr>

		<webwork:iterator value="summary">
			<tr>
				<td><webwork:property value="label" /></td>

				<td><webwork:property value="count" /></td>
				<td><webwork:property value="elapsedFormatted" /></td>
				<td><webwork:property value="elapsedChargeableFormatted" /></td>
				<td><webwork:property value="chargeablePercentFormatted" /></td>
				<td><webwork:property value="elapsedPercentOfShiftTimeFormatted" /></td>
			</tr>
		</webwork:iterator>
	</table>
	
	<webwork:include page="_summaryHours.jsp" />
	
	</div>
</webwork:if>

</body>
 
</html>