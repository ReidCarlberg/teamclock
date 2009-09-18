<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Queued Message List</title>
	</head>
	<body>
		<h2>Unsent Queued Message List</h2>

		<table>
		
		<webwork:iterator value="messages">
			<tr>
				<td><webwork:property value="toAddress" /></td>
				<td><webwork:property value="subject" /></td>
				<td><webwork:property value="sendTime" /></td>
			
			</tr>
		
		</webwork:iterator>
		
		</table>
	</body>
</html>