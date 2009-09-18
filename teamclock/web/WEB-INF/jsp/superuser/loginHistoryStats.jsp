<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>Super User Login Stats</title></head><body>





<h2>Super User Login Stats</h2>

<p>These times are unresolved.

	<webwork:form theme="'simple'" action="'loginHistoryStats.action'" method="'post'">
		<table >
			<tr>
				<td>
					Start: <webwork:textfield theme="'simple'" label="'Start'" name="'params.dateFrom'" value="params.dateFrom" />
				</td>
				
				<td>
					End: <webwork:textfield theme="'simple'" label="'Stop'" name="'params.dateTo'" value="params.dateTo" />
				</td>
				<td>
					<webwork:submit theme="'simple'" value="'Search'" name="'search'" />
				</td>
			</tr>
		</table>
	</webwork:form>

<p>



<table width="100%">
<tr>
	<td width="50%" valign="top">
	<div id="listTable" >
<table>
	<tr><th>Count</th><th>Username</th></tr>
	
<webwork:iterator value="byUser">
	<tr>
		<td><webwork:property value="key" /></td>
		<td><webwork:property value="value" /></td>
	<tr>
</webwork:iterator>

</table>	
</div>
	</td>
	<td width="50%" valign="top">

<div id="listTable" >
<table>

<tr><th>Count</th><th>Owner Key</th></tr>
	
<webwork:iterator value="byOwner">
	<tr>
		<td><webwork:property value="key" /></td>
		<td><webwork:property value="value" /> (<a href="<webwork:url value="'/systemOwnerModify.action'"><webwork:param name="'target'" value="value" /></webwork:url>">Modify</a>)</td>
	<tr>
</webwork:iterator>

</table>
</div>
	
	</td>
</tr>
</table>







</body>
</html>