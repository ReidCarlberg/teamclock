<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>

	
	<!-- List the queued Todos -->
	<tr><th  class="dashboard_header" colspan="4" align="center">Queued Todos</th></tr>
	<webwork:iterator value="queuedTodos" status="toDoRow">
		<tr>
			<webwork:if test="status != null">
				<webwork:set name="displayClass" value="'dashboardToDo_Status'"/>
			</webwork:if>
			<webwork:elseif test="#toDoRow.modulus(3) == 1">
				<webwork:set name="displayClass" value="'dashboardToDo_Alternate'"/>
			</webwork:elseif>
			<webwork:else>
				<webwork:set name="displayClass" value="'dashboardToDo_Main'"/>			
			</webwork:else>
			
			<td class="<webwork:property value="#displayClass"/>" valign="top" width="80"><webwork:property value="projectName" /></td>
			
			<td class="<webwork:property value="#displayClass"/>" valign="top" width="*">

						<webwork:if test="dashboardContext.showingToDoDetail">
							<webwork:iterator value="toDoPriorities">
								<webwork:if test="priority != null && priority.equals(name)">
									<img align="top" border="0" src="<webwork:url value="iconOn" />">
								</webwork:if>
								<webwork:else>
									<a class="dashboard_header" href="<webwork:url value="'dashboardtodopriority.action'" >
										<webwork:param name="'target'" value="id" /><webwork:param name="'priority'" value="name"/></webwork:url>"><img align="top" border="0" src="<webwork:url value="icon" />"></a>
								</webwork:else>
							</webwork:iterator>						
	
							<webwork:if test="dashboardContext.priority != null">
								<a class="dashboard_header" href="<webwork:url value="'sooner.action'" >
									<webwork:param name="'target'" value="id" /></webwork:url>"><img border="0" align="top" src="<webwork:url value="'/images/icon-todo-sequence-sooner.gif'" />"></a>
		
								<a class="dashboard_header" href="<webwork:url value="'later.action'" >
									<webwork:param name="'target'" value="id" /></webwork:url>"><img border="0" align="top" src="<webwork:url value="'/images/icon-todo-sequence-later.gif'" />"></a>
							</webwork:if>
							
						</webwork:if>
						<webwork:property value="sequence" />

					<a href="<webwork:url value="'/tododetail.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
						<webwork:property value="detailShort" /></a>
					<time:userIsPrivileged>
						<webwork:if test="dashboardContext.showingToDoDetail">
							<br>
							<webwork:iterator value="activeUsers">
								<webwork:if test="!user.username.equals(dashboardContext.toDoUsername)">
									[<a class="dashboard_header" href="<webwork:url value="'dashboardtodoreassign.action'" >
										<webwork:param name="'target'" value="id" /><webwork:param name="'username'" value="user.username"/></webwork:url>"><webwork:property value="user.username"/></a>]
								</webwork:if>
							</webwork:iterator>
						</webwork:if>
					</time:userIsPrivileged>
					
			</td>
			
			<td class="<webwork:property value="#displayClass"/>" valign="top" width="60"><webwork:property value="friendlyDeadline"/></td>
			<td class="<webwork:property value="#displayClass"/>" valign="top" align="right" nowrap width="25">
				
				<a href="<webwork:url value="'/dashboardQueueAccept.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
				Accept</a>
				
			</td>
		</tr>
	</webwork:iterator>
