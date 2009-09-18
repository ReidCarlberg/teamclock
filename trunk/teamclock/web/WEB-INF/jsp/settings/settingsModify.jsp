<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Settings Modify</title>
	</head>
	<body>
	
		<h2>Account Information</h2>
		
		<p>Account Type: <webwork:property value="sessionContext.systemOwner.account" />. 
		
		Expires: <webwork:property value="sessionContext.systemOwner.expirationDate" />.

		Update your <a href="<webwork:url value="'updateAccount.action'" />">account information</a>.</p>



		<h2>System Settings</h2>
		
		<webwork:form method="'post'" action="'settingsModify.action'">
		
		<table>
		<tr>
			<td><strong>General</strong></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>System Name:</td>
			<td><webwork:textfield theme="'simple'" name="'settings.systemName'" value="settings.systemName" size="'50'" /></td>
		</tr>


		
		<tr>
			<td>Your time zone:</td>
		<td>
					<webwork:select theme="'simple'" label="'Your Time Zone'" 
			name="'settings.systemTimeZone'" listKey="id" listValue="displayNameFull"
			list="timeZones" emptyOption="true" />
			
			</td>
		</tr>		
		<tr>
	



		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td><strong>Time Clock</strong></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>Allow login from time clock page:</td>
			<td>
			<webwork:select theme="'simple'" name="'timeclockLogin'" value="timeclockLogin"
				list="trueFalse" listKey="key" listValue="value" emptyOption="true"  />    
			</td>
		</tr>
		
		<tr>
			<td>Round time clock entries using:</td>
			<td>
			<webwork:select  theme="'simple'" name="'settings.timeClockRounderType'" value="settings.timeClockRounderType"
				list="rounderTypes" listKey="name" listValue="description" emptyOption="true"  />
			
			</td>
		</tr>

		<tr>
			<td>Recent Pay Period Start Date:</td>
			<td>
				<webwork:textfield theme="'simple'" name="'payPeriodRefDate'" value="payPeriodRefDate" />
			</td>
		</tr>

		<tr>
			<td>Pay Period Type:</td>
			<td>
			<webwork:select  theme="'simple'" name="'settings.timeClockPayPeriodType'" value="settings.timeClockPayPeriodType"
				list="payPeriodTypes" listKey="name" listValue="name" emptyOption="true"  />
			

			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td><strong>Activity</strong></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>Round activity entries' time using:</td>
			<td>
			<webwork:select  theme="'simple'" name="'settings.activityRounderType'" value="settings.activityRounderType"
				list="rounderTypes" listKey="name" listValue="description" emptyOption="true"  />			
			</td>
		</tr>
		
		<tr>
			<td>Default project:</td>
			<td>
			<webwork:select theme="'simple'" name="'settings.activityDefaultProject'" value="settings.activityDefaultProject"
				list="projects" listKey="id" listValue="shortName" emptyOption="true"  />
			</td>
		</tr>

		<tr>
			<td>Default task:</td>
			<td>
			<webwork:select theme="'simple'" name="'settings.activityDefaultTask'" value="settings.activityDefaultTask"
				list="tasks" listKey="id" listValue="nameShort" emptyOption="true"  />
			</td>
		</tr>
			
		<tr>
			<td>Activity Posted Through Date:</td>
			<td>
				<webwork:textfield theme="'simple'" name="'activityPostedThrough'" value="activityPostedThrough" />
			</td>
		</tr>		
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><strong>To Do</strong></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Default project:</td>
				<td>
				<webwork:select theme="'simple'" name="'settings.todoDefaultProject'" value="settings.todoDefaultProject"
					list="projects" listKey="id" listValue="shortName" emptyOption="true"  />
				</td>
			</tr>	
			<tr>
				<td>Default entered by:</td>
				<td>
				<webwork:select theme="'simple'" name="'settings.todoDefaultEnteredBy'" value="settings.todoDefaultAssignedTo"
					list="users" listKey="key" listValue="value" emptyOption="true"  />
				</td>
			</tr>	
			<tr>
				<td>Default assigned to:</td>
				<td>
				<webwork:select theme="'simple'" name="'settings.todoDefaultAssignedTo'" value="settings.todoDefaultAssignedTo"
					list="users" listKey="key" listValue="value" emptyOption="true"  />
				</td>
			</tr>	
			
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>						
			<tr>
				<td><strong>Calendar</strong></td>
				<td>&nbsp;</td>
			</tr>
	
			<tr>
				<td>Day Starts (hour):</td>
				<td>
	
						
				<webwork:select theme="'simple'" name="'stringStart'" value="stringStart"
					list="startTimes" listKey="key" listValue="value" emptyOption="true"  />
	
						
				</td>
			</tr>
			
			<tr>
				<td>Day Ends (hour):</td>
				<td>
				<webwork:select theme="'simple'" name="'stringEnd'" value="stringEnd"
					list="endTimes" listKey="key" listValue="value" emptyOption="true"  />
					
				
				</td>
			</tr>

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><h2>Advanced</h2></td>
			<td>&nbsp;</td>
		</tr>
		
										
															

		<tr>
			<td><strong>SMTP and Mail From Settings</strong></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>Mail From Name</td>
			<td>
						<webwork:textfield theme="'simple'" name="'settings.mailFromName'" value="settings.mailFromName" size="'50'" maxlength="'50'" />
			</td>
		</tr>

		<tr>
			<td>Mail From Address</td>
			<td>
						<webwork:textfield theme="'simple'" name="'settings.mailFromAddress'" value="settings.mailFromAddress" size="'50'" maxlength="'50'" />
			</td>
		</tr>

		<tr>
			<td>Host</td>
			<td>
						<webwork:textfield theme="'simple'" name="'settings.smtpHost'" value="settings.smtpHost" size="'50'" maxlength="'50'" />
			</td>
		</tr>

		<tr>
			<td>Port (Don't know? Try 25)</td>
			<td>
						<webwork:textfield theme="'simple'" name="'settings.smtpPort'" value="settings.smtpPort" size="'10'" maxlength="'10'" />
			</td>
		</tr>

		<tr>
			<td>Username</td>
			<td>
						<webwork:textfield theme="'simple'" name="'settings.smtpUsername'" value="settings.smtpUsername" size="'20'" maxlength="'20'" />
			</td>
		</tr>

		<tr>
			<td>Password</td>
			<td>
						<webwork:textfield theme="'simple'" name="'settings.smtpPassword'" value="settings.smtpPassword" size="'20'" maxlength="'20'" />
			</td>
		</tr>
		<tr>
			<td colspan="2">We assume authentication is required.</td>
		</tr>


		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td><strong>API Access</strong></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>Allow system access via token:</td>
			<td>
			<webwork:select theme="'simple'" name="'stringTokenAccess'" value="stringTokenAccess"
				list="trueFalse" listKey="key" listValue="value" emptyOption="true"  /> 
			</td>
		</tr>
		
		<tr>
			<td>API Token (
				<webwork:if test="token == null" >
					<a href="<webwork:url value="'/settingsGenerateToken.action'"/>">Generate</a>
				</webwork:if>
				<webwork:else>
					<a href="<webwork:url value="'/settingsRevokeToken.action'"/>">Revoke</a>
				</webwork:else>	
				)			
			:</td>
			<td>
				<textarea rows="2" cols="50"><webwork:property value="token" />
				</textarea>
				</span>
			</td>
		</tr>		


		
		<tr>
			<td colspan="2" align="right">
				<webwork:submit theme="'simple'" name="'cancelSettings'" value="'Cancel'" />
				<webwork:submit theme="'simple'" name="'submitSettings'" value="'Save Changes'" />			
			</td>
		</tr>


				
		</table>
		
		
		
		
		</webwork:form>
		
				<p><strong>Your direct login link:</strong><br>
			<pre>
http://my.teamclock.com<webwork:url value="'/login.action'" ><webwork:param name="'target'" value="sessionContext.systemOwner.key" /></webwork:url>
			</pre>

				<p><strong>Your direct timeclock only link:</strong><br>
			<pre>
http://my.teamclock.com<webwork:url value="'/timeclockOnly.action'" ><webwork:param name="'target'" value="sessionContext.systemOwner.key" /></webwork:url>
			</pre>
			

		
	</body>
</html>