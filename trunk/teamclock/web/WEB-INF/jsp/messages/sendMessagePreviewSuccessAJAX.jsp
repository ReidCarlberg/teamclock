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
	<response type="element" id="messagePreviewAJAXDiv">
	
		<table>
			<tr>
				<td>Name</td>
				<td>
					<ajax:wellformed><webwork:property value="message.name"/></ajax:wellformed><br/>
				</td>
			</tr>
			<tr>
				<td>Subject</td>
				<td>
					<ajax:wellformed><webwork:property value="message.subject"/></ajax:wellformed><br/>
				</td>
			</tr>
			<tr>
				<td valign="top">Message</td>
				<td>
				<pre><ajax:wellformed><webwork:property value="message.message"/></ajax:wellformed><br/>
				</pre>
				</td>
			</tr>
		</table>
		</response>
</ajax-response>
		