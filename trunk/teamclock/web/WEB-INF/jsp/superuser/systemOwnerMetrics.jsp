<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>System Owner Metrics</title>
	</head>
	<body>

		<h1>System Owner Metrics</h1>
		
		<p>Selected owner: <webwork:property value="targetOwner.name" />
		
		<table>
		
		<webwork:iterator value="metrics">
			<tr>
				<td><webwork:property value="metricType" /></td>
				<td><webwork:property value="metricValue" /></td>
			</tr>
		
		</webwork:iterator>
		
		
		</table>		
			

		
	</body>
</html>