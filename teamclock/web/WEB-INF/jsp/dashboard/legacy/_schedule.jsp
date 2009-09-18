<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>


<table border="0" cellspacing="2" cellpadding="0" width="100%">
		<webwork:iterator value="schedule.displayableBins" >
			
				<tr>
					<td width="20%" valign="top" align="right" nowrap="true">
						<webwork:property value="binLower" />
					</td>
					<td width="80%">
						<webwork:if test="displayCalendars.size == 0">

						</webwork:if>
						<webwork:iterator value="displayCalendars" >
							<a href="<webwork:url value="'calendarmodify.action'" >
							<webwork:param name="'target'" value="calendar.id" /></webwork:url>">
							
							<ajax:wellformed><webwork:property value="calendar.description" /></ajax:wellformed>
							
							</a> 
							<a href="<webwork:url value="'calendaractivity.action'" >
					<webwork:param name="'target'" value="calendar.id" /></webwork:url>"><img src="<webwork:url value="'/images/icon-todo-start.gif'" />" border="0" align="middle" /></a> 
							
							
							<br/>
						</webwork:iterator>
					</td>
				</tr>
			
		</webwork:iterator>
	</table>	
		