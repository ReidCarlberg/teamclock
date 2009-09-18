<?xml version="1.0" encoding="utf-8"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>
<%response.setHeader("Content-Type", "text/xml; charset=iso-8859-1");
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        %>
<rss version="2.0">
	<channel>
		<title>TeamClock.com ToDo Summary - <webwork:property value="authenticationBasedServiceSupport.sessionContext.systemOwner.name" /></title>
		<link>http://my.teamclock.com/?target=<webwork:property value="authenticationBasedServiceSupport.sessionContext.systemOwner.key" /></link>
		<description>Top priority to do list.</description>
		<language>en-us</language>
				<webwork:iterator value="listResults">
					<item>
						<title>
							<ajax:wellformed><webwork:property value="customerName" /></ajax:wellformed> - 
							<ajax:wellformed><webwork:property value="projectName" /></ajax:wellformed> - 
							<ajax:wellformed><webwork:property value="detailShort" /></ajax:wellformed>
						</title>
						<description><ajax:wellformed><webwork:property value="detailFull" /></ajax:wellformed></description>
					</item>
				</webwork:iterator>
</channel>
</rss>
