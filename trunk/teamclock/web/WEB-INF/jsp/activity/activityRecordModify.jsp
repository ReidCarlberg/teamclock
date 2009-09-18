<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Activity Record Modify</title>
	</head>
	<body>
		<h2>Activity Record Modify</h2>
		
		<webwork:form action="'activityrecordmodify.action'" method="'post'">

				<time:userIsPrivileged>		 
					<webwork:select label="'User'" name="'targetActivity.username'" value="targetActivity.username"
					list="users" listKey="key" listValue="value" emptyOption="true"  />
				</time:userIsPrivileged>				
			
<webwork:select label="'Project'" name="'targetActivity.projectId'" value="targetActivity.projectId"
				list="projects" listKey="id" listValue="shortName" emptyOption="true"  />


			
<webwork:select label="'Task'" name="'targetActivity.taskId'" value="targetActivity.taskId"
				list="tasks" listKey="id" listValue="nameShort" emptyOption="true"  />
				
				

				<tr>
				<td align="right" class="label">Date:</td>
				<td>
				<webwork:textfield theme="'simple'" label="'Date'" name="'targetActivity.date'" value="targetActivity.date" />
<a href="#" onclick="displayDatePicker('targetActivity.date');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td></tr>
				
<webwork:textfield label="'Start time'" name="'startString'" value="startString" />

<webwork:textfield label="'Stop time'" name="'stopString'" value="stopString" />
				
<webwork:textfield label="'Elapsed (Read only)'" name="'targetActivity.elapsed'" value="targetActivity.elapsed" readonly="true" />

<webwork:textfield label="'Chargeable'" name="'targetActivity.chargeableElapsed'" value="targetActivity.chargeableElapsed" />

		<webwork:textarea rows="'6'" cols="'30'" label="'Comments'" name="'targetActivity.comments'" value="targetActivity.comments" />
		
			<webwork:submit  value="'Save'" name="'submitActivity'" />
			<webwork:submit  value="'Save & Override Chargeable'" name="'submitAndOverrideChargeable'" />			
			<webwork:submit  value="'Cancel'" name="'cancelActivity'" />
			<webwork:if test="activityModifyContext.targetActivity != null">
				<webwork:submit  value="'Delete'" name="'deleteActivity'" />
			</webwork:if>
		
		
		
 
		
		</webwork:form>
		
	</body>
</html>