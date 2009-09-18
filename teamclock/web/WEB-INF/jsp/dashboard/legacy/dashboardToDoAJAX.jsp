<?xml version="1.0" encoding="ISO-8859-1"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<%
response.setHeader("Content-Type","text/xml; charset=iso-8859-1"); 
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<ajax-response>
	<response type="element" id="todoAJAXDiv">
	<%--
				<h3 align="center" class="dashboard_header">
					
					<table>
					<tr>
					<td>
					<a class="dashboard_header" href="<webwork:url value="'todolist.action'" />">To Do List</a> 
					</td>
					<td>			
					<a class="dashboard_header" href="<webwork:url value="'/todomodify.action'" />">+</a>
					</td>
	
					<td>
					<webwork:iterator value="toDoPriorities">
						<webwork:if test="dashboardContext.priority != null && dashboardContext.priority.equals(name)">
							<img align="top" border="0" src="<webwork:url value="iconOn" />" />
						</webwork:if>
						<webwork:else>
							<a class="dashboard_header" href="<webwork:url value="'homeFilter.action'" >
								<webwork:param name="'priority'" value="name"/></webwork:url>"><img align="top" border="0" src="<webwork:url value="icon" />"/></a>							
						</webwork:else>
					</webwork:iterator>

					<a class="dashboard_header" href="<webwork:url value="'homeFilter.action'" >
						<webwork:param name="'priority'" value="'all'"/></webwork:url>"><img align="top" border="0" src="<webwork:url value="'images/icon-todo-pall.gif'" />"/></a> 
					<a class="dashboard_header" href="<webwork:url value="'resequence.action'" />">Resequence</a>
					</td>
						<td>
							<a class="dashboard_header" href="<webwork:url value="'homeFilter.action'" ><webwork:param name="'detail'" value="'x'"/></webwork:url>"><webwork:if test="dashboardContext.showingToDoDetail">Detail Off</webwork:if><webwork:else>Detail On</webwork:else></a>						
						</td>
						<time:userIsPrivileged>
							<td>
							<webwork:form method="'post'" action="'homeFilter.action'">
								<webwork:select theme="'simple'" label="'User'" 
									name="'toDoUsername'" value="dashboardContext.toDoUsername"
									list="activeUsers" listKey="user.username" listValue="user.username" emptyOption="false" 
									onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />							
							</webwork:form>
							</td>					
						</time:userIsPrivileged>
						<td>
							<webwork:form method="'post'" action="'homeFilter.action'">
								<webwork:select theme="'simple'" label="'Project'" 
									name="'toDoProjectFilter'" value="dashboardContext.toDoProjectFilter"
									list="projects" listKey="id" listValue="shortName" emptyOption="false" 
									onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />							
							</webwork:form>
						</td>	
					</tr>
					</table>
					

				</h3>					
				--%>
				
				<webwork:include value="'_todos.jsp'" />
	
	
		</response>
</ajax-response>
		