<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>User List</title></head><body>





<h2>User List</h2>

<p><a href="<webwork:url value="'userAdd.action'" />">Add</a>


<div id="listTable" >
<table>
<tr>
<th>Username</th>
<th>Type</th>
<th>Email</th>
<th>Status</th>
<th>&nbsp;</th>
</tr>
<webwork:iterator value="users">
	<tr>
		<td>
			<a href="<webwork:url value="'userModify.action'"><webwork:param name="'target'" value="user.username" /></webwork:url>"><webwork:property value="user.username" /></a>
		</td>
		<td>
			<webwork:property value="userTypeEnum.publicName" />
		</td>
		<td>
			<webwork:property value="user.email" />
		</td>
		<td>
			<webwork:if test="user.inactive">
				Inactive
			</webwork:if>
		</td>
		<td>
			<webwork:if test="user.booleanInactive">
				<a href="<webwork:url value="'/userActive.action'" ><webwork:param name="'target'" value="user.username"/></webwork:url>">Make Active</a>
			</webwork:if>
			<webwork:else>
				<a href="<webwork:url value="'/userInactive.action'" ><webwork:param name="'target'" value="user.username"/></webwork:url>">Make Inactive</a>
			</webwork:else> | 
			<a href="<webwork:url value="'/passwordModify.action'"><webwork:param name="'targetUser'" value="user.username" /></webwork:url>">Change Password</a>
		</td>
	</tr>

</webwork:iterator>


</table>
</div>

</body>
</html>