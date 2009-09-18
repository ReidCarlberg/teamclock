<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>

<%@ taglib uri="fstxtime" prefix="time" %>

<%@ taglib uri="webwork" prefix="webwork" %>


<html>
    <head>
        <title>
    		<decorator:title /> 
    	</title>
    	<link rel="stylesheet" type="text/css" href="<webwork:url value="'/css/general.css'" />">
        <decorator:head />
        
    </head>
    <body>
<p align="right"><img src="<time:logourl />" border="0">


<decorator:body />


</body>
</html>