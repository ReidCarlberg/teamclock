<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Scheduled To Do List</title>
	</head>
	<body>
		<h2>Scheduled To Do List</h2>

		
			
		<table border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
		
		<tr>
		<th>Customer</th>
		<th>Project</th>
		<th>Assigned User</th>
		<th>To Do Description</th>
		<th>Next Initiation Date</th>
		<th>Frequency</th>
		<th>Description</th> 
		</tr>
		<webwork:iterator value="scheduledTodos">
			<tr>
				<td valign="top"><webwork:property value="toDo.customerName"/>&nbsp;</td>
				<td valign="top"><webwork:property value="toDo.projectName" />&nbsp;</td>
				<td valign="top"><webwork:property value="toDo.assignedToUser" />&nbsp;</td>
				<td valign="top">
					<a href="<webwork:url value="'todomodify.action'" >
						<webwork:param name="'target'" value="toDo.id" />
					</webwork:url>"><webwork:property value="toDo.detail" /></a>&nbsp;</td>
				<td valign="top"><webwork:property value="initiationDate"/>&nbsp;</td>
				<td valign="top"><webwork:property value="frequency"/> <webwork:property value="multiplier"/>&nbsp;</td>
								<td valign="top"><webwork:property value="description"/>&nbsp;</td>
			</tr>
		</webwork:iterator>
		
		</table>
		

	</body>
</html>