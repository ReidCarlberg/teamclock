<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Contact Detail</title>

<style>

.leftLabel {
	vertical-align: top;
	font-size: 10px;
	color: #999;
	width: 100;
	text-align:right;
}




h3 {
	padding: 5px 0px 0px 0px;
	margin: 0px;
}

#contactLeft, #contactRight {
	width: 48%;
	float: left;
}
</style>

	</head>
	<body>
		<h2><webwork:property value="targetContactV2.nameFormatted" /></h2>

<jsp:include page="_contactv2.jsp" flush="true" />


	</body>
</html>