<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>


<%@ taglib uri="fstxtime" prefix="time" %>
<%@ taglib uri="webwork" prefix="webwork" %>

<html>
    <head>
        <title>
    		<time:systemname /> - <decorator:title default="fstxTIME" />
    	</title>
    	<link rel="stylesheet" type="text/css" href="<webwork:url value="'/css/general-aug.css'" />">
<script type="text/javascript" >
        var djConfig = {isDebug: false};
</script>        
<script language="javascript" src="dojo031-ajax/dojo.js" ></script>
<script type="text/javascript">
	dojo.require('dojo.debug');
</script>

        <decorator:head />
        
<style>
#registrationSpacer {
	height: 35px;	
}
</style>
    </head>
    <body>

<div align="center">
<div id="shebang">

<div id="header">
	

	<div id="header_systemname">
		<h1><a class="cleanlink" href="<webwork:url value="'home.action'" />"><time:systemname /></a></h1>
	</div>
	<div id="registrationSpacer"></div>
	<div id="header_menu">

		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="submenu">
		<tr>
			<td><img src="<webwork:url value="'/images/submenuspacer-23.gif'"/>"/></td>
			<td width="100%" class="border_menu" align="center"></td>
		</tr>
	</table>
	</div>

</div>

<div id="primarycontent" align="center">		
		
<div id="registration">
			<webwork:if test="sessionContext.messagePresent">
				<div id="errormessage">
					<p><webwork:property value="sessionContext.message" />
				</div>
			</webwork:if>
			
			<decorator:body />
</div>
		
		
</div>
		
		
<div id="footer">
		

			<jsp:include page="_decorator_footer.jsp" flush="true" />

		
</div>

<div>
</div>


	</body>
</html>