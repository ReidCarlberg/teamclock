<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Time Clock Home</title></head>
<body>





<h2>Time Clock</h2>


<table>
<tr>
<td width="50%" valign="top">

<h2>Actions</h2>
 <webwork:if test="timeclockResult != null">
 	<p><webwork:property value="timeclockResult" />
 </webwork:if>

<p><webwork:if test="showPunchIn">
		<a href="<webwork:url value="'timeclockPunchIn.action'" />">Punch In</a>
	</webwork:if>
	<webwork:else>
		Punch In
	</webwork:else>

<p><webwork:if test="showBreakStart">
	<a href="<webwork:url value="'timeclockBreakStart.action'" />">Break Start</a>
</webwork:if>
<webwork:else>
Break Start
</webwork:else>

<p><webwork:if test="showBreakStop">
<a href="<webwork:url value="'timeclockBreakStop.action'" />">Break Stop</a>
</webwork:if>
<webwork:else>
Break Stop
</webwork:else>

<p><webwork:if test="showPunchOut">
	<a href="<webwork:url value="'timeclockPunchOut.action'" />">Punch Out</a>
	</webwork:if>
	<webwork:else>
Punch Out
</webwork:else>


</td>
<td width="50%" valign="top">

<h2>Shift Summary</h2>

<jsp:include page="_wwPunchDetail.jsp" flush="true" />

</td>
</tr>
</table>

<jsp:include page="_currentTime.jsp" flush="true" />

<h3>Notes</h3>

<ul>
<li>Punch In and Punch Out controls time recorded on the clock.  Punch in to start recording, punch out to stop recording.
<li>Break Start and Break Stop records break time <strong>on the clock</strong>.
</ul>

</body>
</html>