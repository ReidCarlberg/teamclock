<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Lookups</title>
	</head>
	<body>
		<h2>Lookups</h2>
		
		<webwork:form method="'post'" action="'/lookupsList.action'" >

		<webwork:select label="'Show Lookup Type'" name="'target'" value="listContext.targetType.name"
			list="lookupTypes" listKey="name" listValue="friendlyName" emptyOption="false"   onchange="'form.submit();'" />
		
		<!--
		<webwork:submit name="'submitList'" value="'Go'" />		
		-->
		
		</webwork:form>

		<p><a href="<webwork:url value="'/lookupModify.action'" />">Add</a>

<div id="listTable" >		
		<table>
			<tr>
				<th>Short</th>
				<th>Full</th>
				<th></th>
			</tr>
			<webwork:iterator value="lookups">
				<tr>
					<td><webwork:property value="shortName" /></td>
					<td><webwork:property value="fullName" /></td>
					<td><a href="<webwork:url value="'/lookupModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">Modify</a></td>
				</tr>
			</webwork:iterator>
		</table>
</div>		
		

	</body>
</html>