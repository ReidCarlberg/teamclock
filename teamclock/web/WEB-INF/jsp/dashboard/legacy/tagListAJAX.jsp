<?xml version="1.0" encoding="ISO-8859-1"?>
<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>


<%response.setHeader("Content-Type", "text/xml; charset=iso-8859-1");
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        %>
<ajax-response>
<response type="element" id="tagListAJAXDiv"> 

		<webwork:form method="'post'"  theme="'simple'" action="'homeFilter.action'">
			<webwork:select theme="'simple'" headerValue="'[ALL]'" headerKey ="''" 
				 name="'toDoTagFilter'"
				value="dashboardContext.todoFilter.tagFriendly" list="tags"
				onchange="'form.submit();'" cssClass="'dashboard_header_drop'" />
		</webwork:form>

</response>
</ajax-response>

