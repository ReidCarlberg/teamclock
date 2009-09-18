<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Auction Setup Box Modify</title>
	</head>
	<body>
		<h2>Auction Setup: Box Modify</h2>
		
		<webwork:form method="'post'" action="'/auctionSetupBoxModify.action'" >
		
		<webwork:textfield name="'targetBox.name'" 
			label="'Name'" value="targetBox.name" size="'40'" maxlength="'55'" />

		<webwork:textfield name="'targetBox.length'" 
			label="'Length'" value="targetBox.length" maxlength="'10'" />
		
		<webwork:textfield name="'targetBox.width'" 
			label="'Width'" value="targetBox.width" maxlength="'10'" />
		
		<webwork:textfield name="'targetBox.height'" 
			label="'Height'" value="targetBox.height" maxlength="'10'" />
		
		<webwork:textfield name="'targetBox.weight'" 
			label="'Weight'" value="targetBox.weight" maxlength="'10'" />
		
		<webwork:textfield name="'targetBox.cost'" 
			label="'Cost'" value="targetBox.cost" maxlength="'10'" />
		
		<webwork:textfield name="'targetBox.defaultHandlingCost'" 
			label="'Default Handling'" value="targetBox.defaultHandlingCost" maxlength="'10'" />

		<webwork:textfield name="'targetBox.qtyOnHand'" 
			label="'Qty On Hand'" value="targetBox.qtyOnHand" maxlength="'10'" />
		
		<webwork:select label="'Active'" name="'surrogateActive'" value="surrogateActive"
			list="booleanSurrogate" listKey="key" listValue="value" emptyOption="false"  />
		
		<webwork:submit name="'cancelBox'" value="'Cancel'" />

		<webwork:submit name="'saveBox'" value="'Save'" />		

		<webwork:if test="boxModifyContext.target != null">
			<webwork:submit name="'copyBox'" value="'Copy'" />		
			<webwork:submit name="'deleteBox'" value="'Delete'" />		

		</webwork:if>
		
		</webwork:form>		

	</body>
</html>