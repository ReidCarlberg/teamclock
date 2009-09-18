<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>
<html>
<head>
<title>Dashboard</title>
<!--
		<meta http-equiv="Refresh" content="120;URL=<webwork:url value="'home.action'" />">
		-->

<link rel="stylesheet" type="text/css"
	href="<webwork:url value="'/css/autoComplete.css'" />">
<script type="text/javascript"
	src="<webwork:url value="'/js/prototype.js'" />"></script>
<script type="text/javascript"
	src="<webwork:url value="'/js/rico.js'" />"></script>
<script type="text/javascript"
	src="<webwork:url value="'/js/todoautocompleter.js'" />"></script>
<script type="text/javascript"
	src="<webwork:url value="'/js/log.js'" />"></script>
<script type="text/javascript"
	src="<webwork:url value="'/js/dashboardScript.js'" />"></script>
	

</head>
<body>


<script>
<%-- Call these when the body load --%>
  onloads.push( registerAjaxStuff );
  onloads.push( getUpdateTimeClock );
  onloads.push( getUpdateCalendar );
  onloads.push( getUpdateActivity );
  onloads.push( getUpdateToDo );
  onloads.push( setUpActivityRefreashTimeout);  
  onloads.push( runAutoRefresh ); 
 

   
   
</script>

<!-- 
<div id="log" style="display:none;background-color : #DDDDDD;height:50;width:100%;overflow:auto;"></div>
<a href="#n" onClick=toggleLogging();>Log</a>
 -->
 <!--
<div id="toDoToActivitySuccessDiv" ></div>
-->



<table width="100%" border="0">
<time:userIsPrivileged>
	<webwork:if	test="dashboardContext.showingTimeClockStatus">
		<tr>
			<td colspan="3" class="dashboard_header" valign="top"
				bgcolor="#D6DEEC">
				<div id="timeClockStatusDiv" style="display:block; "></div>
			</td>
		</tr>
	</webwork:if>
</time:userIsPrivileged>



	<tr>
		<td width="20%" valign="top" bgcolor="#D6DEEC" align="center"><webwork:include
			value="'_schedulePanel.jsp'" /></td>
		<td width="50%" valign="top" bgcolor="#95B3DE" align="center"><webwork:include
			value="'_todoPanel.jsp'" /></td>
		<td width="30%" valign="top" bgcolor="#D6DEEC" align="center"><webwork:include
			value="'_activityPanel.jsp'" /></td>
	</tr>
</table>

<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["todo"].elements["detail"];

  if (focusControl.type != "hidden") {
     focusControl.focus();
  }
  // -->
</script>

</body>


</html>
