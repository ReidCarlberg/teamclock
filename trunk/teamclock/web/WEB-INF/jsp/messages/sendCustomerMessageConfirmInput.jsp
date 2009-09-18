<%@ taglib uri="webwork" prefix="webwork"%>

<html>
<head>
<title>Confirm Send Message</title>

</head>
<body>

<h2>Confirm Send Message</h2>

<h3>Recipients</h3>

<p>You are about to send a message to <webwork:property value="sendMessageContext.recipientCount" /> recipients.


<h3>Message</h3>

<p>Name: <webwork:property value="sendMessageContext.message.name" />

<p>Subject: <webwork:property value="sendMessageContext.message.subject" />

<p>Message: <br/>
<webwork:property value="sendMessageContext.message.message"/>


<h3>Confirm</h3>

<p>Are you sure you want to continue?  This cannot be undone.

<webwork:form method="'post'" action="'sendCustomerMessageConfirm'">

	<webwork:submit name="'submitConfirm'" value="'Yes, send this message to all recipients.'" />

	<webwork:submit name="'cancelConfirm'" value="'Cancel this message.'" />
	
</webwork:form>


</body>
</html>