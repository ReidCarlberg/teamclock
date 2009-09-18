<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Auction Customer Modify</title>
	</head>
	<body>
	

	<h2>Auction Customer Modify</h2>
	
	<webwork:if test="sessionContext.rights.canUseAccountTransactions">
		<p><a href="<webwork:url value="'/auctionCustomerViewAccount.action'" >
		<webwork:param name="'target'" value="customer.id" /></webwork:url>">
		eBay Account Transactions</a>	
	</webwork:if>		
	
	<webwork:form method="'post'" action="'auctionCustomerModify.action'">
	
	<webwork:textfield label="'First Name'" name="'contact.nameFirst'" value="contact.nameFirst" size="'30'" />

	<webwork:textfield label="'Last Name'" name="'contact.nameLast'" value="contact.nameLast" size="'30'" />
	
	<webwork:textfield label="'Company'" name="'customer.name'" value="customer.name"size="'30'"  />	
	
	<webwork:textfield label="'Street (1)'" name="'customer.street1'" value="customer.street1" size="'30'" />	
	
	<webwork:textfield label="'Street (2)'" name="'customer.street2'" value="customer.street2" size="'30'" />	
	
	<webwork:textfield label="'City'" name="'customer.city'" value="customer.city" size="'30'" />
	
	<webwork:textfield label="'State'" name="'customer.state'" value="customer.state" />
	
	<webwork:textfield label="'Zip'" name="'customer.zip'" value="customer.zip" />	
	
	<webwork:textfield label="'Phone 1'" name="'contact.primaryPhone'" value="contact.primaryPhone" />

	<webwork:textfield label="'Phone 2'" name="'contact.alternatePhone'" value="contact.alternatePhone" />
	
	<webwork:textfield label="'Email'" name="'contact.primaryEmail'" value="contact.primaryEmail" size="'30'" />	

	<webwork:select label="'Commission Override'" name="'customerCommission'" 
	value="customerCommission"
	list="commissions" listKey="id" listValue="name" emptyOption="true"  /> 

	<webwork:submit name="'saveCustomer'" value="'Save'" />

	<webwork:submit name="'cancelCustomer'" value="'Cancel'" />

	<webwork:if test="customerModifyContext.targetCustomer != null" >
		<webwork:submit name="'deleteCustomer'" value="'Delete'" />
	</webwork:if>

	</webwork:form>
	
	</body>
</html>