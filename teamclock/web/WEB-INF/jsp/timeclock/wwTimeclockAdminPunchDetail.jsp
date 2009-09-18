<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Timeclock Admin User Detail</title></head>
<body>





<h2>Time Clock Admin User Detail</h2>

<p>User: <webwork:property value="timeclockAdminContext.user.username" />

<p>Date: <webwork:property value="timeclockAdminContext.targetDate" />
 
<p>&nbsp;

<table width="100%" cellspacing="0" cellpadding="3" border="1" >	
	<tr>
           <th width="20%">Event</th>
           <th width="20%">Event Timestamp</th>
           <th width="15%">Modified Date</th>
           <th width="15%">Modified Time</th>
           <th width="30%">Comment</th>
 	</tr>  


	<webwork:iterator value="display">
      <tr>
		<td>
			<a href="<webwork:url value="'timeclockUserDetailModify.action'"><webwork:param name="'target'" value="raw.id" /></webwork:url>">
			<webwork:property value="formattedEvent"/>
			</a>
		</td>
        <td><webwork:property value="formattedEventTimestamp"/></td>
        <td><webwork:property value="formattedDate"/></td>
        <td><webwork:property value="formattedTimestamp"/></td>
        <td><webwork:property value="raw.comment"/> &nbsp;</td>
      </tr> 

	</webwork:iterator>
</table>

<p><a href="<webwork:url value="'timeclockUserDetailModify.action'"/>">Add a New Event</a>

</body>
</html>