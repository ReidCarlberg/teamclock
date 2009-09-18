<%@ taglib uri="webwork" prefix="webwork" %>







<p>
		
<table width="100%" border="1" cellspacing="0" cellpadding="2" style="border-collapse: collapse;">
	<tr>
		<td valign="top">Short Name</td>
		<td valign="top">Customer</td>
		<td valign="top">Ebay Id</td>
		<td valign="top">Status</td>
		<td valign="top">Added</td>
		<td valign="top">Updated Status</td>
	</tr>
	<webwork:iterator value="items">
		<tr>
			<td valign="top"><a href="<webwork:url value="'auctionItemDetail.action'" ><webwork:param name="'target'" value="item.id" /></webwork:url>" ><webwork:property value="item.descriptionShort"/></a></td>
			<td valign="top"><webwork:property value="customer.name"/></td>
			<td valign="top"><webwork:property value="item.ebayItemId"/></td>		
			<td valign="top"><webwork:property value="item.itemStatus"/></td>
			<td valign="top"><webwork:property value="item.dateAdded"/></td>
			<td valign="top">
				<table>
				<tr>
				<td>
				<webwork:form method="'post'" action="'auctionUpdateStatus.action'" >
					<webwork:hidden name="'target'" value="item.id" />
					<webwork:select theme="'simple'" label="'Item Status'" name="'targetStatus'" 
					value="item.itemStatus" onchange="'form.submit();'"
					list="updatableItemStatusTypes" listKey="name" listValue="friendlyName" emptyOption="true"  />	
				</webwork:form>
				</td></tr></table>				
			</td>
		</tr>				
	</webwork:iterator>




</table>
		

<webwork:if test="okToPost">
	<p><a href="<webwork:url value="'auctionPostItemsToAccount.action'" />">Post to account</a>.  This cannot be undone.
</webwork:if>
<webwork:else>
	<p>If you're looking to post to account, you have first search for a status of ready to pay net.
</webwork:else>

<webwork:if test="okToClose">
	<p><a href="<webwork:url value="'auctionMarkPostedItemsClosed.action'" />">Update status to "closed - sold"</a>.  This cannot be undone.
</webwork:if>
<webwork:else>
	<p>If you're looking to mark as closed, you have to first search for a status of posted to account.
</webwork:else>