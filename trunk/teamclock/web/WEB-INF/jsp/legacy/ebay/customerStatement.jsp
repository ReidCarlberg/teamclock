<%@ taglib uri="/WEB-INF/webwork.tld" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Statement</title>
	</head>
	<body>
	
	<h1 align="center">Statement</h1>
	
	<p><webwork:property value="customer.name" /><br>
	<webwork:property value="customer.street1" /> <br>

		<webwork:property value="customer.street2" /> <br>
	<webwork:property value="customer.city" />, <webwork:property value="customer.state" /> <webwork:property value="customer.zip" />	
	
	<h3>Items Ready to Pay</h3>
	
	<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td valign="top" class="small">Name</td>
		<td valign="top" class="small">Status</td>
		<td valign="top" class="small">Added</td>
		<td valign="top" class="small">Sale Price</td>
		<webwork:if test="readyDiscounted">
			<td valign="top" class="small">Discount</td>		
		</webwork:if>
		<td valign="top" class="small">Prepaid Amount</td>
		<td valign="top" class="small">Commission</td>
		<webwork:if test="readyDeductions">
			<td valign="top" class="small">Deductions</td>		
		</webwork:if>
		<td valign="top" class="small">Customer Net</td>
	</tr>
	<webwork:iterator value="ready">
		<tr>
			<td valign="top" class="small"><webwork:property value="descriptionShort"/></td>
			<td valign="top" class="small"><webwork:property value="itemStatus"/></td>
			<td valign="top" class="small"><webwork:property value="dateAdded"/></td>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="priceFinal" /></webwork:text></td>
			<webwork:if test="readyDiscounted">
				<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="commissionDiscount" /></webwork:text></td>
			</webwork:if>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="prepayAmount" /></webwork:text></td>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="commission" /></webwork:text></td>
			<webwork:if test="readyDeductions">
				<td valign="top" class="small">
					<webwork:text name="'format.money'" ><webwork:param value="customerNetDeduction" /></webwork:text><br>
					<webwork:property value="customerNetDeductionReason" />
				</td>
			</webwork:if>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="customerNet" /></webwork:text></td>
		</tr>				
	</webwork:iterator>
		<tr>
			<td colspan="3" valign="top" class="small">Total</td>
			<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="readyStats.sale" /></webwork:text> </td>		
			<webwork:if test="readyDiscounted">
				<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="readyStats.discount" /></webwork:text> </td>
			</webwork:if>
			<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="readyStats.prepay" /></webwork:text> </td>
			<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="readyStats.commission" /></webwork:text> </td>
			<webwork:if test="readyDeductions">
				<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="readyStats.netDeduction" /></webwork:text> </td>
			</webwork:if>
			<td  valign="top" class="small"><webwork:text name="'format.money'"  ><webwork:param value="readyStats.net" /></webwork:text></td>		
			
		</tr>
	</table>




	
	<h3>Other Open Items</h3>

	<table width="100%" border="1" cellspacing="0" cellpadding="4" style="border-collapse: collapse;">
	<tr>
		<td valign="top" class="small">Name</td>
		<td valign="top" class="small">Status</td>
		<td valign="top" class="small">Added</td>
		<td valign="top" class="small">Sale Price</td>
		<webwork:if test="openDiscounted">
			<td valign="top" class="small">Discount</td>		
		</webwork:if>
		<td valign="top" class="small">Prepaid Amount</td>
		<td valign="top" class="small">Commission</td>
		<webwork:if test="openDeductions">
			<td valign="top" class="small">Deductions</td>		
		</webwork:if>		
		<td valign="top" class="small">Customer Net</td>
	</tr>
	<webwork:iterator value="open">
		<tr>
			<td valign="top" class="small"><webwork:property value="descriptionShort"/></td>
			<td valign="top" class="small"><webwork:property value="itemStatus"/></td>
			<td valign="top" class="small"><webwork:property value="dateAdded"/></td>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="priceFinal" /></webwork:text></td>
			<webwork:if test="openDiscounted">
				<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="commissionDiscount" /></webwork:text></td>
			</webwork:if>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="prepayAmount" /></webwork:text></td>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="commission" /></webwork:text></td>
			<webwork:if test="openDeductions">
				<td valign="top" class="small">
					<webwork:text name="'format.money'" ><webwork:param value="customerNetDeduction" /></webwork:text><br>
					<webwork:property value="customerNetDeductionReason" />
				</td>
			</webwork:if>
			<td valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="customerNet" /></webwork:text></td>
		</tr>				
	</webwork:iterator>
		<tr>
			<td colspan="3" valign="top" class="small">Total</td>
			<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="openStats.sale" /></webwork:text> </td>		
			<webwork:if test="openDiscounted">
				<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="openStats.discount" /></webwork:text> </td>
			</webwork:if>
			<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="openStats.prepay" /></webwork:text> </td>
			<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="openStats.commission" /></webwork:text> </td>
			<webwork:if test="openDeductions">
				<td  valign="top" class="small"><webwork:text name="'format.money'" ><webwork:param value="openStats.netDeduction" /></webwork:text> </td>
			</webwork:if>
			<td  valign="top" class="small"><webwork:text name="'format.money'"  ><webwork:param value="openStats.net" /></webwork:text></td>		
			
		</tr>	
	</table>

	

	</body>
</html>