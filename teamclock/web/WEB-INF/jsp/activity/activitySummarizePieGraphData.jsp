<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib uri="webwork" prefix="webwork" %>

<graph caption='Elapsed Time By Project Class' decimalPrecision='2' showPercentageValues='1' showNames='1' 
	numberPrefix='' showValues='0' showPercentageInLabel='0' pieYScale='45' pieBorderAlpha='100' 
	 animation='0' shadowXShift='4' shadowYShift='4' shadowAlpha='40' pieFillAlpha='95' 
	pieBorderColor='FFFFFF'>

	<webwork:iterator value="activitySummaryContext.summary">
		
		<webwork:if test="label != 'Total'">
		   <set value='<webwork:property value="elapsed" />' 
		   	name='<webwork:property value="shortLabel" />' />
		</webwork:if>
	</webwork:iterator>
</graph>