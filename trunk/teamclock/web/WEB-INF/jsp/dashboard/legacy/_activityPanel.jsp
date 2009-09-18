<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>


<div id="dashboardActivity">
<table>
	<tr>
		<time:userIsPrivileged>
			<td class="dashboard_header" ><div><webwork:form method="'post'" theme="'simple'" action="'homeFilter.action'">
				<webwork:select theme="'simple'" label="'User'"
					name="'activityUsername'" value="dashboardContext.activityUsername"
					list="activeUsers" listKey="user.username"
					listValue="user.username" emptyOption="false"
					onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />
			</webwork:form></div></td>
		</time:userIsPrivileged>
		<td>			
			<a class="dashboard_header" href="#" onclick="javascript:getUpdateActivity();"><img src="<webwork:url value="'/images/refresh-icon.gif'" />" border="0" alt="refresh"/></a>					
		</td>
		
		<td align="center"><webwork:form theme="'simple'" name="'activity'"
			action="'/dashboardActivityAdd.action'" method="'post'">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td class="dashboard"><a
						href="help.action?file=dashboard/add-a-todo.jsp&amp;back=home.action"><img
						src="<webwork:url value="'/images/icon-help.gif'" />" border="0"
						align="middle" /></a>:</td>

					<td><input AUTOCOMPLETE="off" class="dashboard" name="detail"
						onkeyup="activityAutoComplete(event)" type="text" size="105"
						tabindex="1" value="" id="activityDetail"></td>
					<td align="left"><webwork:submit theme="'simple'" name="'submit'"
						value="'Add'" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">
					<div id="activityAutoCompleteDiv"></div>
					</td>
				</tr>
			</table>

		</webwork:form></td>
	</tr>
</table>

	<div id="activityAJAXDiv" style="display:block; "></div>
</div>