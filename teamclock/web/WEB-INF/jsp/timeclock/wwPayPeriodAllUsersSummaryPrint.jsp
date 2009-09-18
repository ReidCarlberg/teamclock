<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Pay Period Summary Report</title></head>
<body>


<h2>Pay Period Summary Report</h2>

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
		<td><webwork:property value="user.username" /></td>
		
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