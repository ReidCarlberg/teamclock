<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>

<div id="dashboardToDo"><table>
	<tr>
		<%--
		<td><a class="dashboard_header"
			href="<webwork:url value="'todolist.action'" />">To Do List</a></td>
		<td><a class="dashboard_header"
			href="<webwork:url value="'/todomodify.action'" />">+</a></td>--%>

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
<%--<a
			class="dashboard_header"
			href="<webwork:url value="'resequence.action'" />">Resequence</a>--%>
</td>
<%--
		<td><a class="dashboard_header"
			href="<webwork:url value="'homeFilter.action'" ><webwork:param name="'detail'" value="'x'"/></webwork:url>"><webwork:if
			test="dashboardContext.showingToDoDetail">Detail Off</webwork:if><webwork:else>Detail On</webwork:else></a>
		</td>--%>
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



</div>