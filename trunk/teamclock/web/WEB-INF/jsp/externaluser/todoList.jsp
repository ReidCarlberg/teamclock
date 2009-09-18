<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Incomplete To-Do's</title>
	</head>
	<body>
		<h1>Incomplete To-Do's - <webwork:property value="sessionContext.externalUserSessionContext.activeCustomer.name" /></h1>
		
<p><a href="<webwork:url value="'/extTodoModify.action'"/>">Create a New To-Do</a></p>

<div id="listTable">

<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td valign="top">Project</td>
		<td valign="top">Detail</td>
		<td valign="top">Assigned To</td>
		<td valign="top">Deadline</td>
	</tr>
	<webwork:iterator value="todos">
		<tr>
			<td valign="top">
				<a href="<webwork:url value="'/extTodoDetail.action'">
					<webwork:param name="'target'" value="id" />
				</webwork:url>"><webwork:property value="projectName"/></a>

			</td>
			<td valign="top"><webwork:property value="detail"/></td>
			<td valign="top"><webwork:property value="assignedToUser"/></td>
			<td valign="top"><webwork:property value="friendlyDeadline"/></td>

		</tr>				
	</webwork:iterator>


</table>

</div>

	</body>
</html>