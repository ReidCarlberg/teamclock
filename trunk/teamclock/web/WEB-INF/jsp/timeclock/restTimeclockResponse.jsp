<?xml version="1.0" encoding="utf-8"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%response.setHeader("Content-Type", "text/xml; charset=iso-8859-1");
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        %>
<teamclock xmlns="http://my.teamclock.com">
	<timeclock>
		<result><webwork:property value="result"/></result>
		<verboseResult><webwork:property value="verboseResult"/></verboseResult>
		<currentStatus><webwork:property value="currentStatus"/></currentStatus>
		<localizedTime><webwork:property value="localizedTime"/></localizedTime>
		<roundedLocalizedTime><webwork:property value="roundedLocalizedTime"/></roundedLocalizedTime>
		<shiftTime><webwork:property value="shiftTime" /></shiftTime>
	</timeclock>
</teamclock>