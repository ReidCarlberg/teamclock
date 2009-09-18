<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>To Do</title>
		
	</head>
	<body>
		<table>
		<tr><td>
		<h2>To Do</h2>
		</td>
		<td valign="bottom"></td></tr>
		</table>
		
	
		
		<webwork:form action="'todomodify.action'" method="'post'">

		<webwork:select label="'Project'" name="'targetToDo.projectId'" value="targetToDo.projectId"
			list="projects" listKey="id" listValue="shortName" emptyOption="true" />

		<time:userIsPrivileged>

			<webwork:if test="sessionContext.rights.canHaveExternalUsers">
				<webwork:select label="'External User'" name="'targetToDo.externalUser'" value="targetToDo.externalUser"
					list="externalUsers" listKey="key" listValue="value" emptyOption="true"  />
			</webwork:if>

			<webwork:select label="'Entered By'" name="'targetToDo.enteredByUser'" value="targetToDo.enteredByUser"
				list="users" listKey="key" listValue="value" emptyOption="true"  />
	
			<webwork:select label="'Assigned To'" name="'targetToDo.assignedToUser'" value="targetToDo.assignedToUser" 
				list="users" listKey="key" listValue="value" emptyOption="true"  />

		</time:userIsPrivileged>

		<webwork:select label="'Priority'" name="'targetToDo.priority'" value="targetToDo.priority"
			list="allTypes" listKey="name" listValue="name" emptyOption="true"  />

		<webwork:textarea label="'Detail'" name="'targetToDo.detail'" value="targetToDo.detail" rows="5" cols="50" />
		
<tr>
<td align="right" class="label">
	Deadline:</td>
<td>	<webwork:textfield theme="'simple'" label="'Deadline'" name="'targetToDo.deadlineTimestamp'" value="targetToDo.deadlineTimestamp" /> 
<a href="#" onclick="displayDatePicker('targetToDo.deadlineTimestamp');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>

</td>
</tr>

		<webwork:textfield label="'Estimated Total Hours'" name="'targetToDo.estimatedTotalHours'" value="targetToDo.estimatedTotalHours" />

		<webwork:textfield label="'Estimated Remaining Hours'" name="'targetToDo.estimatedRemainingHours'" value="targetToDo.estimatedRemainingHours" />
		
		<webwork:select label="'Complete'" name="'surrogateComplete'" value="surrogateComplete"
			list="booleanSurrogate" listKey="key" listValue="value" emptyOption="false"  />
		

		
		
		<webwork:submit value="'Save'" name="'submitTodo'" />

		<webwork:submit value="'Cancel'" name="'cancelTodo'" />

		<webwork:if test="todoModifyContext.target != null">
			<webwork:submit value="'Delete'" name="'deleteTodo'" />
		</webwork:if>
		
		

		</webwork:form>
		
	</body>
</html>

