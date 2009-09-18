<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<div id="dashboardSchedule">
					<table>
					<tr>
						<td>
							<a class="dashboard_header" href="<webwork:url value="'calendarDefault.action'" />">Schedule</a>
						</td>
						<td>			
							<a class="dashboard_header" href="<webwork:url value="'calendarmodify.action'" />">+</a>					
						</td>
						<time:userIsPrivileged>
							<td>
							<webwork:form method="'post'" action="'homeFilter.action'">
								<webwork:select theme="'simple'" label="'User'" 
									name="'calendarUsername'" value="dashboardContext.calendarUsername"
									list="activeUsers" listKey="user.username" listValue="user.username" emptyOption="false" 
									onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />							
							</webwork:form>
							</td>					
						</time:userIsPrivileged>
			
							<td>
							<webwork:form method="'post'" action="'homeFilter.action'">
								<webwork:select theme="'simple'" label="'User'" 
									name="'dashboardContext.calendarDisplayType'" value="dashboardContext.calendarDisplayType"
									list="{'Day', 'Week'}"  emptyOption="false" 
									onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />							
							</webwork:form>
							</td>	
						<td>			
							<a class="dashboard_header" href="#" onclick="javascript:getUpdateCalendar();">Refresh</a>					
						</td>
						
						
					</tr>
					</table>
					
					<div id="calendarAJAXDiv" style="display:block; "></div>		
				
				</div>