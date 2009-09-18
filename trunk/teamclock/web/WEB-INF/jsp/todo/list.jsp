<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>To Do List</title>
	</head>
	<body>
	
	<table width="100%">
		<tr>
			<td width="50%">
				<h2>To Do List</h2>
			</td>
			<td width="50%" align="right">
				<a href="<webwork:url value="'todomodify.action'" />" >Add</a> |
				
				<a href="<webwork:url value="'todolistPrint.action'" />" >Printer Friendly</a>
			</td>
		</tr>
	</table>

<div id="listFilter" >		
		<webwork:form action="'todolist.action'" method="'post'">		
		<table  >
			<tr>
				<td>Project</td>


				<time:userIsPrivileged>
					<td>Entered By</td>
				</time:userIsPrivileged>
				<td>Details</td>
				<td>Created</td>
				<td>Complete</td>
				<td>Return</td>
				<td>&nbsp;</td>
			</tr>
		
			<tr>
			<td>
			<webwork:select theme="'simple'" label="'Project'" name="'params.projectId'" list="projects" 
				listKey="id" listValue="shortName" emptyOption="true" />
				</td>

				<time:userIsPrivileged>
	
					<td>
					<webwork:select theme="'simple'" label="'Entered By'" name="'params.enteredByUser'" 
						list="users" listKey="key" listValue="value" emptyOption="true" />
					</td>

				</time:userIsPrivileged>
					

				
				<td>
			<webwork:textfield theme="'simple'" label="'Details'" name="'params.detailLike'" value="params.detailLike" />
			</td>


			<td>
				<webwork:textfield size="'9'" theme="'simple'" label="'Start'" name="'params.createTimestampRangeStart'" value="params.createTimestampRangeStart" />
<a href="#" onclick="displayDatePicker('params.createTimestampRangeStart');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
			
				
				<webwork:textfield size="'9'" theme="'simple'" label="'Stop'" name="'params.createTimestampRangeStop'" value="params.createTimestampRangeStop" />
<a href="#" onclick="displayDatePicker('params.createTimestampRangeStop');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td>

			</td>
			<td>
			<webwork:select theme="'simple'" label="'Complete'" name="'params.todoComplete'" 
				list="{'true','false'}"  emptyOption="true" />
</td>
	<td>
		<webwork:select theme="'simple'" label="'Complete'" name="'params.maxResults'" 
		value="params.maxResults"
		list="maximums" listKey="key" listValue="value" emptyOption="false" />	
	</td>
	
			<td>
			<webwork:submit theme="'simple'" value="'Search'" name="'submitList'" />			
			<webwork:submit theme="'simple'" value="'Reset'" name="'resetList'" />	
			</td>
			
</tr>

			</table>
		</webwork:form>
</div>
		
<jsp:include page="_list.jsp" flush="true" />
		

	</body>
</html>