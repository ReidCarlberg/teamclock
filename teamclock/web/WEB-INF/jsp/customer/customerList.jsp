<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="/WEB-INF/tlds/authorization.tld" prefix="auth" %>

<%@ page import="com.fivesticks.time.useradmin.FstxTimeSystemRights" %>

<html><head><title>Customer List</title></head><body>

<table width="100%">
	<tr>
		<td valign="top">
			<h2>Customer List</h2>
		</td>
		<td align="right">
			<p>
			<a href="<webwork:url value="'/customermodify.action'" />">Add a Customer</a> |
			<a href="<webwork:url value="'/customerContactProjectAdd.action'" />">Add Customer, Contact & Project</a>
		</td>
	</tr>
</table>
<div id="listFilter" >	
<webwork:form method="'post'" action="'customerlist.action'">	


<table >
	<tr>
		<th>Customer Id</th>
		<th>Name</th>
		<th>Status</th>
		<th>Return</th>
		<th>Special</th>
		<th>Hidden</th>
		<th>Go</th>
	</tr>
	<tr>
		<td>
			<webwork:textfield theme="'simple'" name="'customerListContext.filter.id'" 
				label="'Short Description'" value="customerListContext.filter.id" size="'5'" maxlength="'10'" />
		
		</td>
		<td>
			<webwork:textfield theme="'simple'" name="'customerListContext.filter.nameLike'" 
				label="'Short Description'" value="customerListContext.filter.nameLike" size="'20'" maxlength="'20'" />
		
		</td>
		<td>
			<webwork:select theme="'simple'" label="'Item Status'" name="'customerListContext.filter.status'" 
			value="customerListContext.filter.status" 
			list="statusLookups" listKey="id" listValue="shortName" emptyOption="true"  />		
		</td>
		<td>
			<webwork:select theme="'simple'" label="'Complete'" name="'customerListContext.filter.returnMaximum'" 
			value="customerListContext.filter.returnMaximum"
			list="maximums" listKey="key" listValue="value" emptyOption="false" />	
		
		</td>
		<td>
			<webwork:select theme="'simple'" label="'Complete'" name="'special'" 
			value="special"
			list="#{'negative':'Negative Balance', 'nocontact':'Without a Contact'}" emptyOption="true" />		
	
		</td>
		<td>
			<webwork:checkbox theme="'simple'" name="'showHidden'"
				fieldValue="'true'" value="showHidden" />
		</td>
		<td>
			<webwork:submit theme="'simple'" value="'Search'" name="'submitSearch'" />	

			<webwork:submit theme="'simple'" value="'Reset'" name="'submitReset'" />	

		</td>
	</tr>
</table>

</webwork:form>	
</div>

<jsp:include page="_customerList.jsp" flush="true" />


</body></html>

