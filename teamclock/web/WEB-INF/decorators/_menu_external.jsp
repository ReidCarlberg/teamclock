
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

<ul id="mainMenu">

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"HOME\")">class="activeMenu"</webwork:if>><a  accesskey="H" href="<webwork:url value="'external.action'"/>">Home</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"TODO\")">class="activeMenu"</webwork:if>><a  accesskey="O" class="mainmenu" href="<webwork:url value="'extTodos.action'"/>">To-do's</a></li>

				<li <webwork:if test="sessionContext.menuSection.name.equals(\"ACTIVITY\")">class="activeMenu"</webwork:if>><a  accesskey="A" class="mainmenu" href="<webwork:url value="'extAccount.action'"/>">Activities</a></li>


</ul>

	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="submenu">
		<tr>
			<td align="left" width="1%"><img src="<webwork:url value="'/images/submenuspacer-23.gif'"/>"/></td>			<td width="100%" class="border_menu" align="center"></td>

		</tr>
	</table>	
</time:validSession>