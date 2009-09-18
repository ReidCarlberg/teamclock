<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Auction Setup: Box List</title>
	</head>
	<body>
		<h2>Auction Setup: Box List</h2>
		
		<p> <a href="<webwork:url value="'/auctionSetupBoxModify.action'" />" >Add</a>
		
		<table>
			<tr>
				<th>Name</th>
				<th>Length</th>
				<th>Width</th>
				<th>Height</th>
				<th>Weight</th>
				<th>Handling</th>
				<th>&nbsp;</th>
			</tr>
		
			<webwork:iterator value="boxes">
				<tr>			
					<td><webwork:property value="name" /></td>
					<td><webwork:property value="length" /></td>
					<td><webwork:property value="width" /></td>
					<td><webwork:property value="height" /></td>
					<td><webwork:property value="weight" /></td>
					<td><webwork:property value="defaultHandlingCost" /></td>
					<td><a href="<webwork:url value="'/auctionSetupBoxModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">Modify</a></td>
				</tr>			
			</webwork:iterator>
		</table>
		

	</body>
</html>