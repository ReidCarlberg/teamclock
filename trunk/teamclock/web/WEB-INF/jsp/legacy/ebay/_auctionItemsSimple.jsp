<%@ taglib uri="webwork" prefix="webwork" %>







<p>
		
<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td valign="top">Short Name</td>
		<td valign="top">Status</td>
		<td valign="top">Added</td>
		<td valign="top">Sale Price</td>
		<td valign="top">Commission</td>
		<td valign="top">Customer Net</td>
	</tr>
	<webwork:iterator value="items">
		<tr>
			<td valign="top"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="id" /></webwork:url>" ><webwork:property value="descriptionShort"/></a></td>
			<td valign="top"><webwork:property value="itemStatus"/></td>
			<td valign="top"><webwork:property value="dateAdded"/></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="priceFinal" /></webwork:text></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="commission" /></webwork:text></td>
			<td valign="top"><webwork:text name="'format.money'" ><webwork:param value="customerNet" /></webwork:text></td>
		</tr>				
	</webwork:iterator>


</table>
		
