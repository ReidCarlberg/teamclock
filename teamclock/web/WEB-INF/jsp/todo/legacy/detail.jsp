<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>To Do Detail</title>
	</head>
	<body>
		<h1>To Do Detail</h1>
		
		<p><a href="<webwork:url value="'/todomodify.action'">
					<webwork:param name="'target'" value="todo.id" />
					</webwork:url>">Edit</a>
	<table>
	  <tr>
	    <td>
		<table>
			<tr>
				<td valign="top">Customer</td>
				<td valign="top"><webwork:property value="todo.customerName"/></td>
			</tr>		
			<tr>
				<td valign="top">Project</td>
				<td valign="top"><webwork:property value="todo.projectName"/></td>
			</tr>	
			<tr>
				<td valign="top">Tags</td>
				<td valign="top"><webwork:property value="todo.tagFriendly"/></td>
			</tr>		
			<webwork:if test="sessionContext.rights.canHaveExternalUsers">			
				<tr>
					<td valign="top">External User</td>
					<td valign="top"><webwork:property value="todo.externalUser"/></td>
				</tr>
			</webwork:if>
			<tr>
				<td valign="top">Entered By</td>
				<td valign="top"><webwork:property value="todo.enteredByUser"/></td>
			</tr>
			<tr>
				<td valign="top">Assigned To</td>
				<td valign="top"><webwork:property value="todo.assignedToUser"/></td>
			</tr>
			<tr>
				<td valign="top">Detail</td>
				<td valign="top"><webwork:property value="todo.detail"/></td>
			</tr>
			<tr>
				<td valign="top">Sequence</td>
				<td valign="top"><webwork:property value="todo.sequence"/></td>
			</tr>
			<tr>
				<td valign="top">Priority</td>
				<td valign="top"><webwork:property value="todo.priority"/></td>
			</tr>			<tr>
				<td valign="top">Deadline</td>
				<td valign="top"><webwork:property value="todo.friendlyDeadline"/></td>
			</tr>									
			<tr>
				<td valign="top">Complete</td>
				<td valign="top"><webwork:property value="todo.complete"/></td>
			</tr>		
			
			
		</table>
		
		<p>Comments 
		(<a href="<webwork:url value="'/todocomment.action'">
					<webwork:param name="'target'" value="id" />
					</webwork:url>">Add</a>)

		<table>
			<tr>
				<td>Comment</td>
				<td>By</td>
				<td>Timestamp</td>
			</tr>
			<webwork:iterator value="comments" >
				<tr>
					<td><webwork:property value="comment"/></td>
					<td><webwork:property value="username"/></td>
					<td><webwork:property value="timestamp"/></td>
				</tr>			
			</webwork:iterator>
		</table>
	    </td>
	    <td>
			<webwork:if test="sessionContext.rights.canUseBetaFeatures">
		    	<table border="1" cellspacing="0">
				<tr><td colspan="3" align="center">Schedule</td></tr>
				  <tr>
				  <th>Description</th>
				  <th>Frequency</th>
				  <th>InitiationDate</th>
				  </tr>
				<webwork:iterator value="todo.schedules" >
					<tr>
					
						<td valign="top"><webwork:property value="description"/></td>
						<td valign="top"><webwork:property value="frequency"/> x <webwork:property value="multiplier"/></td>
						<td valign="top" align="right"><webwork:property value="initiationDate"/></td>
					</tr>			
				</webwork:iterator>
				</table>
			</webwork:if>
	    
	    </td>
	  </tr>
	</table>
		
	</body>
</html>