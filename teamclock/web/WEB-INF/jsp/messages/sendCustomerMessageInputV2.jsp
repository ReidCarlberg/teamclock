<%@ taglib uri="webwork" prefix="webwork"%>

<html>
<head>
<title>Send Message</title>

</head>
<body>

<h2>Send Message</h2>

<webwork:form method="'post'" 
	action="'/sendCustomerMessage.action'"
	name="'sendMessage'">
	
<webwork:select label="'Message'"
	emptyOption="'false'"
	name="'messageId'"
	list="messages"
	listKey="id" listValue="name" multiple="false" />

<webwork:select 
	label="'Status Filter'"
	name="'customerStatusLookupId'"
	list="customerStatusCodes"
	listKey="id"
	listValue="fullName" />	
	
<webwork:submit  name="'updateList'"
				value="'Update List'" />
				
<webwork:submit  name="'submitSend'"
				value="'Preview Message'" />


</webwork:form>

<webwork:if test="sendMessageContext.message != null">
<h3>Message</h3>

<hr/>

<pre>
Subject: <webwork:property value="sendMessageContext.message.subject" />

<webwork:property value="sendMessageContext.message.message" />

</pre>

</h3>

</webwork:if>

<webwork:if test="sendMessageContext.recipients != null && sendMessageContext.recipients.size > 0">
<h3>Selected Recipients</h3>

<table>
	<tr>	
		<th>Contact First Name</th>
		<th>Contact Last Name</th>
		<th>Company Name</th>
		<th>Email Address</th>
	</tr>
	<webwork:iterator value="sendMessageContext.recipients">
		<tr>
			<td><webwork:property value="contact.nameFirst" /></td>
			<td><webwork:property value="contact.nameLast" /></td>
			<td><webwork:property value="customer.name" /></td>
			<td><webwork:property value="contact.primaryEmail" /></td>
		</tr>
	</webwork:iterator>
</table>
</webwork:if>
</body>
</html>