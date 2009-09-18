<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Balance & Transactions</title>
	</head>
	<body>
		<h1>Balance & Transactions - <webwork:property value="sessionContext.externalUserSessionContext.activeCustomer.name" /></h1>
		
<p>Balance: <webwork:property value="currentBalance" />

<div id="listTable">
		
<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td>&nbsp;</td>
		<td valign="top">Date</td>
		<td valign="top">Type</td>
		<td valign="top">Amount</td>
		<td valign="top">Balance</td>
		<td valign="top">Comments</td>

	</tr>
	<webwork:set name="counter" value="0" />
	<webwork:iterator value="transactions" >
		<webwork:set name="counter" value="#counter + 1" />
		<webwork:if test="#counter <= 50">
		<tr>	
			<td><webwork:property value="#counter" /></td>
			<td valign="top"><webwork:property value="date"/></td>
			<td valign="top"><webwork:property value="type"/></td>
			<td valign="top"><webwork:property value="amount"/></td>
			<td valign="top"><webwork:property value="decoratedBalance"/></td>
			<td valign="top"><webwork:property value="comments"/></td>
		</tr>		
		</webwork:if>		
	</webwork:iterator>


</table>

</div>

	</body>
</html>