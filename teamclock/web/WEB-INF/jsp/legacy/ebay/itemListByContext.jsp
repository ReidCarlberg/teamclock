<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Auction Items</title>
	</head>
	<body>
	<table width="100%">
		<tr>
			<td>
				<h2>Auction Items</h2>
			</td>
			<td align="right">
			
				<table>
					<tr>
						<td>
				<a href="<webwork:url value="'/auctionCustomerModify.action'" />">Add Customer</a> |
						</td>
	
				<webwork:if test="customerModifyContext.targetCustomer != null">
						<td>
					<a href="<webwork:url value="'auctionModify.action'" />" >Add Item</a> |
						</td>
						<td>
							<webwork:form action="'auctionCustomerFormPrint.action'">
							Cust Forms
								<webwork:hidden name="'targetCustomer'" value="customerModifyContext.targetCustomer.id" />
								<webwork:select theme="'simple'" label="'Item Status'" name="'target'" 
								value=""
								list="customerForms" listKey="id" listValue="name" emptyOption="true" onchange="'form.submit();'" />		
							</webwork:form>							
						</td>
						<td> | </td>
						<td>
						<a target="_blank" href="<webwork:url value="'/auctionCustomerStatementPrint.action'" >
						<webwork:param name="'target'" value="customerModifyContext.targetCustomer.id" /></webwork:url>">Statement</a> |
						</td>
				</webwork:if>
				
						<td>
				<a href="<webwork:url value="'auctionListByFilterPrint.action'"/>">Printer Friendly</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>




<p><webwork:property value="listType.name" />

<div id="listFilter" >	
<webwork:form method="'post'" action="'auctionUpdateFilter.action'">

<table border="1">
<tr>
	<th>Item Id</th>
	<th>Description</th>
	<th>Headline</th>
	<th>Customer</th>
	<th>Dates</th>
	<th>Status</th>
	<th>Open/Closed</th>
	<th>View</th>
	<th>Return</th>
</tr>
<tr>
	<td>
		<webwork:textfield theme="'simple'" name="'ebayItemListContext.filter.id'" 
			label="'Short Description'" value="ebayItemListContext.filter.id" size="'5'" maxlength="'10'" />
	
	</td>
	<td>
		<webwork:textfield theme="'simple'" name="'ebayItemListContext.filter.descriptionShortLike'" 
			label="'Short Description'" value="ebayItemListContext.filter.descriptionShortLike" size="'10'" maxlength="'20'" />
	
	</td>
	<td>
		<webwork:textfield theme="'simple'" name="'ebayItemListContext.filter.listingHeadlineLike'" 
			label="'Short Description'" value="ebayItemListContext.filter.listingHeadlineLike" size="'10'" maxlength="'20'" />	
	</td>
	<td>
		<webwork:select theme="'simple'" label="'Customer'" name="'ebayItemListContext.filter.custId'" 
		value="ebayItemListContext.filter.custId" 
		list="customers" listKey="customer.id" listValue="contact.nameLast + ', ' + contact.nameFirst" emptyOption="true"  />		
	</td>
	<td>
		<!--
		<webwork:select theme="'simple'" label="'Customer'" name="'ebayItemListContext.dateRangeType'" 
		value="ebayItemListContext.dateRangeType" 
		list="#{'added:Added','ended:Ended'}" emptyOption="false"  />		
		-->

		<webwork:textfield theme="'simple'" name="'ebayItemListContext.dateRangeStart'" 
			label="''" value="ebayItemListContext.dateRangeStart" size="'10'" maxlength="'10'" />

		<webwork:textfield theme="'simple'" name="'ebayItemListContext.dateRangeStop'" 
			label="''" value="ebayItemListContext.dateRangeStop" size="'10'" maxlength="'10'" />

		
	</td>
	<td>
		<webwork:select theme="'simple'" label="'Item Status'" name="'ebayItemListContext.filter.itemStatus'" 
		value="ebayItemListContext.filter.itemStatus" 
		list="itemStatusTypes" listKey="name" listValue="friendlyName" emptyOption="true"  />		
	</td>
	<td>
		<webwork:select theme="'simple'" label="'Open/Closed'" name="'openClosed'" 
		value="ebayItemListContext.openClosedType.name" 
		list="openClosedTypes" listKey="name" listValue="friendlyName" emptyOption="true"   />			
	</td>
	<td>
		<webwork:select theme="'simple'" label="'View'" name="'view'" 
		value="ebayItemListContext.viewType.name" 
		list="viewTypes" listKey="name" listValue="friendlyName" emptyOption="false"   />		
	</td>
	<td>
		<webwork:select theme="'simple'" label="'Complete'" name="'ebayItemListContext.filter.returnMaximum'" 
		value="ebayItemListContext.filter.returnMaximum"
		list="maximums" listKey="key" listValue="value" emptyOption="false" />	
	</td>
</tr>
<tr>
<td colspan="8">&nbsp;</td>
	<td>
		<webwork:submit theme="'simple'" value="'Search'" name="'submitSearch'" />	

		<webwork:submit theme="'simple'" value="'Reset'" name="'submitReset'" />	

	</td>

</tr>
</table>

</webwork:form>
</div>

<jsp:include page="_auctionItems.jsp" flush="true" />
		
<jsp:include page="_auctionItemStats.jsp" flush="true" />

	</body>
</html>