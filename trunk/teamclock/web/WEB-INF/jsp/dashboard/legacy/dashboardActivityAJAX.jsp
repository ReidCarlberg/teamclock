<?xml version="1.0" encoding="ISO-8859-1"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<%@ taglib uri="wellformedAJAX" prefix="ajax" %>


<%
response.setHeader("Content-Type","text/xml; charset=iso-8859-1"); 
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<ajax-response>
	<response type="element" id="activityAJAXDiv">
	
<%--
					<table>
					<tr>
						<td>
							<a class="dashboard_header" href="<webwork:url value="'activitylist.action'" />">Activity</a>
						</td>
						<td>			
							<a class="dashboard_header" href="<webwork:url value="'activityrecordmodify.action'" />">+</a>					
						</td>
						<time:userIsPrivileged>
							<td>
							<webwork:form method="'post'" action="'homeFilter.action'">
								<webwork:select theme="'simple'" label="'User'" 
									name="'activityUsername'" value="dashboardContext.activityUsername"
									list="activeUsers" listKey="user.username" listValue="user.username" emptyOption="false" 
									onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />							
							</webwork:form>
							</td>					
						</time:userIsPrivileged>
					</tr>
					</table>

				<h3 align="center" class="dashboard_header"></h3>
				--%>
				
			<webwork:include value="'_activity.jsp'" />
		</response>
</ajax-response>
		