
<%@ taglib uri="/WEB-INF/tlds/authorization.tld" prefix="auth" %>

<%@ taglib uri="/WEB-INF/tlds/fstxtime.tld" prefix="time" %>

<%@ taglib uri="webwork" prefix="webwork" %>


<time:invalidSession>
	<table cellspacing="0" cellpadding="0">
		<tr>
			<td class="mainmenu" >
				&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</time:invalidSession>

<time:validSession>
	<table cellspacing="0" cellpadding="0">
		<tr>
			<time:userIsNotTimeclockOnly>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;&nbsp;<a accesskey="H" class="mainmenu" href="<webwork:url value="'home.action'"/>"><U>H</U>OME</a>&nbsp;&nbsp;
				</td>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;&nbsp;<a accesskey="O" class="mainmenu" href="<webwork:url value="'todolist.action'"/>">T<U>O</U> DO'S</a>&nbsp;&nbsp;
				</td>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;&nbsp;<a accesskey="A" class="mainmenu" href="<webwork:url value="'activitylist.action'"/>"><U>A</U>CTIVITY</a>&nbsp;&nbsp;
				</td>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">&nbsp;&nbsp;
					<a accesskey="C" class="mainmenu" href="<webwork:url value="'calendarDaily.action'"/>"><U>C</U>ALENDAR</a>&nbsp;
				</td>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;<a accesskey="D" class="mainmenu" href="<webwork:url value="'calendarDaily.action'"/>">(D)</a>&nbsp;
				</td>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;<a accesskey="W" class="mainmenu" href="<webwork:url value="'calendarWeekly.action'"/>">(W)</a>&nbsp;
				</td>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;<a accesskey="M" class="mainmenu" href="<webwork:url value="'calendarMonthly.action'"/>">(M)</a>
					&nbsp;
				</td>
			</time:userIsNotTimeclockOnly>
			<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
				&nbsp;&nbsp;<a accesskey="T" class="mainmenu" href="<webwork:url value="'timeclockHome.action'"/>"><U>T</U>IME CLOCK</a>&nbsp;&nbsp;
			</td>
			<time:userIsPrivilegedOrOwner>
				<td class="mainmenu"  onMouseOver="this.className='mainmenu_over'" onMouseOut="this.className='mainmenu'">
					&nbsp;&nbsp;<a accesskey="N" class="mainmenu" href="<webwork:url value="'adminHome.action'"/>">ADMINISTRATIO<u>N</u></a>&nbsp;&nbsp;
				</td>
			</time:userIsPrivilegedOrOwner>
		</tr>
	</table>	
</time:validSession>