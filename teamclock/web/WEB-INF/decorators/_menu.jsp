
<%@ taglib uri="/WEB-INF/tlds/fstxtime.tld" prefix="time" %>

<%@ taglib uri="webwork" prefix="webwork" %>

<time:invalidSession>
<ul id="mainMenu">
				<li class="activeMenu" ><a href="<webwork:url value="'login.action'"/>">Sign In</a></li>
</ul>

	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="submenu">
		<tr>
<td><img src="<webwork:url value="'/images/submenuspacer-23.gif'"/>"/></td>
	
			<td width="100%" class="border_menu" align="center">&nbsp;</td>
		</tr>
	</table>

</time:invalidSession>

<time:validSession>

<div id="mainMenuDiv">
<ul id="mainMenu">
			<time:userIsNotTimeclockOnly>
				<li <webwork:if test="sessionContext.menuSection.name.equals(\"HOME\")">class="activeMenu"</webwork:if>><a  accesskey="H" href="<webwork:url value="'home.action'"/>">Home</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"TODO\")">class="activeMenu"</webwork:if>><a  accesskey="O" class="mainmenu" href="<webwork:url value="'todolist.action'"/>">To-do's</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"ACTIVITY\")">class="activeMenu"</webwork:if>><a  accesskey="A" class="mainmenu" href="<webwork:url value="'activitylist.action'"/>">Activities</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"CALENDAR\")">class="activeMenu"</webwork:if>><a  accesskey="C" class="mainmenu" href="<webwork:url value="'calendarDefault.action'"/>">Calendar</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"CUSTOMERS\")">class="activeMenu"</webwork:if>><a  accesskey="U" class="mainmenu" href="<webwork:url value="'customerlist.action'"/>">Customers</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"CONTACTS\")">class="activeMenu"</webwork:if>><a  accesskey="U" class="mainmenu" href="<webwork:url value="'contactlistv2.action'"/>">Contacts</a></li>
			</time:userIsNotTimeclockOnly>
			
			<li <webwork:if test="sessionContext.menuSection.name.equals(\"TIMECLOCK\")">class="activeMenu"</webwork:if>><a  accesskey="T" href="<webwork:url value="'timeclockHome.action'"/>">Time Clock</a></li>
			
			<time:userIsPrivilegedOrOwner>
				<li <webwork:if test="sessionContext.menuSection.name.equals(\"ADMIN\")">class="activeMenu"</webwork:if>><a  accesskey="N" href="<webwork:url value="'timeclockAdmin.action'"/>">Admin</a></li>
			</time:userIsPrivilegedOrOwner>
			
			<%--
			<li <webwork:if test="sessionContext.menuSection.name.equals(\"HELP\")">class="activeMenu"</webwork:if>><a  href="<webwork:url value="'help.action'"/>">Help</a></li> --%>
</ul>
</div>
	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="submenu">
		<tr>
			<td align="left" width="1%"><img src="<webwork:url value="'/images/submenuspacer-23.gif'"/>"/></td>
			<td align="left">
			<webwork:if test="sessionContext.fadeMessagePresent">
				<div id="fadeMessage"><webwork:property value="sessionContext.fadeMessage" /></div>
				<script>
					dojo.require("dojo.lfx.*");
					dojo.byId("fadeMessage").style.opacity = 100;
					var a = dojo.lfx.html.fadeOut(dojo.byId("fadeMessage"),10000);
					a.play();
				</script>
			</webwork:if>
			</td>
			<td  align="right">
					<webwork:if test="sessionContext.menuSection.name.equals(\"HOME\")">
						<div id="timeClockStatusDiv"></div>
					</webwork:if>
				

					<webwork:if test="sessionContext.menuSection.name.equals(\"PASSWORD\")">
						<a href="<webwork:url value="'/passwordModify.action'" />">Change Password</a>
					</webwork:if>
					<webwork:if test="sessionContext.menuSection.name.equals(\"TODO\")">
						<a href="<webwork:url value="'/todomodify.action'" />">Add a To Do</a>
					</webwork:if>
					<webwork:if test="sessionContext.menuSection.name.equals(\"CALENDAR\")">
						<a href="<webwork:url value="'calendarmodify.action'" />">Add a Calendar Item</a> |
						<a href="<webwork:url value="'/calendarDaily.action'"/>">Daily</a> |
						<a href="<webwork:url value="'/calendarWeekly.action'"/>">Weekly</a> |
						<a href="<webwork:url value="'/calendarMonthly.action'"/>">Monthly</a> 
					</webwork:if>
					<webwork:if test="sessionContext.menuSection.name.equals(\"ACTIVITY\")">
						<a href="<webwork:url value="'activityrecordmodify.action'" />">Add an Activity</a> |
						<a href="<webwork:url value="'activitylist.action?viewPreviousDay=true'" />">Previous Day</a> |
						<a href="<webwork:url value="'activitylist.action?viewNextDay=true'" />">Next Day</a> |
						<a href="<webwork:url value="'activitylist.action?searchReset=true'" />">Reset Search</a> |
						<a href="<webwork:url value="'activitySummarizeByClass.action'" />">Summarize By Class</a> |
						<a href="<webwork:url value="'activitySummarizeByProject.action'" />">Summarize By Project</a>						
					</webwork:if>					
					<webwork:if test="sessionContext.menuSection.name.equals(\"TIMECLOCK\")">
						<a href="<webwork:url value="'timeclockCurrent.action'" />">Show Current Pay Period</a> |
						<a href="<webwork:url value="'timeclockPrevious.action'" />">Show Previous Pay Period</a>
						<time:userIsPrivilegedOrOwner>
							| <a href="<webwork:url value="'/timeclockAdmin.action'" />">Time Clock Admin</a>
						</time:userIsPrivilegedOrOwner>
					</webwork:if>	
					<webwork:if test="sessionContext.menuSection.name.equals(\"CUSTOMERS\")">
						<a href="<webwork:url value="'/customerlist.action'" />">Customers</a> 
					</webwork:if>					
					<webwork:if test="sessionContext.menuSection.name.equals(\"ADMIN\")">
						<a href="<webwork:url value="'/adminHome.action'" />">Admin Menu</a> |					
						<a href="<webwork:url value="'/timeclockAdmin.action'" />">Time Clock Admin</a> |
						<a href="<webwork:url value="'/tasklist.action'" />">Tasks</a> |
						<a href="<webwork:url value="'/projectlist.action'" />">Projects</a>
						<time:userIsOwner>
							|
							<a href="<webwork:url value="'/userList.action'" />">Users</a> |
							<a href="<webwork:url value="'/settingsModify.action'" />">System Settings</a>
							
						</time:userIsOwner>
						|
						<a href="<webwork:url value="'/loginHistoryList.action'" />">Login History</a>

						<webwork:if test="sessionContext.featureComplete">
							|
							<a href="<webwork:url value="'/unpostedActivityList.action'" />">Show Unposted Activity</a>
						</webwork:if>
						<webwork:if test="sessionContext.featureSuperUser">
							|
							<a href="<webwork:url value="'/superUserDashboard.action'" />">Super User Dashboard</a>
						</webwork:if>									
					</webwork:if>

&nbsp;&nbsp;
			</td>

		</tr>
	</table>		
</time:validSession>