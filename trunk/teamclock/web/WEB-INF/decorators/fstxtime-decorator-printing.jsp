<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>

<%@ taglib uri="fstxtime" prefix="time" %>

<%@ taglib uri="webwork" prefix="webwork" %>


<html>
    <head>
        <title>
    		<decorator:title /> 
    	</title>
    	<link rel="stylesheet" type="text/css" href="<webwork:url value="'/css/general-aug.css'" />">
        <decorator:head />
        
    </head>
    <body>
	<div id="header_print_systemname">
		<h1 align="right"><a class="cleanlink" href="<webwork:url value="'home.action'" />"><time:systemname /></a></h1>
	</div>

<decorator:body />


</body>
</html>