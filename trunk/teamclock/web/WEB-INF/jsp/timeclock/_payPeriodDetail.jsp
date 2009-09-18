<%@ taglib uri="webwork" prefix="webwork" %>

<table width="100%" cellspacing="0" cellpadding="3" border="1" >
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
					<webwork:url value="'timeclockDetail.action'">
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

<p>Total Hours: <webwork:property  value="userPayPeriodSummary.summary.totalRoundedHours" />


