<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Auction Customer List</title>
	</head>
	<body>

	<table width="100%">
		<tr>
			<td>
				<h2>Auction Customer List</h2>
			</td>
			<td align="right">
				<p><a href="<webwork:url value="'/auctionCustomerModify.action'" />">Add a New eBay Customer</a>
			</td>
		</tr>
	</table>
		
	

<webwork:form method="'post'" action="'auctionCustomerList.action'">	

<table border="1">
	<tr>
		<td>Customer Id</td>
		<td>Name</td>
		<td>Status</td>
		<td>Return</td>
		<td>Detail</td>
		<td>Go</td>
	</tr>
	<tr>
		<td>
			<webwork:textfield theme="'simple'" name="'listEbayCustomerContext.filter.id'" 
				label="'Short Description'" value="listEbayCustomerContext.filter.id" size="'5'" maxlength="'10'" />
		
		</td>
		<td>
			<webwork:textfield theme="'simple'" name="'listEbayCustomerContext.filter.nameLike'" 
				label="'Short Description'" value="listEbayCustomerContext.filter.nameLike" size="'20'" maxlength="'20'" />
		
		</td>
		<td>
			<webwork:select theme="'simple'" label="'Item Status'" name="'listEbayCustomerContext.filter.status'" 
			value="listEbayCustomerContext.filter.status" 
			list="statusLookups" listKey="id" listValue="shortName" emptyOption="true"  />		
		</td>
		<td>
			<webwork:select theme="'simple'" label="'Complete'" name="'listEbayCustomerContext.filter.returnMaximum'" 
			value="listEbayCustomerContext.filter.returnMaximum"
			list="maximums" listKey="key" listValue="value" emptyOption="false" />	
		
		</td>
		<td>
			<webwork:checkbox theme="'simple'" name="'detail'" value="detail" fieldValue="'true'" />
		</td>
		<td>
			<webwork:submit theme="'simple'" value="'Search'" name="'submitSearch'" />	

			<webwork:submit theme="'simple'" value="'Reset'" name="'submitReset'" />	

		</td>
	</tr>
</table>

</webwork:form>	


	<table border="1" cellpadding="5">
		<tr>
			<th>Id</th>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Company</th>			
			<th>Phone</th>						
			<th>Email</th>						
			<th>Actions</th>	
			<th>Generate a Form</th>				
		</tr>
		<webwork:iterator value="customers">
			<tr>
				<td><webwork:property value="customer.id" /></td>
				<td><webwork:property value="contact.nameLast" /></td>
				<td><webwork:property value="contact.nameFirst" /></td>
				<td><webwork:property value="customer.name" /></td>
				<td><webwork:property value="contact.primaryPhone" /></td>
				<td><webwork:property value="contact.primaryEmail" />&nbsp;</td>
				<td>
					<a href="<webwork:url value="'/auctionUpdateFilter.action'" >
							<webwork:param name="'targetCustomerId'" value="customer.id" /></webwork:url>">Items</a> | 
					<a href="<webwork:url value="'/auctionCustomerModify.action'" >
							<webwork:param name="'target'" value="customer.id" /></webwork:url>">Modify</a> |
					<a target="_blank" href="<webwork:url value="'/auctionCustomerStatementPrint.action'" >
							<webwork:param name="'target'" value="customer.id" /></webwork:url>">Statement</a>
				</td>
				<td>
				<webwork:form action="'auctionCustomerFormPrint.action'">
					<webwork:hidden name="'targetCustomer'" value="customer.id" />
					<webwork:select theme="'simple'" label="'Item Status'" name="'target'" 
					value=""
					list="forms" listKey="id" listValue="name" emptyOption="true" onchange="'form.submit();'" />		
				</webwork:form>				
				</td>
			</tr>		
		</webwork:iterator>
	</table>	

	</body>
</html>