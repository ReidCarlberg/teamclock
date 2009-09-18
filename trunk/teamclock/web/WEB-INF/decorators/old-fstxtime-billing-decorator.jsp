<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="webwork" prefix="webwork" %>

<%@ page import="com.fivesticks.time.settings.FstxTimeSettings" %>


<html>
    <head>
        <title>
    		<%= FstxTimeSettings.getSystemName() %> - <decorator:title default="fstxTIME" />
    	</title>
    	<link rel="stylesheet" type="text/css" href="/fstxtime/css/general.css">
    	
        <decorator:head />
        
    </head>
    <body>

<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<tr><td align="center" valign="top" width="100%" height="85%">

<table width="100%" cellpadding="0" cellspacing="0"><tr><td width="100%" valign="top">

<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<td width="50%" valign="bottom">

<img src="<%= FstxTimeSettings.getLogoURL() %>" border="0">


</td>
<td width="50%" align="right">

<h2><a class="header_system_name" href="<webwork:url value="'/home.action'" />"><%= FstxTimeSettings.getSystemName() %></a></h2>


</td>
</tr>

</table>




<decorator:body />


</body>
</html>