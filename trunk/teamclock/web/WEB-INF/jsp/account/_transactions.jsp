<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>




<p><webwork:property value="accountTransactionContext.label" /> - <webwork:property value="accountTransactionContext.objectTransactionType.name" />

<p>Balance: <webwork:property value="currentBalance" />

<p>

<div id="listTable" >	
		
<table >
	<tr>
		<th valign="top">Date</th>
		<th valign="top">Type</th>
		<th valign="top">Amount</th>
		<th valign="top">Comments</th>
		<th valign="top">By</th>
		<th valign="top">Balance</th>
		<th nowrap>&nbsp;</th>
	</tr>
	<webwork:iterator value="transactions">
		<webwork:if test="type == 'ADD_VALUE'">
			<webwork:set name="bgcolor" value="'#cccccc'" />
		</webwork:if>
		<webwork:else>
			<webwork:set name="bgcolor" value="'#ffffff'" />	
		</webwork:else>						
			<tr>
				<td valign="top" bgcolor="<webwork:property value="#bgcolor" />"><a href="<webwork:url value="'accttxnModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>" ><webwork:property value="date"/></a></td>
				<td valign="top" bgcolor="<webwork:property value="#bgcolor" />"><webwork:property value="type"/></td>
				<td valign="top" bgcolor="<webwork:property value="#bgcolor" />"><webwork:property value="amount"/></td>
				<td valign="top" bgcolor="<webwork:property value="#bgcolor" />"><webwork:property value="comments"/></td>
				<td valign="top" bgcolor="<webwork:property value="#bgcolor" />"><webwork:property value="enteredBy"/></td>
				<td valign="top" bgcolor="<webwork:property value="#bgcolor" />"><webwork:property value="decoratedBalance"/></td>				
				<td nowrap  bgcolor="<webwork:property value="#bgcolor" />">
				<webwork:if test="activityId != null">
						 <a href="<webwork:url value="'/activityrecordmodify.action'" >
						<webwork:param name="'target'" value="activityId" /></webwork:url>">
						Activity Record</a>
					
				</webwork:if>
				</td>
			</tr>	
	</webwork:iterator>


</table>

</div>
		

