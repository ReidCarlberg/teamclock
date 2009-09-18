<%@ taglib uri="webwork" prefix="webwork" %>



<html><head><title>Customer User List</title></head><body>

<table>
<tr>
<td valign="top">
<h2>Customer User List</h2>
</td>
<td valign="bottom">
<p>
<a href="<webwork:url value="'/customerUserAdd.action'" />">Add+</a>
</td>
</tr>
</table>


<p>Customer: <webwork:property value="customerModifyContext.targetCustomer.name" />

<p>&nbsp;

<div id="listTable" >
	<table >
		<tr>
			<td>Username</td>
			<td>Actions</td>
		</tr>
		<webwork:iterator value="associated" >
			<tr>
				<td><webwork:property value="username"/></td>
				<td><a href="<webwork:url value="'/customerUserRemove.action'"><webwork:param name="'target'" value="username"/></webwork:url>">Remove Association</a></td>
			</tr>
		</webwork:iterator>
	</table>
</div>


</body>
</html>

