<%@ taglib uri="webwork" prefix="webwork" %>







<p>
		
<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td valign="top">ID</td>
		<td valign="top">Short Name</td>
		<td valign="top">Customer</td>
		<td valign="top">Status</td>
		<td valign="top">Added</td>
		<td valign="top">Sale Price</td>
		<td valign="top">Commission</td>
		<td valign="top">Customer Net</td>
	</tr>
	<webwork:iterator value="items">
		<tr>
			<td valign="top"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="item.id" /></webwork:url>" ><webwork:property value="item.id"/></a></td>
			<td valign="top"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="item.id" /></webwork:url>" ><webwork:property value="item.descriptionShort"/></a></td>
			<td valign="top"><webwork:property value="customer.name"/></td>
			<td valign="top"><webwork:property value="item.itemStatus"/></td>
			<td valign="top"><webwork:property value="item.dateAdded"/></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="item.priceFinal" /></webwork:text></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="item.commission" /></webwork:text></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="item.customerNet" /></webwork:text></td>
		</tr>				
	</webwork:iterator>


</table>
		
