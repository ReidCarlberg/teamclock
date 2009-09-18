<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/popUpCalendar.tld" prefix="calendar" %>

<html><head></head><body>

<h2><bean:message key="authen.history.loginhistory" /></h2>

<html:form action="/authen/loginhistory" focus="fromString" >

<table>
<tr>
<td>
<bean:message key="authen.history.from" />
</td>
<td>
<bean:message key="authen.history.to" />
</td>
<td>
<bean:message key="authen.history.user" />
</td>
<td></td>
</tr>

<tr>
<td>
<html:text property="fromString" size="10" maxlength="10" />
<calendar:popUp name="calFrom"/>
</td>
<td>
<html:text property="toDateString" size="10" maxlength="10" />
<calendar:popUp name="calDateTo"/>
</td>
<td>
<html:select property="username" >
	<html:option value=""> </html:option>
	<html:options collection="users.select.all" property="username" labelProperty="username" />
</html:select>
</td>
<td>
<html:submit />
</tr>


</table>


</html:form>

<table>
	<tr>
		<th><bean:message key="authen.history.username" /></th>
		<th><bean:message key="authen.history.timestamp" /></th>
		<th><bean:message key="authen.history.event" /></th>
		<th>Location</th>
	</tr>
	<logic:iterate id="current" name="login.history" >	
		<tr>
			<td><bean:write name="current" property="username" /></td>
			<td><bean:write name="current" property="timestamp" /></td>
			<td><bean:write name="current" property="type" /></td>
			<td><bean:write name="current" property="location" /></td>
		</tr>
	</logic:iterate>
</table>

<calendar:setup name="calFrom" formId="0" input="fromString"/>
<calendar:setup name="calDateTo" formId="0" input="toDateString"/>

</body></html>


