<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Time Clock Admin</title></head>
<body>


<h1>Time Clock User Pay Period</h1>

<div id="listTable" >

<table >
	<tr>
		<th class="fieldlabel_normal" width="20%">User</th>
		<th class="fieldlabel_normal" width="20%">Start</th>
		<th class="fieldlabel_normal" width="20%">Status</th>
		<th class="fieldlabel_normal" width="20%">Time</th>
		<th class="fieldlabel_normal" width="20%">Break Time</th>
		
	</tr>
	

	<webwork:iterator value="userPayPeriodSummary.displayableShifts" >
		<tr>
			<td class="report"><webwork:property value="username" /></td>		
			<td class="report">
				<a href="
					<webwork:url value="'timeclockUserPunchDetail.action'">
						<webwork:param name="'targetDate'" value="shiftStart" />
					</webwork:url>
				">
				<webwork:property value="shiftStart" />
				</a>
			</td>		
			<td class="report"><webwork:property value="status" /></td>
			<td class="report"><webwork:property value="roundedHoursAndHundredths" /></td>
			<td class="report"><webwork:property value="roundedBreakHoursAndHundredths" /></td>			
			
		</tr>
	</webwork:iterator>
</table>

</div>

<p>Total Hours: <webwork:property  value="userPayPeriodSummary.summary.totalRoundedHours" />

<p>&nbsp



</body>
</html>