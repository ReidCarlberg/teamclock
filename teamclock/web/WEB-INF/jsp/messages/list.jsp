<%@ taglib uri="webwork" prefix="webwork" %>



<html>
	<head>
		<title>System Messages</title>
	</head>
	<body>
	
	<table width="100%">
		<tr>
			<td width="50%">
				<h2>System Messages</h2>
			<td>
			<td width="50%" align="right">
				<a href="<webwork:url value="'/messageModify.action'" />">Add a Message</a>
			</td>
		</tr>
	</table>
	
	<div id="listTable" >
	<table >
		<tr>
			<th>Name</th>
			<th>Subject</th>
			<th>&nbsp;</th>
		</tr>			
		<webwork:iterator value="messages" >
			<tr>
				<td><webwork:property value="name" /></td>
				<td><webwork:property value="subject" /></td>
				<td><a href="<webwork:url value="'/messageModify.action'"><webwork:param name="'target'" value="id" /></webwork:url>">Modify</a>
			</tr>		
		</webwork:iterator>

	</table>
	</div>		
			
			
	</body>
	
</html>