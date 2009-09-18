<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib uri="webwork" prefix="webwork" %>

<graph xaxisname='Project Class' yaxisname='Hours' hovercapbg='DEDEBE' hovercapborder='889E6D' rotateNames='0' animation='0' yAxisMaxValue='100' numdivlines='12' divLineColor='CCCCCC' divLineAlpha='80' decimalPrecision='0' showAlternateHGridColor='1' AlternateHGridAlpha='30' AlternateHGridColor='CCCCCC' caption='Elapsed vs. Chargeable Hours' subcaption='Grouped by Project Class' >
   <categories font='Arial' fontSize='11' fontColor='000000'>
	   <webwork:iterator value="activitySummaryContext.summary">
   			<webwork:if test="label != 'Total'">
			   <category name='<webwork:property value="shortLabel" />' />
			</webwork:if>
		</webwork:iterator>
   </categories>
   <dataset seriesname='Elapsed' color='FDC12E' alpha='100'>
   	   <webwork:iterator value="activitySummaryContext.summary">
   			<webwork:if test="label != 'Total'">
			   <set value='<webwork:property value="elapsed" />' />
			</webwork:if>
		</webwork:iterator>
   </dataset>
    <dataset seriesname='Chargeable' color='56B9F9' showValues='1' alpha='100'>
    	   <webwork:iterator value="activitySummaryContext.summary">
   			<webwork:if test="label != 'Total'">
			    <set value='<webwork:property value="elapsedChargeable" />' />
			</webwork:if>
		</webwork:iterator>

   </dataset>

</graph>