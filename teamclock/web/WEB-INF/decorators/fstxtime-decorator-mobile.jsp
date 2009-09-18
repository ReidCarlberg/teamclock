<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@ taglib uri="sitemesh-decorator" prefix="decorator" %>
<%@ taglib uri="fstxtime" prefix="time" %>

<html>
    <head>
        <title>
    		<time:systemname /> - <decorator:title default="TeamClock.com" />
    	</title>
   	</head>
    <body >

			
			<decorator:body />
		
		

	</body>
</html>