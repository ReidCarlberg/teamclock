<%@ taglib uri="/WEB-INF/webwork.tld" prefix="webwork" %>

 

<html>

<head>
<meta http-equiv="Refresh" content="3;URL=<webwork:url value="action" />">
</head>

<body>

<div align="center">

<table width="500"><tr><td width="100%">

 
<webwork:if test="message == null">
	<p>display is null</p>
</webwork:if>
<webwork:else>
	 
       
	<p><h2><webwork:property value="message"/> </h2></p>

	 
</webwork:else>
<p> <a href="<webwork:url value="'timeclockOnly.action'" />">Return to the Time Clock</p>


</td></tr></table>
</div>

</body></html>



