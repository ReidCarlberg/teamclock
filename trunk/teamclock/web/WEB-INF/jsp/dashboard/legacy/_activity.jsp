<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>

<table border="0" cellpadding="0" cellspacing="2" width="100%">
	<tr>
		<th>Project</th>
		<th>Start</th>
		<th>Stop</th>
		<th> </th>
		<th>Comments</th>
		
		
	</tr>
	<webwork:iterator value="myActivity">
		<tr>					
			<td>
				<a href="<webwork:url value="'activityrecordmodify.action'" >
					<webwork:param name="'target'" value="id" />
					</webwork:url>"><webwork:property value="projectShort" /></a>
				
			</td>

			<td><webwork:property value="startSimple" /></td>
			<td><webwork:property value="stopSimple" /> </td>

			<td  nowrap="true">
	
  
				<webwork:if test="activity.stop == null">
					<a onClick="dashboardActivityStop('<webwork:property value="id"/>')">
					<img src="<webwork:url value="'/images/icon-todo-stop.gif'" />" border="0" align="middle" /></a>
				</webwork:if>
				<webwork:else>
					<a onClick="dashboardActivityContinue('<webwork:property value="id"/>')">
					<img src="<webwork:url value="'/images/icon-todo-start.gif'" />" border="0" align="middle" /></a>				
				</webwork:else>
				
  

				
			</td>	
			<td><ajax:wellformed><webwork:property value="comments" /></ajax:wellformed></td>
							
		</tr>
	</webwork:iterator>
	

</table>


<p />Total: <webwork:property value="myActivity.formattedTotal" />