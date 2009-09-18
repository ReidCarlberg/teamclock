<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>To Do Detail</title>
	</head>
	<body>
		<h1>To Do Detail</h1>
		
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
				<td valign="top">Created By</td>
				<td valign="top"><webwork:property value="todo.externalUser"/></td>
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
				<td valign="top">Complete</td>
				<td valign="top"><webwork:property value="todo.complete"/></td>
			</tr>		
		</table>
		
		<p>Comments 
		(<a href="<webwork:url value="'/extTodoComment.action'">
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

	</body>
</html>