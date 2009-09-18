<?xml version="1.0" encoding="ISO-8859-1"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="fstxtime" prefix="time" %>
<%@ taglib uri="wellformedAJAX" prefix="ajax" %>


<%
response.setHeader("Content-Type","text/xml; charset=iso-8859-1"); 
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<ajax-response>
	<response type="object" id="ToDoAutoCompleter">
		<webwork:iterator value="autoCompleteOptions">
			<option longName="<webwork:property value="project.longName"/>" shortName="<webwork:property value="project.shortName"/>"
			 user="<webwork:property value="user.username"/>"/>
		</webwork:iterator>
		
	
	</response>
</ajax-response>
		