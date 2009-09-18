<%@ taglib uri="webwork" prefix="webwork" %>


<div id="listTable" >
	<table cellpadding="5" border="1" style="border-collapse: collapse;">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>City</th>
			<th>State</th>
			<th>Status</th>
			<th>Balance</th>
			<th>Actions</th>
		</tr>
		<webwork:iterator value="customers" >
			<tr>
				<td>
					<a href="<webwork:url value="'/customerdetail.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					<webwork:property value="id" /></a>

					
				</td>
				<td>
					<a href="<webwork:url value="'/customerdetail.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					<webwork:property value="name" /></a>

					<webwork:if test="fstxCustomer.hidden">(Hidden)</webwork:if>
				</td>
				<td>
					<webwork:property value="city" />
				</td>
				<td>
					<webwork:property value="state" />
				</td>
				<td>
					<webwork:property value="status.fullName" />
				</td>
				<td>
					<webwork:property value="formattedBalance" />
				</td>
				<td>
					<webwork:if test="sessionContext.rights.canHaveExternalUsers">
						<a href="<webwork:url value="'/customerUserList.action'" >
						<webwork:param name="'target'" value="id" /></webwork:url>">
						External Users</a>	
					</webwork:if>									
				</td>
			</tr>
		</webwork:iterator>
	</table>
</div>



