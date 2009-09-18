<%@ taglib uri="webwork" prefix="webwork" %>







<p>
		
<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td valign="top">ID</td>
		<td valign="top">Short Name</td>
		<td valign="top">Customer</td>
		<td valign="top">Ebay Id</td>
		<td valign="top">Status</td>
		<td valign="top">Added</td>
		<td valign="top">Listed</td>
		<td valign="top">Closed</td>
		<td valign="top">NAA Paid</td>
		<td valign="top">Sale Price</td>
		<td valign="top">Commission</td>
		<!--
		<td valign="top"></td>
		-->
		<td valign="top"></td>
	</tr>
	<webwork:iterator value="items">
		<tr>
			<td valign="top"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="item.id" /></webwork:url>" ><webwork:property value="item.id"/></a></td>
			<td valign="top"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="item.id" /></webwork:url>" ><webwork:property value="item.descriptionShort"/></a></td>
			<td valign="top"><webwork:property value="customer.name"/></td>
			<td valign="top"><webwork:property value="item.ebayItemId"/></td>
			<td valign="top"><webwork:property value="item.itemStatus"/></td>
			<td valign="top"><webwork:property value="item.dateAdded"/></td>
			<td valign="top"><webwork:property value="item.dateStart"/></td>
			<td valign="top"><webwork:property value="item.dateEnded"/></td>
			<td valign="top"><webwork:property value="item.dateNetPaid"/></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="item.priceFinal" /></webwork:text></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="item.commission" /></webwork:text></td>
			<!--
			<td valign="top">
				<table>
				<tr>
				<td>
				<form method="post" action="http://fstxblog.com/naa/main/item.php">
					<input type="hidden" name="name" value="<webwork:property value="customerModifyContext.targetCustomer.name" escape="true" /> " >
					<input type="hidden" name="item" value="<webwork:property value="item.descriptionShort" escape="true" />" >
					<input type="hidden" name="starting" value="<webwork:property value="item.priceStart" />" >
					<input type="hidden" name="length" value="<webwork:property value="item.length" />" >
					<input type="hidden" name="notes" value="<webwork:property value="item.description" escape="true" />" >
					<input type="submit" value="Agree">
				</form>			
				</td></tr></table>				
			</td>
			-->
			<td valign="top">
				<webwork:form action="'auctionItemFormPrint.action'">
					<webwork:hidden name="'targetItem'" value="item.id" />
					<webwork:select theme="'simple'" label="'Item Status'" name="'target'" 
					value=""
					list="forms" listKey="id" listValue="name" emptyOption="true" onchange="'form.submit();'" />		
				</webwork:form>					
			</td>
			
		</tr>				
	</webwork:iterator>


</table>
		
