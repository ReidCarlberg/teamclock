<%@ taglib uri="/WEB-INF/webwork.tld" prefix="webwork" %>

 

<html>

<head>
<meta http-equiv="Refresh" content="15;URL=<webwork:url value="action" />">
<title>Pay Period Summary</title>
</head>


<body>


<h2>Pay period summary.</h2>

<jsp:include page="_payPeriodDetail.jsp" flush="true" />


<p>This page times out after 15 seconds.  Use the regular login to view your full report.


</body></html>



