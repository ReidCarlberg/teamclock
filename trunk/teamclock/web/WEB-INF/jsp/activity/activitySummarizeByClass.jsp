<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
 
<html>
<head>
<title>Activity - Summarize By Project Class</title>
</head>
<body>


<h2>Activity - Summarize By Project Class</h2>


<div id="listFilter" >	
	<webwork:form theme="'simple'" action="'activitySummarizeByClass.action'" method="'post'">
		<table >
			<tr>
				<time:userIsPrivileged>
					<td>User</td>
				</time:userIsPrivileged>
				<td>Start</td>
				<td>End</td>
				<td>Year</td>
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
				<td><webwork:textfield size="'8'" theme="'simple'" label="'Stop'" name="'targetYear'" value="targetYear" />
				</td>
				
				<td>
					<webwork:submit theme="'simple'" value="'Search'" name="'searchActivities'" />
					<webwork:submit theme="'simple'" value="'Monthly Summary'" name="'monthlySummary'" />
				</td>
			</tr>
		</table>
	</webwork:form>
</div>

<webwork:if test="summary != null">



 <div id="listTable" >
	<table>
		<tr>
			<th>Class</th>
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
	
<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/
flash/swflash.cab#version=6,0,0,0" WIDTH="375" HEIGHT="420" id="FC_2_3_Pie2D">
<PARAM NAME=movie VALUE="<webwork:url value="'/Charts/FC_2_3_Pie2D.swf'" />">
<PARAM NAME="FlashVars" VALUE="&dataURL=getSummaryDataForGraph%2Ejxml">
<PARAM NAME=quality VALUE=high>
<PARAM NAME=bgcolor VALUE=#FFFFFF>
<param name="wmode" value="transparent"> 
<EMBED src="<webwork:url value="'/Charts/FC_2_3_Pie2D.swf'" />" FlashVars="&dataURL=getSummaryDataForGraph%2Ejxml" quality=high bgcolor=#FFFFFF WIDTH="375" HEIGHT="420" NAME="FC_2_3_Bar2D" TYPE="application/x-shockwave-flash" PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer" wmode="transparent"></EMBED>
</OBJECT>


<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/
flash/swflash.cab#version=6,0,0,0" WIDTH="500" HEIGHT="420" id="FC_2_3_Pie2D">
<PARAM NAME=movie VALUE="<webwork:url value="'/Charts/FC_2_3_MSColumn2D.swf'" />">
<PARAM NAME="FlashVars" VALUE="&dataURL=getSummaryDataForGraph%2Ejxml%3Ftype%3Dchargeable">
<PARAM NAME=quality VALUE=high>
<PARAM NAME=bgcolor VALUE=#FFFFFF>
<param name="wmode" value="transparent"> 
<EMBED src="<webwork:url value="'/Charts/FC_2_3_MSColumn2D.swf'" />" FlashVars="&dataURL=getSummaryDataForGraph%2Ejxml%3Ftype%3Dchargeable" quality=high bgcolor=#FFFFFF WIDTH="500" HEIGHT="420" NAME="FC_2_3_Bar2D" TYPE="application/x-shockwave-flash" PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer" wmode="transparent"></EMBED>
</OBJECT>
</webwork:if>


<webwork:if test="monthly != null">

<webwork:iterator value="monthly">

<div id="listTable" >

<p><strong>Start: <webwork:property value="start" /> Stop: <webwork:property value="stop" /></strong></p>
 
	<table>
		<tr>
			<th>Class</th>
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
</webwork:iterator>

</webwork:if>

</body>
 
</html>