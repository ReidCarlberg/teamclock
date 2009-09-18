<%@ taglib uri="/WEB-INF/webwork.tld" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Auction Item Modify</title>

	</head>
	<body>

		<table width="100%">
		<tr><td width="44%">
		<h2>Auction Item Modify</h2>
		</td>
		<td valign="bottom" width="50%">
			<webwork:property value="customerModifyContext.targetCustomer.name" />
		</td></tr>
		</table>
		
		
		
		<webwork:form theme="'simple'" action="'auctionModify.action'" name="'modify'" method="'post'">


<table border="1" width="100%">
<tr>
	<td width="44%" valign="top">
				<p>Status:
				<webwork:select theme="'simple'" label="'Item Status'" name="'targetItem.itemStatus'" 
				value="targetItem.itemStatus"
				list="itemStatus" listKey="name" listValue="friendlyName" emptyOption="false"  />		
		<p>Short Description:<br>
		<webwork:textfield theme="'simple'" name="'targetItem.descriptionShort'" 
			label="'Short Description'" value="targetItem.descriptionShort" size="'55'" maxlength="'55'" />
		
		<p>Full Description:<br>
		<webwork:textarea theme="'simple'"  name="'targetItem.description'" label="'Description'" 
		 value="targetItem.description" rows="7" cols="50" />		

		<p>Listing Headline:<br>
		<webwork:textfield theme="'simple'" name="'targetItem.listingHeadline'" 
			label="'Short Description'" value="targetItem.listingHeadline" size="'55'" maxlength="'55'" />
		
		<p>Listing Detail:<br>
		<webwork:textarea theme="'simple'"  name="'targetItem.listingDetail'" label="'Description'"  
			value="targetItem.listingDetail" rows="7" cols="50" />		

	</td>

	<td width="28%" valign="top">

			<table>
				<tr>
					<td>Starting price:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.priceStart'" label="'Starting Price'" value="targetItem.priceStart" /></td>
				</tr>
				<tr>
					<td>Buy it now price:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.priceBuyItNow'" label="'Buy It Now Price'" value="targetItem.priceBuyItNow" /></td>
				</tr>
				<tr>
					<td>Reserve price:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.priceReserve'" label="'Reserve Price'" value="targetItem.priceReserve" /></td>
				</tr>
				<tr>
					<td>Final price:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.priceFinal'" label="'Final Price'" value="targetItem.priceFinal" onchange="'compute(document.modify.elements[\"targetItem.priceFinal\"].value);'" /></td>
				</tr>
				<tr>
					<td>Lock prices?</td>
					<td><webwork:checkbox theme="'simple'" label="'checkbox test'" name="'targetItem.lockedCosts'" 
						value="targetItem.lockedCosts" fieldValue="'true'"/></td>

				</tr>
		
					<tr>
						<td>Prepay Amount:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.prepayAmount'" label="'Prepay'" value="targetItem.prepayAmount" /></td>
					</tr>	

					<tr>
						<td>Disc Reason:</td>
						<td> 
							<webwork:select theme="'simple'" 
								label="'Disc Type'" name="'targetItem.commissionDiscountTypeLuId'" 
								value="targetItem.commissionDiscountTypeLuId"
								list="discountTypeLookups" listKey="id" listValue="fullName" 
								emptyOption="true"   />
						</td>
					</tr>							
					<tr>
						<td>Disc Method:</td>
						<td>
							<webwork:select theme="'simple'" 
								label="'Disc Type'" name="'targetItem.commissionDiscountMethod'" 
								value="targetItem.commissionDiscountMethod"
								list="discountMethods" listKey="name" listValue="name" 
								emptyOption="true"   />						
						
						</td>
					</tr>							
					<tr>
						<td>Disc Base:</td>
						<td> <webwork:textfield theme="'simple'" 
							name="'targetItem.commissionDiscountBase'" label="'Disc Base.'" 
							value="targetItem.commissionDiscountBase" /></td>
					</tr>							
					<tr>
						<td>Discount Amt:</td>
						<td> <webwork:textfield theme="'simple'" 
							name="'targetItem.commissionDiscount'" label="'Discount Amt.'" 
							value="targetItem.commissionDiscount" /></td>
					</tr>
				
					<tr>
						<td>Commission:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.commission'" label="'Commission'" value="targetItem.commission" /></td>
					</tr>					
					<tr>
						<td>Net Deduction:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.customerNetDeduction'" 
							label="'Customer Net.'" value="targetItem.customerNetDeduction" /></td>
					</tr>
					<tr>
						<td>Deduction Reason:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.customerNetDeductionReason'" 
							label="'Customer Net.'" value="targetItem.customerNetDeductionReason" maxlength="'50'" /></td>
					</tr>					
					<tr>
						<td>Customer Net:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.customerNet'" label="'Customer Net.'" value="targetItem.customerNet" /></td>
					</tr>
					<tr>
						<td>Shipping Charge:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.shippingCharge'" label="'Customer Net.'" value="targetItem.shippingCharge" /></td>
					</tr>		


					<tr>
						<td>eBay Listing Fee:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.feeEbayListing'" label="'eBay Listing Fee'" value="targetItem.feeEbayListing" /></td>
					</tr>
					<tr>
						<td>eBay Final Fee:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.feeEbayFinal'" label="'eBay Final Value Fee'" value="targetItem.feeEbayFinal" /></td>
					</tr>
					<tr>
						<td>PayPal Fee:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.feePayPal'" label="'PayPal Fee'" value="targetItem.feePayPal" /></td>
					</tr>
					<tr>
						<td>Other Fee:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.feeOther'" label="'Other Fee'" value="targetItem.feeOther" /></td>
					</tr>			
					
										
					<tr>
						<td>Gross:</td>
						<td> <webwork:textfield theme="'simple'" name="'targetItem.grossProfit'" label="'Customer Net.'" value="targetItem.grossProfit" /></td>
					</tr>							
			</table>
	</td>

	<td width="28%" valign="top">

			<table>
				<tr>
					<td>Auction length:</td>
					<td><webwork:select theme="'simple'" name="'targetItem.length'" value="targetItem.length"  label="'Auction Length'" list="{1,3,5,7,10}" emptyOption="'true'" /></td>
				</tr>
				<tr>
					<td>eBay category id:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.ebayCategoryId'" label="'eBay Category Id'" value="targetItem.ebayCategoryId" /></td>
				</tr>
				<tr>
					<td>eBay item id (1):</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.ebayItemId'" label="'eBay Item Id'" value="targetItem.ebayItemId" /></td>
				</tr>
				<tr>
					<td>eBay item id (2):</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.ebayItemId2'" label="'eBay Item Id'" value="targetItem.ebayItemId2" /></td>
				</tr>
				<tr>
					<td>Weight (w/o packaging):</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.weight'" value="targetItem.weight"  label="'Auction Weight'" /></td>
				</tr>
				<tr>
					<td>Lock shipping?</td>
					<td><webwork:checkbox theme="'simple'" label="'checkbox test'" name="'targetItem.lockedShipping'" 
						value="targetItem.lockedShipping" fieldValue="'true'"/></td>
				</tr>
				
				<tr>
					<td>Shipping Weight:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.shippingWeight'" value="targetItem.shippingWeight"  label="'Shipping Weight'" /></td>
				</tr>
				<tr>
					<td>Handling Charge:</td>
					<td><webwork:textfield theme="'simple'" name="'targetItem.handlingCharge'" value="targetItem.handlingCharge"  label="'Handling Charge'" /></td>
				</tr>
				<tr>
						<td>Date added:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.dateAdded'" label="'Date Added'" value="targetItem.dateAdded" /></td>
					</tr>
					<tr>
						<td>Date started:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.dateStart'" label="'Date Started'" value="targetItem.dateStart" /></td>
					</tr>
					<tr>
						<td>Date ended:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.dateEnded'" label="'Date Ended'" value="targetItem.dateEnded" /></td>
					</tr>
					<tr>
						<td>Date paid:</td>
						<td><webwork:textfield theme="'simple'" name="'targetItem.dateNetPaid'" label="'Date Paid'" value="targetItem.dateNetPaid" /></td>
					</tr>					
					
					<tr>
						<td>Commission override:</td>
						<td>
							<webwork:select theme="'simple'" label="'Commission'" name="'targetItem.simpleCommissionId'" 
							value="targetItem.simpleCommissionId"
							list="commissions" listKey="id" listValue="name" emptyOption="true"  /> 						
						</td>
			</table>
	</td>

</table>


		<table width="100%">
			<tr>
				<td width="50%">
					<webwork:if  test="ebayItemModifyContext.target != null">
						<webwork:submit theme="'simple'" value="'Delete'" name="'ebayDelete'" />
						<!--
							<webwork:submit theme="'simple'" value="'Copy'" name="'ebayCopy'" />
						-->
					</webwork:if>				
				</td>
				<td width="50%" align="right">
					<webwork:submit theme="'simple'" value="'Save'" name="'ebaySubmit'" />
					<webwork:submit theme="'simple'" value="'Cancel'" name="'ebayCancel'" />				
				</td>
			</tr>
		<table>



		</webwork:form>
		
	</body>
</html>