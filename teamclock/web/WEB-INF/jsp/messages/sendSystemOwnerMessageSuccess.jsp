<%@ taglib uri="webwork" prefix="webwork"%>

<html>
<head>
<title>Messages Sent</title>
</head>
<body>
Messages Sent.
<p />

<webwork:if test="undeliverable.size() >0">
		Undeliverable messages:<br />
	<webwork:iterator value="undeliverable">

		<webwork:property value="name" />
		<br />

	</webwork:iterator>
</webwork:if>

</body>
</html>
