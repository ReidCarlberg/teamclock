<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Pay Period Summary, All Users</title></head>
<body>

<table width="100%">
<tr>
<td width="50%" valign="bottom">
<h2>Pay Period Summary, All Users</h2>
</td>
<td width="50%" valign="bottom" align="right">
	<p><a href="<webwork:url value="'timeclockPayPeriodAllUsersPrint.action'">
		<webwork:param name="'targetPeriod'" value="targetPeriod"/>
		<webwork:param name="'showDetail'" value="showDetail"/>
		</webwork:url>">Printer Friendly</a> | 
		<a href="<webwork:url value="'timeclockPayPeriodAllUsersExcel.xls'">
		<webwork:param name="'targetPeriod'" value="targetPeriod"/>
		<webwork:param name="'showDetail'" value="showDetail"/>
		</webwork:url>">Excel</a>
		</p>
</td>
</tr>
</table>

<jsp:include page="_payPeriodAllUsers.jsp" flush="true" />



<p>&nbsp



</body>
</html>