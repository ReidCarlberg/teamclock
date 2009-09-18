<?xml version="1.0" encoding="ISO-8859-1"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<%
response.setHeader("Content-Type","text/xml; charset=iso-8859-1"); 
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<ajax-response>
	<response type="element" id="timeClockStatusDiv">
	
	
					
<webwork:if test="timeclockResult != null">
	<webwork:property value="timeclockResult" />
</webwork:if>

<webwork:if test="showPunchIn">
	<a href="<webwork:url value="'timeclockPunchInDashboard.action'" />">Punch In</a>
</webwork:if>
	

<webwork:if test="showBreakStart">
	<a href="<webwork:url value="'timeclockBreakStartDashboard.action'" />">Break Start</a>
</webwork:if>


<webwork:if test="showBreakStop">
	<a href="<webwork:url value="'timeclockBreakStopDashboard.action'" />">Break Stop</a>
</webwork:if>


<webwork:if test="showPunchOut">
	| <a href="<webwork:url value="'timeclockPunchOutDashboard.action'" />">Punch Out</a>
</webwork:if>

		
				
- Total Hours - Pay Period: <webwork:property  value="userPayPeriodSummary.summary.totalRoundedHours" />
- Today: <webwork:property  value="todaySummary.summary.totalRoundedHours" />
	
	
				
				</response>
</ajax-response>
				
				