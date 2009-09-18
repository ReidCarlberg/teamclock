<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Time Clock Modify</title></head>
<body>





<h2>Time Clock Modify</h2>

<p>User: <webwork:property value="timeclockAdminContext.user.username" />

<webwork:form action="actionName" method="'post'">

			<webwork:select label="'Event'" name="'targetTimeclockEvent.event'" value="targetTimeclockEvent.event"
				list="events" listKey="name" listValue="friendlyName" emptyOption="false"  />



				<tr>
				<td align="right" class="label">Date:</td>
				<td>
				<webwork:textfield theme="'simple'" label="'Date'" name="'newEventDate'" value="newEventDate" />
<a href="#" onclick="displayDatePicker('newEventDate');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td></tr>

<webwork:textfield label="'Hours'" name="'newEventHours'" value="newEventHours" />

<webwork:textfield label="'Minutes'" name="'newEventMinutes'" value="newEventMinutes" />

		<webwork:select label="'Meridian'" name="'newEventMeridian'"
			list="{'AM','PM'}" value="newEventMeridian" />

<webwork:textarea label="'Comments'" name="'targetTimeclockEvent.comment'" value="targetTimeclockEvent.comment" cols="40" rows="5" />

<webwork:submit name="'submitTimeclock'" value="'Save'"/>

<webwork:submit name="'cancelTimeclock'" value="'Cancel'"/>

<webwork:if test="timeclockEventModifyContext.timeclockEvent != null">
	<webwork:submit name="'deleteTimeclock'" value="'Delete'"/>
</webwork:if>

</webwork:form>

</body>
</html>