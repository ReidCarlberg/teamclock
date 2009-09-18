<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Contact Detail</title>

<style>
.smallLabel {
	font-size: 8px;
	color: #999;
}
.leftLabel {
	vertical-align: top;
	font-size: 10px;
	color: #999;
	width: 100;
	text-align:right;
}

.topLabel {
	font-size: 10px;
	color: #999;
	width: 100;
	text-align:center;
}

input, select, textarea {
	font-size: 9pt;
}

h3 {
	padding: 5px 0px 0px 0px;
	margin: 0px;
}

#contactLeft, #contactRight {
	width: 48%;
	float: left;
}

#settings {
	width: 30%;
	position: absolute;
	top:125px;
	left: 68%;
}
</style>

	</head>
	<body>
	
	<table width="100%">
		<tr>
			<td width="50%">
		<h2><webwork:property value="targetContactV2.nameFormatted" /></h2>
			</td>
			<td width="50%" align="right">
			
<p><a href="<webwork:url value="'/contactmodifyv2.action'" />">Modify</a> | 
<a href="<webwork:url value="'/contactdetailv2Print.action'" />">Printer Friendly</a> |
<a href="<webwork:url value="'/contactmodifyv2.action'" ><webwork:param name="'target'" value="'new'" /></webwork:url>">Add Contact</a>
</p>

			</td>
		</tr>
	</table>
<jsp:include page="_contactv2.jsp" flush="true" />


	</body>
</html>