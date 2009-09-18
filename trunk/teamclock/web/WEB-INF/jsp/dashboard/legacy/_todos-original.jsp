<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>



	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th width="5"></th>
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'projectId'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Project</a>


			</th>
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'tag'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Tags</a>

			</th>
			<th width="*">Item</th>
			<%--
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'deadlineTimestamp'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Deadline</a>

			</th>
			--%>
			<th width="20"></th>
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


				<td width="5">
				
				<webwork:if test="dashboardContext.showingToDoDetail">
					<webwork:checkbox theme="'simple'" name="'todosToTag'"
						fieldValue="id" />
				</webwork:if>
				
				</td>


				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="80"><webwork:property value="projectName" /></td>
				<td class="<webwork:property value="#displayClass"/>" valign="top" width="80">

			     
			     <webwork:if test="dashboardContext.showingToDoDetail">
			    	 <webwork:property value="tagFriendly" />
			      </webwork:if>
			      <webwork:else>
		
				
				 
				<div  class="todoTagDashboardForm" style="display:block;width:50;" id="todoTagDiv<webwork:property value="id" />" onClick="javascript:expandById('todoTagFormDiv<webwork:property value="id" />', null);
					      javascript:collapseById('todoTagDiv<webwork:property value="id" />');
					      javascript:replaceTagDiv('todoTagFormDiv<webwork:property value="id" />', '<webwork:property value="id" />',document.getElementById('todoTagRawDiv<webwork:property value="id" />').innerHTML);">	
					<webwork:property value="tagFriendly" />&#160;&#160;
				</div>	
				<div style="display:none;" id="todoTagFormDiv<webwork:property value="id" />">	
				
				</div>
				<div style="display:none;" id="todoTagRawDiv<webwork:property value="id" />">	
					<webwork:property value="tagRaw" />
				</div>		
				
				
				</webwork:else>
				
				</td>


				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="*"><webwork:if test="dashboardContext.showingToDoDetail">
					<webwork:iterator value="toDoPriorities">
						<webwork:if test="priority != null && priority.equals(name)">
							<img align="top" border="0" src="<webwork:url value="iconOn" />" />
						</webwork:if>
						<webwork:else>

							<a
								href="<webwork:url value="'dashboardtodopriority.action'" >
										<webwork:param name="'target'" value="id" /><webwork:param name="'priority'" value="name"/></webwork:url>"><img
								align="top" border="0" src="<webwork:url value="icon" />" /></a>

						</webwork:else>
					</webwork:iterator>

					<webwork:if test="dashboardContext.priority != null">
						<a
							href="<webwork:url value="'sooner.action'" >
									<webwork:param name="'target'" value="id" /></webwork:url>"><img
							border="0" align="top"
							src="<webwork:url value="'/images/icon-todo-sequence-sooner.gif'" />" /></a>

						<a
							href="<webwork:url value="'later.action'" >
									<webwork:param name="'target'" value="id" /></webwork:url>"><img
							border="0" align="top"
							src="<webwork:url value="'/images/icon-todo-sequence-later.gif'" />" /></a>
					</webwork:if>

					<time:userIsPrivilegedOrOwner>
					<webwork:if test="dashboardContext.showingToDoDetail">

						<webwork:iterator value="activeInternalUsers">
							<webwork:if
								test="!user.username.equals(dashboardContext.toDoUsername)">
									[<a
									href="<webwork:url value="'dashboardtodoreassign.action'" >
										<webwork:param name="'target'" value="id" /><webwork:param name="'username'" value="user.username"/></webwork:url>"><webwork:property
									value="user.username" /></a>]
								</webwork:if>
						</webwork:iterator>
					</webwork:if>
				</time:userIsPrivilegedOrOwner>
					
				<br/>	
				</webwork:if> 
				

					
					
					
			<div style="display:inline;"
				 onMouseOver="javascript:handleToDoDetail('<webwork:property value="id" />');"
			     onMouseOut="javascript:collapseById('todoFullDiv<webwork:property value="id" />');">		

				<div id="todoFullDiv<webwork:property value="id" />"
					class="<webwork:property value="#displayClass"/>DetailDiv"
					style="display:none;"
					onMouseOut="javascript:collapseById('todoFullDiv<webwork:property value="id" />');"
					>
					<webwork:property value="sequence" /> 
					<ajax:wellformed><webwork:property value="detailFull" /></ajax:wellformed>
					<a href="<webwork:url value="'/tododetail.action'">					
							<webwork:param name="'target'" value="id" />
							</webwork:url>">(detail)</a> 
					
					
					<br/>
					<webwork:iterator value="toDoPriorities">
						<webwork:if test="priority != null && priority.equals(name)">
							<img align="top" border="0" src="<webwork:url value="iconOn" />" />
						</webwork:if>
						<webwork:else>

							<a
								href="<webwork:url value="'dashboardtodopriority.action'" >
										<webwork:param name="'target'" value="id" /><webwork:param name="'priority'" value="name"/></webwork:url>"><img
								align="top" border="0" src="<webwork:url value="icon" />" /></a>

						</webwork:else>
					</webwork:iterator>

					<webwork:if test="dashboardContext.priority != null">
						<a
							href="<webwork:url value="'sooner.action'" >
									<webwork:param name="'target'" value="id" /></webwork:url>"><img
							border="0" align="top"
							src="<webwork:url value="'/images/icon-todo-sequence-sooner.gif'" />" /></a>

						<a
							href="<webwork:url value="'later.action'" >
									<webwork:param name="'target'" value="id" /></webwork:url>"><img
							border="0" align="top"
							src="<webwork:url value="'/images/icon-todo-sequence-later.gif'" />" /></a>
					</webwork:if>

					<time:userIsPrivilegedOrOwner>
					

						<webwork:iterator value="activeInternalUsers">
							<webwork:if
								test="!user.username.equals(dashboardContext.toDoUsername)">
									[<a
									href="<webwork:url value="'dashboardtodoreassign.action'" >
										<webwork:param name="'target'" value="id" /><webwork:param name="'username'" value="user.username"/></webwork:url>"><webwork:property
									value="user.username" /></a>]
								</webwork:if>
						</webwork:iterator>
					
				</time:userIsPrivilegedOrOwner>
					
					
				</div>

					
				<div id="todoShortDiv<webwork:property value="id" />"
					style="display:block;">
					<webwork:property value="sequence" />
					 <ajax:wellformed><webwork:property value="detailShort" /></ajax:wellformed>
					
				</div>
			</div>
				
				
				
				</td>

				<%--
				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="60"><webwork:property value="friendlyDeadline" /></td>
				--%>
				<td class="<webwork:property value="#displayClass"/>" valign="top"
					align="right" nowrap="true" width="25">
					
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

					</td>
			</tr>
		</webwork:iterator>
		<webwork:if test="dashboardContext.showingToDoDetail">
			<tr>
				<td></td>
				<td colspan="5">Tag ToDos As:<webwork:textfield
					cssClass="'dashboard'" theme="'simple'" label="''" name="'newTag'"
					size="20" /> 
					
					
					<webwork:submit theme="'simple'" name="'submit'"
					value="'Add Tag'" /> 
					
					<webwork:submit theme="'simple'"
					name="'submit'" value="'Replace Tag'" /></td>


			</tr>
		</webwork:if>

	</table>




<time:userIsPrivilegedOrOwner>

	<br />

	<table width="100%" border="0" cellpadding="1" cellspacing="0">

		<tr>
			<th colspan="6">Delegated ToDos</th>
		</tr>
		<tr>

			
		
			
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'projectId'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Project</a>


			</th>
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'tag'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Tags</a>

			</th>
			<th width="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'assignedToUser'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Assignee</a>

			</th>
			<th width="*">Item</th>
			<th wdith="60"> <a
				class="dashboard_header_sort"
				href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'dashboardContext.todoFilter.orderBy'" value="'deadlineTimestamp'" />
						<webwork:param name="'dashboardContext.todoFilter.sortDirection'" value="dashboardContext.todoFilter.inverseSortDirection" /></webwork:url>">Deadline</a>

			</th>
			<th width="20"></th>

			
		</tr>
		<webwork:iterator value="delegatedToDos" status="toDoRow">
			<webwork:if test="#toDoRow.modulus(3) == 1">
				<webwork:set name="displayClass" value="'dashboardToDo_Alternate'" />
			</webwork:if>
			<webwork:else>
				<webwork:set name="displayClass" value="'dashboardToDo_Main'" />
			</webwork:else>
			<tr>
				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="80"><webwork:property value="projectName" /></td>
				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="80"><webwork:property value="tagFriendly" /></td>

				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="80"><webwork:property value="assignedToUser" /></td>



				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="*"><a
					href="<webwork:url value="'/tododetail.action'" >
						<webwork:param name="'target'" value="id" /></webwork:url>">
				<ajax:wellformed><webwork:property value="detailShort" /></ajax:wellformed> </a></td>

				<td class="<webwork:property value="#displayClass"/>" valign="top"
					width="60"><webwork:property value="friendlyDeadline" /></td>
				<td class="<webwork:property value="#displayClass"/>" valign="top"
					align="right" nowrap="true" width="25">
					
					
			<a onClick="sendToDoComplete('<webwork:property value="id"/>')">
				<img src="<webwork:url value="'/images/icon-todo-complete.gif'" />"
					border="0" align="middle" /></a> 
					
			<a onClick="sendToDoToActivity('<webwork:property value="id"/>')">
				<img src="<webwork:url value="'/images/icon-todo-start.gif'" />"
					border="0" align="middle" /></a>
					
					
					

					</td>
			</tr>
		</webwork:iterator>
	</table>
</time:userIsPrivilegedOrOwner>