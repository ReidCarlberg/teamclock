<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Task List</title>
	</head>
	<body>

	<table>
	<tr><td>
	<h2>Task Types</h2>
	</td><td>
	<p><a href="<webwork:url value="'/taskmodify.action'" />">+Add</a>	
	</td></tr>
	</table>
	
<div id="listTable" >	
	<table>
		<webwork:iterator value="tasks" >
			<tr>
				<td>
					<a href="<webwork:url value="'/taskmodify.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					<webwork:property value="nameShort" /></a>
				</td>
			</tr>
		</webwork:iterator>
	</table>
</div>

	</body>
</html>