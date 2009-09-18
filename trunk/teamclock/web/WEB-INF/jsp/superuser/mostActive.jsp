<%@ taglib uri="webwork" prefix="webwork" %>





<html>
	<head>
		<title>Most Active</title>
	</head>
	<body>
		<h1>Most Active</h1>

<div id="listTable" >
		<table >
		<tr>
			<th>Name</th>
			<th>Contact Name</th>
			<th>Login Count</th>
			<th>Actions</th>
		</tr>
		<webwork:iterator value="active">
			<tr>
				<td><webwork:property value="owner.name" /></td>
				<td>
					<a href="mailto:<webwork:property value="owner.contactEmail" />"><webwork:property value="owner.contactName" /></a>
				</td>
				<td><webwork:property value="metric.metricValue" /></td>
				<td>
					&nbsp;
				
				</td>
			</tr>		
		</webwork:iterator>
		</table>
</div>		

	</body>
</html>