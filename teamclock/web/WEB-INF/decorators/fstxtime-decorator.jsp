<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<%@ taglib uri="webwork" prefix="webwork" %>

<html>
    <head>
        <title>
    		<time:systemname /> - <decorator:title default="TeamClock.com" />
    	</title>
    	<link rel="stylesheet" type="text/css" href="<webwork:url value="'/css/general-aug.css'" />">
<script type="text/javascript" >
        var djConfig = {isDebug: false};
</script>        
<script language="javascript" src="dojo-0.4.1-ajax/dojo.js" ></script>

         <script type="text/javascript" src="<webwork:url value="'/js/dplib.js'" />"></script>  
        
	

	
	<decorator:head />
   	</head>
    <body >


<div id="shebang">

<div id="header">
	
	<div id="header_systemname">
		<h1><a class="cleanlink" href="<webwork:url value="'home.action'" />"><time:systemname /></a></h1>
	</div>
	<div id="header_links">

				<time:validSession>
						<p><webwork:property value="sessionContext.user.email" /> | 
						<webwork:if test="sessionContext.userTypeEnum.name != 'USERTYPE_EXTERNAL'">
							<a href="<webwork:url value="'userSettingsModify.action'" />">Account Settings</a> 
						</webwork:if>
						<webwork:else>
							<a href="<webwork:url value="'extPassword.action'" />">Change Password</a>
						</webwork:else>
						| <a  href="<webwork:url value="'help.action'"/>">Help</a> |
						<a href="<webwork:url value="'logout.action'" />">Logout</a></p>
				</time:validSession>
	</div>
	<div id="header_menu">

				<webwork:if test="sessionContext.externalUserSessionContext != null && !sessionContext.menuSection.name.equals(\"TIMECLOCK_ONLY\")">
						<jsp:include page="_menu_external.jsp" flush="true" />
				</webwork:if>
				<webwork:else>
							<jsp:include page="_menu.jsp" flush="true" />
				</webwork:else>
	</div>

</div>

<div id="primarycontent">		
		
		
			<webwork:if test="sessionContext.messagePresent">
				<div id="globalerrormessage">
					<p><webwork:property value="sessionContext.message" /></p>
				</div>
			</webwork:if>
			
			<decorator:body />
		
		
		
</div>
		
		
<div id="footer">
		

			<jsp:include page="_decorator_footer.jsp" flush="true" />

		
</div>

</div>





	</body>
</html>