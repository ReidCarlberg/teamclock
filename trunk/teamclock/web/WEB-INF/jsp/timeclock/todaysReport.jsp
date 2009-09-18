<%@ taglib uri="/WEB-INF/webwork.tld" prefix="webwork" %>



<html>

<head>
<meta http-equiv="Refresh" content="15;URL=<webwork:url value="action" />">
</head>

<body>


<h2>Today's Report</h2>

<p> <a href="<webwork:url value="action" />">Return to the Time Clock</p>

<div id="generalTable">
<table  >	
	<tr >
           <th>Event</th>
           <th>Time</th>
 	</tr>  

<webwork:if test="display == null">
	<p>display is null</p>
</webwork:if>
<webwork:else>
	<webwork:iterator value="display">
	 
      <tr>
		<td><webwork:property value="formattedEvent"/></td>
        <td><webwork:property value="formattedEventTimestamp"/></td>
      </tr> 

	</webwork:iterator>
</webwork:else>
</table>
</div>

<p>This page times out after 15 seconds.  Use the regular login to view your full report.</p>
 
</body></html>



