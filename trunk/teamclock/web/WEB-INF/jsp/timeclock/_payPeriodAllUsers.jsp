<%@ taglib uri="webwork" prefix="webwork" %>
 

<div id="listTable">

<webwork:iterator value="allUserPayPeriods" >

	<h3><webwork:property value="user.username" /></h3>
	
	<table width="100%" cellspacing="0" cellpadding="3" border="1" >
		<tr>
		<%--
			<th>User</th>--%>
			<th >Start</th>
			<th >Status</th>
			<th >Time</th>
			<th >Break Time</th>
			<webwork:if test="showDetail">
				<th>Detail</th>
			</webwork:if>
		</tr>
		
	
		<webwork:iterator value="displayableShifts" >
			<tr>
				<%--<td ><webwork:property value="username" /></td> --%>
				<td ><webwork:property value="shiftStart" /></td>		
				<td ><webwork:property value="status" /></td>
				<td ><webwork:property value="roundedHoursAndHundredths" /></td>
				<td ><webwork:property value="roundedBreakHoursAndHundredths" /></td>
				<webwork:if test="showDetail">
				<td>
					<webwork:iterator value="displayableEvents" >
						<webwork:property value="formattedEventTimestamp"/> (<webwork:property value="formattedEvent"/>)
					</webwork:iterator>
				</td>
				</webwork:if>
			</tr>
		</webwork:iterator>
	</table>

	<p>Total Hours: <webwork:property  value="summary.totalRoundedHours" />

</webwork:iterator>

</div>

