<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Contacts</title>
	</head>
	<body>
	<table width="100%">
	<tr>
		<td width="50%">
	<h2>Contact List</h2>
	</td>
	<td width="50%" align="right">
	<p><a href="<webwork:url value="'/contactmodifyv2.action'" ><webwork:param name="'target'" value="'new'" /></webwork:url>">Add Contact</a></p>
	</td>
	</tr>
	</table>
	
<div id="listFilter" >	
<webwork:form method="'post'" action="'contactlistv2.action'">	


<table >
	<tr>
		<th>Name</th>
		<th>Organization</th>
		<th>City</th>
		<th>Modified</th>
		<th>Class</th>
		<th>Source</th>
		<th>Interest</th>		
		<th>Go</th>
	</tr>
	<tr>
		
		<td>
			<webwork:textfield theme="'simple'" name="'params.nameFormattedLike'" 
				value="contactV2ListContext.params.nameFormattedLike" size="'20'" maxlength="'20'" />
		</td>

		<td>
			<webwork:textfield theme="'simple'" name="'params.organizationNameLike'" 
				value="contactV2ListContext.params.organizationNameLike" size="'20'" maxlength="'20'" />
		</td>

		<td>
			<webwork:textfield theme="'simple'" name="'params.organizationCityLike'" 
				value="contactV2ListContext.params.organizationCityLike" size="'20'" maxlength="'20'" />
		</td>
		<td>
				<webwork:textfield size="'9'" theme="'simple'" label="'Start'" name="'params.modifyDateRangeStart'" value="params.modifyDateRangeStart" />
<a href="#" onclick="displayDatePicker('params.modifyDateRangeStart');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
			
				
				<webwork:textfield size="'9'" theme="'simple'" label="'Stop'" name="'params.modifyDateRangeStop'" value="params.modifyDateRangeStop" />
<a href="#" onclick="displayDatePicker('params.modifyDateRangeStop');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
		</td>

		<td>
			<webwork:select theme="'simple'" label="'Class'" name="'params.contactClassLuId'" value="params.contactClassLuId"
				list="classLookups" listKey="id" listValue="fullName" 
				emptyOption="true"   />
		</td>	
		<td>
			<webwork:select theme="'simple'" label="'Source'" name="'params.contactSourceLuId'" value="params.contactSourceLuId"
				list="sourceLookups" listKey="id" listValue="fullName" 
				emptyOption="true"   />
		</td>		
		<td>
			<webwork:select theme="'simple'" label="'Interest'" name="'params.contactInterestLuId'" value="params.contactInterestLuId"
				list="interestLookups" listKey="id" listValue="fullName" 
				emptyOption="true"   />
		</td>		

		<td>
			<webwork:submit theme="'simple'" value="'Search'" name="'searchContacts'" />	

			<webwork:submit theme="'simple'" value="'Reset'" name="'resetContacts'" />	

		</td>
	</tr>
</table>

</webwork:form>	
</div>	


<div id="listTable" >
	<table cellpadding="5" border="1" style="border-collapse: collapse;">
		<tr>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Organization</th>
			<th>Email</th>
			<th>Phone</th>
			<th>&nbsp;</th>

		</tr>
		<webwork:iterator value="contacts" >
			<tr>

				<td>
					<webwork:property value="nameLast" />

				</td>
				<td>
					<webwork:property value="nameFirst" />

				</td>
				<td>
					<webwork:property value="organizationName" />
				</td>
				<td>
					<a href="mailto:<webwork:property value="organizationEmail" />"><webwork:property value="organizationEmail" /></a>
				</td>
				<td>
					<webwork:property value="organizationPhone" />
				</td>								
				<td>
					<a href="<webwork:url value="'/contactdetailv2.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					View</a> | 
					<a href="<webwork:url value="'/contactdetailv2Print.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					Print View</a>
					<webwork:if test="associate">
						| <a href="<webwork:url value="'/contactassociatev2.action'" >
						<webwork:param name="'targetContactId'" value="id" /></webwork:url>">
						Associate</a>					
					</webwork:if>
					
				</td>

			</tr>
		</webwork:iterator>
	</table>
</div>
	
	
	</body>
	
	</html>
	
	
	
	