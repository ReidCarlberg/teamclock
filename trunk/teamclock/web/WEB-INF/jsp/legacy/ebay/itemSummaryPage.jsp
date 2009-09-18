<%@ taglib uri="/WEB-INF/webwork.tld" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Auction Item Summary</title>
		
<script>
<!-- //

function handleSetCaption(imageTarget) {

	var partial= prompt('Enter your caption.', '');
	
	if (partial != null) {
		var newLoc = 'auctionItemImageSetCaption.action';
		newLoc = newLoc + '?imagetarget=' + imageTarget;
		newLoc = newLoc + '&caption=' + escape(partial);
		location.href = newLoc;
	}
}

// -->
</script>
		
	</head>
	<body>


<table width="100%" >
<tr>
<td width="10%" valign="top">
	<table width="100%">
	<webwork:iterator value="ebayItemListContext.results" >
		<tr>
			<td class="small"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="item.id" /></webwork:url>" ><webwork:property value="shortDescriptionShort"/></a></td>
		</tr>
	</webwork:iterator>
	</table>
</td>

<td width="90%" valign="top">

		<table width="100%">
		
		<tr><td width="25%">
		<h2>Auction Item Summary</h2>
		</td>
		<td valign="bottom" width="25%">
			<webwork:property value="customerModifyContext.targetCustomer.name" />
			<webwork:property value="customerModifyContext.firstContactPhone" />
		</td>
		<td valign="bottom" width="25%">
		
			<a href="<webwork:url value="'auctionModify.action'" ><webwork:param name="'target'" value="ebayItemModifyContext.target.id" /></webwork:url>" >Modify</a>
			<!--
			<a target="_blank" href="<webwork:url value="'auctionForm.action'"><webwork:param name="'form'" value="'forms/naa1.jsp'" /></webwork:url>">Listing</a>
			-->
			<a href="<webwork:url value="'auctionItemDetailToggleView.action'" />">Toggle View</a>
		<td>
		<td valign="bottom" width="25%">
				<webwork:form action="'auctionListingForm.action'">
					<webwork:select theme="'simple'" label="'Item Status'" name="'target'" 
					value=""
					list="listingEbayForms" listKey="id" listValue="name" emptyOption="true" onchange="'form.submit();'" />		
				</webwork:form>
		</td>		
		</tr>
		</table>
		
	<table width="100%">
		<tr>
		
			<td width="50%" valign="top" class="medium">
		
				<p>Description<br>
				<webwork:property value="ebayItemModifyContext.target.descriptionShort" /><br>
				<webwork:property value="ebayItemModifyContext.target.description"/>
				
				<p>Listing<br>
				<webwork:property value="ebayItemModifyContext.target.listingHeadline"/><br>
				<webwork:property value="ebayItemModifyContext.target.listingDetail"/>				
				

				
				
				

				
			</td>
			<td width="25%" valign="top">

			<table class="medium">
		
				<tr>
					<td>Auction length:</td>
					<td><webwork:property  value="ebayItemModifyContext.target.length"  /></td>
				</tr>
				<tr>
					<td>eBay category id:</td>
					<td><webwork:property  value="ebayItemModifyContext.target.ebayCategoryId" /></td>
				</tr>
				<tr>
					<td>eBay item id (1):</td>
					<td><webwork:property   value="ebayItemModifyContext.target.ebayItemId" /></td>
				</tr>
				<tr>
					<td>eBay item id (2):</td>
					<td><webwork:property   value="ebayItemModifyContext.target.ebayItemId2" /></td>
				</tr>
				<tr>
					<td>Weight (w/o packaging):</td>
					<td><webwork:property  value="ebayItemModifyContext.target.weight"   /></td>
				</tr>
				<tr>
					<td>Shipping Locked:</td>
					<td><webwork:property   value="ebayItemModifyContext.target.lockedShipping" /></td>
				</tr>				
				<tr>
					<td>Shipping Weight:</td>
					<td><webwork:property   value="ebayItemModifyContext.target.shippingWeight" /></td>
				</tr>
				<tr>
					<td>Handling Charge:</td>
					<td><webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.handlingCharge" /></webwork:text></td>
				</tr>
				<tr>
					<td>Date added:</td>
						<td><webwork:property   value="ebayItemModifyContext.target.dateAdded" /></td>
					</tr>
					<tr>
						<td>Date started:</td>
						<td><webwork:property   value="ebayItemModifyContext.target.dateStart" /></td>
					</tr>
					<tr>
						<td>Date ended:</td>
						<td><webwork:property    value="ebayItemModifyContext.target.dateEnded" /></td>
					</tr>
					<tr>
						<td>Date paid:</td>
						<td><webwork:property   value="ebayItemModifyContext.target.dateNetPaid" /></td>
					</tr>	
				<tr>
					<td>Starting price:</td>
					<td><webwork:text name="'format.money'"   ><webwork:param value="ebayItemModifyContext.target.priceStart" /></webwork:text></td>
				</tr>
				<tr>
					<td>Buy it now price:</td>
					<td><webwork:text name="'format.money'"   ><webwork:param value="ebayItemModifyContext.target.priceBuyItNow" /></webwork:text></td>
				</tr>
				<tr>
					<td>Reserve price:</td>
					<td><webwork:text name="'format.money'"  ><webwork:param value="ebayItemModifyContext.target.priceReserve" /></webwork:text></td>
				</tr>
				<tr>
					<td>Final price:</td>
					<td><webwork:text name="'format.money'"   ><webwork:param value="ebayItemModifyContext.target.priceFinal" /></webwork:text></td>
				</tr>
				<webwork:if test="ebayItemModifyContext.standardView">
					<tr>
						<td>Costs Locked:</td>
						<td><webwork:property   value="ebayItemModifyContext.target.lockedCosts" /></td>
					</tr>					
			
					<tr>
						<td>Disc Reason:</td>
						<td> <webwork:property    value="ebayItemModifyContext.discountReason" /></td>
					</tr>				
					<tr>
						<td>Disc Method:</td>
						<td><webwork:property    value="ebayItemModifyContext.target.commissionDiscountMethod" /></td>
					</tr>				
					<tr>
						<td>Disc Base:</td>
						<td><webwork:property    value="ebayItemModifyContext.target.commissionDiscountBase" /></td>
					</tr>				
					<tr>
						<td>Discount Amt:</td>
						<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.commissionDiscount" /></webwork:text></td>
					</tr>	
				</webwork:if>		
				<tr>
					<td>Commission:</td>
					<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.commission" /></webwork:text></td>
				</tr>			
				<tr>
					<td>Prepay Amount:</td>
					<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.prepayAmount" /></webwork:text></td>
				</tr>	
				<tr>
					<td>Net Deduction:</td>
					<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.customerNetDeduction" /></webwork:text></td>
				</tr>	
				<tr>
					<td>Deduction Reason:</td>
					<td> <webwork:property value="ebayItemModifyContext.target.customerNetDeductionReason" /></td>
				</tr>	
				<tr>
					<td>Customer Net:</td>
					<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.customerNet" /></webwork:text></td>
				</tr>													
				<webwork:if test="ebayItemModifyContext.standardView">
					<tr>
						<td>Shipping Charge:</td>
						<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.shippingCharge" /></webwork:text></td>
					</tr>				

					<tr>
						<td>eBay Listing Fee:</td>
						<td><webwork:text name="'format.money'"   ><webwork:param value="ebayItemModifyContext.target.feeEbayListing" /></webwork:text></td>
					</tr>
					<tr>
						<td>eBay Final Fee:</td>
						<td><webwork:text name="'format.money'"   ><webwork:param value="ebayItemModifyContext.target.feeEbayFinal" /></webwork:text></td>
					</tr>
					<tr>
						<td>PayPal Fee:</td>
						<td><webwork:text name="'format.money'"  ><webwork:param value="ebayItemModifyContext.target.feePayPal" /></webwork:text></td>
					</tr>
					<tr>
						<td>Other Fee:</td>
						<td><webwork:text name="'format.money'"  ><webwork:param value="ebayItemModifyContext.target.feeOther" /></webwork:text></td>
					</tr>		
					
			


														
					<tr>
						<td>Gross Profit:</td>
						<td> <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.target.grossProfit" /></webwork:text></td>
					</tr>		
					<tr>
						<td>Commission Override:</td>
						<td><webwork:property value="ebayItemModifyContext.commissionOverride" /></td>
					</tr>																		
				</webwork:if>		
			</table>	
			</td>
			<td width="25%" valign="top">
				<p class="small">Shipping	<a href="<webwork:url value="'auctionItemShippingModify.action'" />">Add</a>
				
				<table width="100%"> 
					<tr>
						<th class="small">Len</th>
						<th class="small">Wid</th>
						<th class="small">Hgt</th>
						<th class="small">Hand</th>
						<th class="small">Ship</th>
						<th class="small">&nbsp;</th>
					</tr>
					<webwork:iterator value="ebayItemModifyContext.shipping">
						<tr>
							<td class="small"><webwork:property value="length" /></td>
							<td class="small"><webwork:property value="width" /></td>
							<td class="small"><webwork:property value="height" /></td>
							<td class="small"><webwork:text name="'format.money'" ><webwork:param value="handlingCharge" /></webwork:text></td>
							<td class="small"><webwork:text name="'format.money'" ><webwork:param value="shippingCost" /></webwork:text></td>
							<td class="small"><a href="<webwork:url value="'auctionItemShippingModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">Mod</a></td>
						</tr>
					</webwork:iterator>
					<tr>
						<td class="small">Stats</td>
						<td class="small"># </td>
						<td class="small">W <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.shippingStats.weight" /></webwork:text></td>
						<td class="small">H <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.shippingStats.handling" /></webwork:text></td>
						<td class="small">S <webwork:text name="'format.money'" ><webwork:param value="ebayItemModifyContext.shippingStats.shipping" /></webwork:text></td>
						<td class="small"></td>
					</tr>
				</table>

				<p class="small">Images				<a href="<webwork:url value="'auctionImageUpload.action'" />">Add</a>
				
				<webwork:iterator value="ebayItemModifyContext.images">
				
				<table>
				<tr><td valign="top">
				<img border="0" src="<webwork:property value="sessionContext.settings.ebayImagesSrc" />/<webwork:property value="imageSmall" />">
				</td><td valign="top" class="small">
					<a target="_blank" href="<webwork:property value="sessionContext.settings.ebayImagesSrc" />/<webwork:property value="imageFull" />">View</a><br>
					<a href="<webwork:url value="'auctionImageEarlier.action'"><webwork:param name="'imgtarget'" value="id" /></webwork:url>">Sooner</a><br>
					<a href="<webwork:url value="'auctionImageLater.action'"><webwork:param name="'imgtarget'" value="id" /></webwork:url>">Later</a><br>
					<a href="<webwork:url value="'auctionImageDelete.action'"><webwork:param name="'imgtarget'" value="id" /></webwork:url>">Delete</a><br>
					<a href="#" onclick="javascript:handleSetCaption('<webwork:property value="id" />');">Caption</a>
				</td>
				</tr>
				<webwork:if test="caption != null">
				<tr>
					<td colspan="2" class="small">
						<webwork:property value="caption" />
					</td>
				</tr>
				</webwork:if>
				
				</table>
			
				</webwork:iterator>
			</td>
		</tr>
	</table>
	
</td>

</tr>
<table>	
		
	</body>
</html>