<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
 

<html>
	<head>
		<title>User Settings</title>
	</head>
	<body>
		<h2>User Settings </h2>
		
		<p><a href="<webwork:url value="'/passwordModify.action'" />">Change Your Password</a></p>
		
		
		
		
		<webwork:form action="'userSettingsModify.action'" method="'post'">

		<webwork:select label="'Default login destination?'" 
			name="'userSettingVO.loginDestination'" 
			value="userSettingVO.loginDestination"
			list="{'Standard', 'Calendar', 'TimeClock'}" emptyOption="true" />
			
	<time:userIsPrivileged>
		<webwork:select label="'Display timeclock on dashboard?'" 
				name="'userSettingVO.showTimeClockStatus'" 
				value="userSettingVO.showTimeClockStatus"
				list="userSettingVO.standardShowTimeClockStatusOptions" emptyOption="true" />
	</time:userIsPrivileged>
                
        <%--
		<webwork:select label="'Notify me of upcoming calender items via e-mail?'" 
			name="'userSettingVO.calendarNotificationOnOrOff'" 
			value="userSettingVO.calendarNotificationOnOrOff"
			list="{'Notify', 'Do Not Notify'}" emptyOption="true" />
		

		<webwork:textfield label="'E-Mail address to send notification(Leave blank to use default address)'"
		 name="'userSettingVO.calendarNotificationEmailAddress'" 
		 value="userSettingVO.calendarNotificationEmailAddress" />
		 --%>
	
	
		<webwork:select label="'Calendar Default Dashboard View'" 
			name="'userSettingVO.calendarDefaultDashboardView'" 
			value="userSettingVO.calendarDefaultDashboardView" 			
			list="userSettingVO.standardCalendarDefaultDashboardViewOptions" emptyOption="true" />
			
		<webwork:select  label="'Calendar Default Tab View'" 
			name="'userSettingVO.calendarDefaultTabView'" 
			value="userSettingVO.calendarDefaultTabView" 			
			list="userSettingVO.standardCalendarDefaultTabViewOptions" emptyOption="true" />

		
		<webwork:select  label="'User Time Zone'" 
			name="'userSettingVO.userTimeZone'" 
			value="userSettingVO.userTimeZone" 			
			list="userSettingVO.standardTimeZoneOptions" listKey="id" listValue="displayNameFull" emptyOption="true" />
	
		
		
		<webwork:submit value="'Save'" name="'userSettingSubmit'" />
	    <webwork:submit value="'Cancel'" name="'userSettingCancel'" />
		
		</webwork:form>
		
		<p>User Token:
		<webwork:if test="userSettingVO.token == null">
			<a href="<webwork:url value="'/userGenerateToken.action'"/>">Generate</a>
		</webwork:if>
		<webwork:else>
			<br/>
			<webwork:property value="userSettingVO.token" />
			<br/>
			<a href="<webwork:url value="'/userRevokeToken.action'"/>">Revoke</a></p>
			
			<%--
			<p>ToDo RSS Feed URL: <webwork:url value="'/todoRSSv2.rss'"><webwork:param name="'token'" value="userSettingVO.token" /></webwork:url> 
			--%>
		</webwork:else>
		
		</p>
		
	</body>
</html>