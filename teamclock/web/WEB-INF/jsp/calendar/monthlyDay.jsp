<%@ taglib uri="webwork" prefix="webwork" %>


<head>
<title>Monthly Calendar</title>
</head>
<body>

<table width="100%">
<tr><td width="70%" class="bottom">
<table>
<tr>
<td class="bottom"><a href="<webwork:url value="'/calendarMonthly.action'" >
	<webwork:param name="'targetDate'" value="targetDatePrevious" /></webwork:url>"><webwork:property value="monthNamePrevious" /></a>
</td>
<td class="bottom"><h1><webwork:property value="monthName" /> </h1></td>
<td class="bottom"><a href="<webwork:url value="'/calendarMonthly.action'" >
	<webwork:param name="'targetDate'" value="targetDateNext" /></webwork:url>"><webwork:property value="monthNameNext" /></a>
</td>

<td class="bottom"><a href="<webwork:url value="'/calendarMonthly.action'" >
	<webwork:param name="'targetDate'" value="actualDate" /></webwork:url>">(This Month)</a>
</td>


</tr>
</table>
</td><td width="30%" class="bottom" align="right">
	<a href="<webwork:url value="'/calendarMonthlyPrint.action'" />">Printer Friendly</a>
</td>
</tr>
</table>



	      
<jsp:include page="_month.jsp" flush="true" />
	     
    
</body>
 
</html>