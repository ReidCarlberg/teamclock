<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Teams</title>
	</head>
	<body>
		<h2>Teams</h2>
		

		<p><a href="<webwork:url value="'/teamModify.action'" />">Add</a>

<div id="listTable" >
		
		<table border="1" cellpadding="5" style="border-collapse: collapse;">
			<tr>
				<th>Id</th>
				<th>Short Name</th>
				<th>Name</th>
				<th>&nbsp;</th>
				
			</tr>
			<webwork:iterator value="teams">
				<tr>
					<td>
						<a href="<webwork:url value="'/teamModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>"></a>
						<webwork:property value="id" />
					</td>
					<td><webwork:property value="nameShort" /></td>
					<td><webwork:property value="name" /></td>
					<td>
						<a href="<webwork:url value="'/teamModifyMembers.action'"><webwork:param name="'target'" value="id" /></webwork:url>">
						Members</a>
					</td>
				</tr>
			</webwork:iterator>
		</table>
		
</div>

	</body>
</html>