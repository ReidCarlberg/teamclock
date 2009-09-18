<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Auction Customer List</title>
	</head>
	<body>




	<table border="1" cellpadding="5">
		<tr>
			<th>Id</th>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Company (or combined name)</th>			
			<th>Street 1</th>
			<th>Street 2</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Phone</th>						
			<th>Email</th>					
		</tr>
		<webwork:iterator value="customers">
			<tr>
				<td><webwork:property value="customer.id" /></td>
				<td><webwork:property value="contact.nameLast" /></td>
				<td><webwork:property value="contact.nameFirst" /></td>
				<td><webwork:property value="customer.name" /></td>
				<td><webwork:property value="customer.street1" /></td>
				<td><webwork:property value="customer.street2" />&nbsp;</td>
				<td><webwork:property value="customer.city" /></td>
				<td><webwork:property value="customer.state" /></td>
				<td><webwork:property value="customer.zip" /></td>
				<td><webwork:property value="contact.primaryPhone" /></td>
				<td><webwork:property value="contact.primaryEmail" />&nbsp;</td>

			</tr>		
		</webwork:iterator>
	</table>	

	</body>
</html>