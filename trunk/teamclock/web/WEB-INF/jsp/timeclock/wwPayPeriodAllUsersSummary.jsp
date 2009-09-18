<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Pay Period Summary, All Users</title></head>
<body>


<h2>Pay Period Summary, All Users</h2>

<p><a href="<webwork:url value="'timeclockPayPeriodAllUsersSummaryPrint.action'"><webwork:param name="'targetPeriod'" value="targetPeriod"/></webwork:url>">Printer Friendly</a>

<p>Period: <webwork:property value="targetPeriod" /> 
(<webwork:property value="startStop.start" /> - <webwork:property value="startStop.end" />)
<table>
	<tr>
		<th>Username</th>
		<th>Total Hours</th>
		<th>Notes</th>
	</tr>
<webwork:iterator value="allUserPayPeriods" >
	<tr>
		<td><a href="<webwork:url value="'timeclockUserPayPeriod.action'"><webwork:param name="'targetUser'" value="user.username"/><webwork:param name="'targetPeriod'" value="targetPeriod"/></webwork:url>"><webwork:property value="user.username" /></a></td>
		
		<td><webwork:property  value="summary.totalRoundedHours" /></td>
		<td>
			<webwork:iterator value="displayableShifts" >
				<webwork:if test="status.equalsIgnoreCase(\"INCOMPLETE\")">
					<webwork:property value="shiftStart" /> Incomplete
				</webwork:if>
			</webwork:iterator>		
		</td>
	</tr>

</webwork:iterator>
</table>



<p>&nbsp



</body>
</html>