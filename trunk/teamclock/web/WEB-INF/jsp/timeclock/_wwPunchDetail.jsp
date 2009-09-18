<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>

<div id="listTable" >
<table width="100%" >	
	<tr >
           <th>Event</th>
           <th>Event Timestamp</th>
           <th>Comment</th>
		<time:userIsPrivileged>
			<th>&nbsp;</th>
		</time:userIsPrivileged>
 	</tr>  


	<webwork:iterator value="display">
	 
      <tr>
		<td><webwork:property value="formattedEvent"/></td>
        <td><webwork:property value="formattedEventTimestamp"/></td>
        <td><webwork:property value="raw.comment"/>&nbsp;</td>
		<time:userIsPrivileged>
        		<td><a href="<webwork:url value="'timeclockUserDetailModifytch.action'"><webwork:param name="'target'" value="raw.id" /></webwork:url>">Adjust</a></td>
		</time:userIsPrivileged>
      </tr> 

	</webwork:iterator>
	
</table>

<p>Total Hours: <webwork:property  value="todaySummary.summary.totalRoundedHours" />

<time:userIsPrivileged>
	<a href="<webwork:url value="'timeclockUserDetailModifytch.action'"/>">Add New Event</a>
</time:userIsPrivileged></p>




</div>
