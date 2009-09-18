<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>Timeclock</title></head><body>

<div align="center">



<h2><webwork:text name="'login.headline'" /></h2>

<webwork:iterator value="actionErrors" >
	<p><webwork:property />	
</webwork:iterator>

<webwork:form action="action" method="'post'">

<webwork:textfield label="getText('login.prompt.username')" name="'username'" 
	value="" />

<webwork:password label="getText('login.prompt.password')" name="'password'" 
	value="" />

<tr><td colspan="2" align="center">

<webwork:submit theme="'simple'" value="getText('login.button.punchin')" name="'punchInButton'" /><br>

<webwork:submit theme="'simple'" value="getText('login.button.punchout')" name="'punchOutButton'" /><br>

<p><webwork:submit theme="'simple'" value="'Show Today'" name="'quickReportButton'" /><br>

<webwork:submit theme="'simple'" value="'Show This Pay Period'" name="'showThisPeriod'" /><br>

<webwork:submit theme="'simple'" value="'Show Last Pay Period'" name="'showLastPeriod'" />
</td></tr>
</webwork:form>

<webwork:if test="sessionContext.settings.systemTimeZone != null">
	<jsp:include page="_currentTime.jsp" flush="true" />
</webwork:if>

</body></html>