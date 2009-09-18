<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<html>
	<head>
		<title>Calendar Record Modify</title>
	</head>
	<body>
		<h2>Calendar Record Modify</h2>
		
		<webwork:form action="'calendarmodify.action'" method="'post'">

		<webwork:select label="'Type'" name="'typeLuId'" value="typeLuId"
			list="types" listKey="id" listValue="fullName" 
			emptyOption="true"   />
			
<time:userIsPrivileged>
		<webwork:select label="'User'" name="'username'" value="username"
		list="users" listKey="key" listValue="value" emptyOption="true"  />
	

</time:userIsPrivileged>
			
				<webwork:select label="'Project'" name="'project'" value="project"
				list="projects" listKey="id" listValue="longName" emptyOption="true"  />
			
				 
				 <tr>
				<td>Start Date</td>
				<td>
				<webwork:textfield theme="'simple'" label="'StartDate'" name="'startDate'" value="startDate" />
				<a href="#" onclick="displayDatePicker('startDate');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a></td></tr>
				
				 
				<webwork:select label="'Hour'" name="'startHours'" value="startHours"
				list="startHoursSelection" listKey="id" listValue="id" emptyOption="false"  />
				
				<webwork:select label="'Min'" name="'startMinutes'" value="startMinutes"
				list="startMinutesSelection" listKey="id" listValue="id" emptyOption="false"  />
				 
				<webwork:select label="'Duration Hour'" name="'durationHours'" value="durationHours"
				list="durationHoursSelection" listKey="id" listValue="id" emptyOption="false"  />
				
				<webwork:select label="'Duration Min'" name="'durationMinutes'" value="durationMinutes"
				list="durationMinutesSelection" listKey="id" listValue="id" emptyOption="false"  />
				
				<webwork:textarea rows="'5'" cols="'30'" label="'Description'" name="'description'" value="description" />
				

			<webwork:submit value="'Save'" name="'submitCalendar'" />
			
			<webwork:submit value="'Cancel'" name="'cancelCalendar'" />
			
			<webwork:if test="calendarModifyContext.targetCalendar != null">
				<webwork:submit value="'Delete'" name="'deleteCalendar'" />
			</webwork:if>

		
		
		
 
		
		</webwork:form>
		
	</body>
</html>