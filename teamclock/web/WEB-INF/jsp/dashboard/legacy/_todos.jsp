<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>



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


