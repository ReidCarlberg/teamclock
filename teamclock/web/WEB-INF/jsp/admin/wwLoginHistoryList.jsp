<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>User List</title></head><body>





<h2>Login History</h2>


<div id="listFilter" >	
	<webwork:form theme="'simple'" action="'loginHistoryList.action'" method="'post'">
	
		<table >
			<tr>
				<webwork:if test="sessionContext.featureSuperUser">
					<td>
					Owner:
						<webwork:select theme="'simple'" label="'System Owner'" name="'params.ownerKeyForSuperUser'" value="params.ownerKeyForSuperUser"
						list="systemOwners" listKey="key" listValue="name" emptyOption="true"  />
					</td>
				</webwork:if>					
				<td>
					User: 
						<webwork:select theme="'simple'" label="'User'" name="'params.username'" value="params.username"
						list="users" listKey="key" listValue="value" emptyOption="true"  />
				</td>
				<td>
					Start: <webwork:textfield theme="'simple'" label="'Start'" name="'params.dateFrom'" value="params.dateFrom" />
				</td>
				
				<td>
					End: <webwork:textfield theme="'simple'" label="'Stop'" name="'params.dateTo'" value="params.dateTo" />
				</td>
		
				<td>
					<webwork:submit theme="'simple'" value="'Search'" name="'search'" />
					
				</td>
			</tr>
		</table>
	</webwork:form>
</div>
<p>



<div id="listTable" >
<table border="1" cellpadding="5" style="border-collapse: collapse;">
<tr>

 




<webwork:if test="sessionContext.featureSuperUser">
	<th>
	Owner
	</th>
</webwork:if>	

<th>Username</th>
<th>Type</th>
<th>Timestamp</th>
<th>Location</th>

</tr>
<webwork:iterator value="list">
	<tr>
		<webwork:if test="sessionContext.featureSuperUser">
			<td>
				<webwork:property value="ownerKey" /></a>
			</td>
		</webwork:if>		
		<td>
			<webwork:property value="username" /></a>
		</td>
		<td>
			<webwork:property value="type" />
		</td>
		<td>
			<webwork:property value="formattedTimestamp" />
		</td>
		<td>
			<webwork:property value="location" />
		</td>		
	</tr>

</webwork:iterator>


</table>

</div>

</body>
</html>