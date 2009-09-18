<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ page isErrorPage='true' %>
<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<%@ taglib uri="webwork" prefix="webwork" %>

<html>
    <head>
        <title>
    		TeamClock.com
    	</title>
    	<link rel="stylesheet" type="text/css" href="css/general-aug.css">



        
	

   	</head>
    <body >

<div align="center">
<div id="shebang">

<div id="header">
	
	<div id="header_systemname">
		<h1>TeamClock.com</h1>
	</div>

	<div id="header_menu">
	<p>&nbsp;</p>
	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="submenu">
		<tr>
			<td><img src="<webwork:url value="'/images/submenuspacer-23.gif'"/>"/></td>
			<td width="100%" class="border_menu" align="center"></td>
		</tr>
	</table>
	</div>

</div>

<div id="primarycontent">		
		
		
			<webwork:if test="sessionContext.messagePresent">
				<div id="errormessage">
					<p><webwork:property value="sessionContext.message" />
				</div>
			</webwork:if>
			
<div align="center">
			<h1>There's an Error in the Web Form</h1>

			<p>It's probably a configuration error.</p>

			<p>Use your back button to return to the site you started from.</p>

			<p>Thanks!</p>


<%
	if (exception != null) {
		exception.printStackTrace();

	}
%>
</div>
		
</div>
		
		
<div id="footer">
		

<p>

Powered by <a href="http://www.teamclock.com">TeamClock.com</a>. Copyright &copy; 2006

<a href="http://www.fivesticks.com">Five Sticks, Inc.</a></p>
		
</div>

<div>
</div>

	</body>
</html>