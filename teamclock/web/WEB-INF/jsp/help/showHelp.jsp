<%@ taglib uri="webwork" prefix="webwork" %>



<html>
	<head>
		<title>FSTX Time Help</title>
	</head>
	<body>
		<h1>Help</h1>

		<webwork:if test="back != null">
			<p><a href="<webwork:url value="back" />" >Go back</a> .
		</webwork:if>
		
		<p>
		<webwork:if test="file != null">
			<webwork:include value="file" />
		</webwork:if>
		<webwork:else>
			<webwork:include value="'index.jsp'" />
		</webwork:else>
		
		<p>Have a suggestion? Email us! <a href="mailto:service@fivesticks.com">service@fivesticks.com</a>
		
	</body>
</html>