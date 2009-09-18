<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Auction Setup: Commission List</title>
	</head>
	<body>
		<h2>Auction Setup: Commission List</h2>
		
		<p> <a href="<webwork:url value="'/auctionSetupCommissionModify.action'" />" >Add</a>
		
		<table>
			<tr>
				<th>Name</th>
				<th>Minimum</th>
				<th>&nbsp;</th>
			</tr>
		
			<webwork:iterator value="commissions">
				<tr>			
					<td><webwork:property value="name" /></td>
					<td><webwork:property value="minimum" /></td>
					<td><a href="<webwork:url value="'/auctionSetupCommissionModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">Modify</a></td>
				</tr>			
			</webwork:iterator>
		</table>
		

	</body>
</html>