<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Auction Setup: Form List</title>
	</head>
	<body>
		<h2>Auction Setup: Form List</h2>
		
		<p> <a href="<webwork:url value="'/auctionSetupFormModify.action'" />" >Add</a>
		
		<table>
			<tr>
				<th>Type</th>
				<th>Name</th>
				<th>&nbsp;</th>
			</tr>
		
			<webwork:iterator value="forms">
				<tr>			
					<td><webwork:property value="type" /></td>
					<td><webwork:property value="name" /></td>
					<td><a href="<webwork:url value="'/auctionSetupFormModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">Modify</a></td>
				</tr>			
			</webwork:iterator>
		</table>
		

	</body>
</html>