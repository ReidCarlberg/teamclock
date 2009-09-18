<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>


<html>
<head>
<title>Dashboard</title>



</head>
<body>





<div id="dashboardToDo"><table>
	<tr>


		<td><webwork:iterator value="toDoPriorities">
			<webwork:if
				test="dashboardContext.priority != null && dashboardContext.priority.equals(name)">
				<img align="top" border="0" src="<webwork:url value="iconOn" />">
			</webwork:if>
			<webwork:else>
				<a class="dashboard_header"
					href="<webwork:url value="'homeFilter.action'" >
								<webwork:param name="'priority'" value="name"/></webwork:url>"><img
					align="top" border="0" src="<webwork:url value="icon" />"></a>
			</webwork:else>
		</webwork:iterator> <a class="dashboard_header"
			href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'priority'" value="'all'"/></webwork:url>"><img
			align="top" border="0"
			src="<webwork:url value="'images/icon-todo-pall.gif'" />"></a> 

</td>

		<time:userIsPrivileged>
			<td><webwork:form method="'post'" action="'homeFilter.action'">
				<webwork:select theme="'simple'" label="'User'"
					name="'toDoUsername'" value="dashboardContext.toDoUsername"
					list="activeUsers" listKey="user.username"
					listValue="user.username" emptyOption="false"
					onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />
			</webwork:form></td>
		</time:userIsPrivileged>
		<td><webwork:form method="'post'" action="'homeFilter.action'">
			<webwork:select theme="'simple'" label="'Project'"
				name="'toDoProjectFilter'"
				value="dashboardContext.toDoProjectFilter" list="projects"
				listKey="id" listValue="shortName" emptyOption="false"
				onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />
		</webwork:form></td>
		<td class="dashboard_header"  cellpadding="0" cellspacing="0">

				<webwork:form method="'post'"  theme="'simple'" action="'homeFilter.action'">
					<webwork:select theme="'simple'" headerValue="'[ALL]'" headerKey ="''" 
				 		name="'toDoTagFilter'"
						value="dashboardContext.todoFilter.tagFriendly" list="tags"
						onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />
				</webwork:form>
	
		</td>
		<td class="dashboard_header" >
			<a class="dashboard_header" href="#" onclick="javascript:getUpdateToDo();"><img src="<webwork:url value="'/images/refresh-icon.gif'" />" border="0" alt="refresh"/></a></td>
		<td align="center">

			<webwork:form theme="'simple'" name="'todo'"
			action="'javascript:dashboardtodoaddAJAX(document.todo.detail);'" method="'post'">

			<table cellpadding="0" cellspacing="0">
				<tr>
					<td class="dashboard"><a
						href="help.action?file=dashboard/add-a-todo.jsp&amp;back=home.action"><img
						src="<webwork:url value="'/images/icon-help.gif'" />" border="0"
						align="middle" /></a>:</td>
					<td><input AUTOCOMPLETE="off" class="dashboard" name="detail"
						onkeyup="todoAutoComplete(event)" type="text" size="50"
						tabindex="1" value="" id="detail"></td>
					<td align="left"><webwork:submit theme="'simple'" name="'submit'"
						value="'Add'" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">
					<div id="toDoAutoCompleteDiv"></div>
					</td>
				</tr>
			</table>



		</webwork:form></td>
	</tr>
</table>

<div id="todoAJAXDiv" style="display:block; "></div>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'tag'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Tags</a>

			</th>
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'projectId'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Project</a>


			</th>

			<th width="20"></th>

			<th width="*">Item</th>


		</tr>


		<webwork:iterator value="myIncompleteToDos" status="toDoRow">
			<tr>
				<webwork:if test="status != null">
					<webwork:set name="displayClass" value="'dashboardToDo_Status'" />
				</webwork:if>
				<webwork:elseif test="#toDoRow.modulus(3) == 1">
					<webwork:set name="displayClass" value="'dashboardToDo_Alternate'" />
				</webwork:elseif>
				<webwork:else>
					<webwork:set name="displayClass" value="'dashboardToDo_Main'" />
				</webwork:else>






			     <td class="<webwork:property value="#displayClass"/>">
			    	 <webwork:property value="tagFriendly" />
				</td>
				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="80"><webwork:property value="projectName" /></td>

				<td class="<webwork:property value="#displayClass"/>"  width="45">
					
				<webwork:if test="status != null">
					<a href="<webwork:url value="'/dashboardtodoack.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">ACK</a>
				</webwork:if> 
			<a onClick="sendToDoComplete('<webwork:property value="id"/>')">
				<img src="<webwork:url value="'/images/icon-todo-complete.gif'" />"
					border="0" align="middle" /></a> 
					
			<a onClick="sendToDoToActivity('<webwork:property value="id"/>')">
				
				<img src="<webwork:url value="'/images/icon-todo-start.gif'" />"
					border="0" align="middle" /></a>


				<a onClick="moreToDoInformation('<webwork:property value="id"/>')"><img src="<webwork:url value="'/images/todo-icon-more.gif'" />" border="0" align="middle" /></a>

					</td>




				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="*">

					<ajax:wellformed><webwork:property value="detailFull" /></ajax:wellformed>
	
				</td>



			</tr>
		</webwork:iterator>





	</table>

<div id="dashboardToDoMoreInformation" >
<a onClick="closeMoreToDoInformation();">Close</a>
Here's more information.
</div>



</div>




<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["todo"].elements["detail"];

  if (focusControl.type != "hidden") {
     focusControl.focus();
  }
  // -->
</script>

</body>


</html>
