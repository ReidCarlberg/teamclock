<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Item Shipping Modify</title>
	</head>
	<body>
		<h2>Item Shipping Modify</h2>
		
		<webwork:form method="'post'" action="'/auctionItemShippingModify.action'" >
		
		<webwork:textfield name="'targetShipping.length'" 
			label="'Length'" value="targetShipping.length" maxlength="'10'" />

		<webwork:textfield name="'targetShipping.width'" 
			label="'Width'" value="targetShipping.width" maxlength="'10'" />

		<webwork:textfield name="'targetShipping.height'" 
			label="'Height'" value="targetShipping.height" maxlength="'10'" />

		<webwork:textfield name="'targetShipping.weight'" 
			label="'Weight'" value="targetShipping.weight" maxlength="'10'" />

		<webwork:select label="'Box'" name="'targetShipping.boxId'" value="targetShipping.boxId"
			list="boxes" listKey="id" 
			listValue="length + 'L x ' + width + 'W x ' + height + 'H -- HC: ' + defaultHandlingCost " 
			emptyOption="true" />

		<webwork:textfield name="'targetShipping.handlingCharge'" 
			label="'Handling'" value="targetShipping.handlingCharge" maxlength="'10'" />

		<webwork:textfield name="'targetShipping.shippingCost'" 
			label="'Shipping Cost'" value="targetShipping.shippingCost" maxlength="'10'" />
		
		<webwork:textfield name="'targetShipping.carrier'" 
			label="'Carrier'" value="targetShipping.carrier" size="'20'" maxlength="'20'" />
		<webwork:textfield name="'targetShipping.tracking'" 
			label="'Tracking '" value="targetShipping.tracking" size="'40'" maxlength="'50'" />
		
		<webwork:submit name="'cancelShipping'" value="'Cancel'" />

		<webwork:submit name="'saveShipping'" value="'Save'" />		

		<webwork:if test="itemShippingModifyContext.target != null">
			<webwork:submit name="'copyShipping'" value="'Copy'" />		
			<webwork:submit name="'deleteShipping'" value="'Delete'" />		

		</webwork:if>
		
		</webwork:form>		

	</body>
</html>