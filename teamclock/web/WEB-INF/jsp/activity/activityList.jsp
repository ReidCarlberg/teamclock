<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
 
<html>
<head>
<title>Activity List</title>
</head>
<body>

<table width="100%">
<tr>
<td width="50%" valign="top">
<h2>Activity List</h2>
</td>
<td width="50%" valign="top" align="right">
<a href="<webwork:url value="'/activitylistPrint.action'"/>">Printer Friendly</a>
</td>
</tr>
</table>


<div id="listFilter" >	
	<webwork:form theme="'simple'" action="'activitylist.action'" method="'post'">
		<table >
			<tr>
				<time:userIsPrivileged>
					<td>User</td>
					<td>Group By User</td>
				</time:userIsPrivileged>
				<td>Project</td>
				<td>Task</td>
				<td>Start</td>
				<td>End</td>
				<td>Comments</td>
				<td>Return</td>
				<td>&nbsp</td>
			</tr>
			<tr>
				<time:userIsPrivileged>
					<td><webwork:select theme="'simple'" label="'User'" name="'params.username'" value="params.username"
					list="users" listKey="key" listValue="value" emptyOption="true"  /></td>
				    
				    <td>Yes <webwork:checkbox theme="'simple'" label="':user by group'" name="'groupByUser'" value="groupByUser" fieldValue="'true'"/></td>
				</time:userIsPrivileged>
								
				<td><webwork:select theme="'simple'" label="'Project'" name="'params.projectId'" value="params.projectId"
				list="projects" listKey="id" listValue="shortName" emptyOption="true"  /></td>
			
				<td><webwork:select theme="'simple'" label="'Task'" name="'params.taskId'" value="params.taskId"
				list="tasks" listKey="id" listValue="nameShort" emptyOption="true"  />
				</td>
				
				<td><webwork:textfield size="'8'" theme="'simple'" label="'Start'" name="'params.start'" value="params.start" />
<a href="#" onclick="displayDatePicker('params.start');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td>
				
				<td><webwork:textfield size="'8'" theme="'simple'" label="'Stop'" name="'params.stop'" value="params.stop" />
<a href="#" onclick="displayDatePicker('params.stop');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td>
				<td><webwork:textfield theme="'simple'" label="'Comments'" name="'params.comment'" value="params.comment" />
				</td>
	<td>
		<webwork:select theme="'simple'" label="'Complete'" name="'params.maxResults'" 
		value="params.maxResults"
		list="maximums" listKey="key" listValue="value" emptyOption="false" />	
	</td>
					
				<td>
					<webwork:submit theme="'simple'" value="'Search'" name="'searchActivities'" />
				</td>
			</tr>
		</table>
	</webwork:form>
</div>

<jsp:include page="_activityList.jsp" flush="true" />

</body>
 
</html>