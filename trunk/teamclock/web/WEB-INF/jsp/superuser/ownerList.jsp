<%@ taglib uri="webwork" prefix="webwork" %>





<html>
	<head>
		<title>Owner List</title>
	</head>
	<body>
		<h1>Owner List</h1>

<div id="listFilter" >	
<webwork:form method="'post'" action="'ownerList.action'">	


<table >
	<tr>

		<th>Name</th>
		<th>Contact Name</th>
		<th>Email</th>
		<th>Key</th>
		<th>Account</th>
		<th>Billing</th>				
		<th>Return</th>
		<th>Go</th>
	</tr>
	<tr>

		<td>
			<webwork:textfield theme="'simple'" name="'params.name'" 
				value="params.name" 
				size="'20'" maxlength="'20'" />
		
		</td>
				<td>
			<webwork:textfield theme="'simple'" name="'params.contactName'" 
				value="params.contactName" 
				size="'20'" maxlength="'20'" />
		
		</td>
		<td>
			<webwork:textfield theme="'simple'" name="'params.contactEmail'" 
				value="params.contactEmail" 
				size="'20'" maxlength="'20'" />
		
		</td>
		<td>
			<webwork:textfield theme="'simple'" name="'params.keyLike'" 
				value="params.keyLike" 
				size="'20'" maxlength="'20'" />
		</td>
		<td>
			<webwork:textfield theme="'simple'" name="'params.account'" 
				value="params.account" 
				size="'20'" maxlength="'20'" />
		</td>
		<td>
			<webwork:textfield theme="'simple'" name="'params.billingFrequency'" 
				value="params.billingFrequency" 
				size="'20'" maxlength="'20'" />
		</td>
		<td>
			<webwork:select theme="'simple'" label="'Complete'" name="'params.maxResults'" 
			value="params.maxResults"
			list="maximums" listKey="key" listValue="value" emptyOption="false" />	
		
		</td>
		<td>
			<webwork:submit theme="'simple'" value="'Search'" name="'search'" />	
<webwork:submit theme="'simple'" value="'Reset'" name="'reset'" />


		</td>
	</tr>
</table>

</webwork:form>	
</div>


<div id="listTable" >
		<table >
		<tr>
			<th>Contact Name</th>
			<th>Email</th>
			<th>Name</th>
			<th>Key</th>
			<th>Account</th>
			<th>Billing</th>
			<th>Expiration</th>
			<th>Actions</th>
		</tr>
		<webwork:iterator value="systemOwners">
			<tr>

				<td>
					<a href="<webwork:url value="'/systemOwnerModify.action'"><webwork:param name="'target'" value="key" /></webwork:url>"><webwork:property value="contactName" /></a>

				</td>
				<td>
					<a href="mailto:<webwork:property value="contactEmail" />"><webwork:property value="contactEmail" /></a>

				</td>
				<td><webwork:property value="name" /></td>
				<td><webwork:property value="key" /></td>
				<td><webwork:property value="account" /></td>
				<td><webwork:property value="billingFrequency" /></td>
				<td><webwork:property value="expirationDate" /></td>
				<td>
					<webwork:if test="!activated">
						<a href="<webwork:url value="'/ownerActivate.action'"><webwork:param name="'target'" value="id" /></webwork:url>">Activate</a>
					</webwork:if>
					<webwork:else>
						<a href="<webwork:url value="'/ownerDeactivate.action'"><webwork:param name="'target'" value="id" /></webwork:url>">Deactivate</a>
					</webwork:else>
					<a href="<webwork:url value="'/ownerUserList.action'"><webwork:param name="'target'" value="id" /></webwork:url>">List Users</a>
					<a href="<webwork:url value="'/systemOwnerModifyAccountSetting.action'"><webwork:param name="'target'" value="id" /></webwork:url>">Edit Account Settings</a>
					<a href="<webwork:url value="'/ownerMetrics.action'"><webwork:param name="'target'" value="id" /></webwork:url>">Metrics</a>
					<a href="<webwork:url value="'/systemOwnerModify.action'"><webwork:param name="'target'" value="key" /></webwork:url>">Modify</a>
				
				
				
				</td>
			</tr>		
		</webwork:iterator>
		</table>
</div>		

	</body>
</html>