<?xml version="1.0" encoding="utf-8"?>
<%@ taglib uri="webwork" prefix="webwork" %>
<%@ taglib uri="wellformedAJAX" prefix="ajax"%>
<%response.setHeader("Content-Type", "text/xml; charset=iso-8859-1");
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        %>
<teamclock xmlns="http://my.teamclock.com">
	<todo>
		<result><webwork:property value="result"/></result>
		<verboseResult><webwork:property value="verboseResult"/></verboseResult>
		<webwork:if test="listResults != null" >
			<todoItems>
				<webwork:iterator value="listResults">
					<todoItem>
						<id><webwork:property value="id" /></id>
						<projectId><webwork:property value="projectId" /></projectId>
						<projectKey><ajax:wellformed><webwork:property value="projectName" /></ajax:wellformed></projectKey>
						<customerName><ajax:wellformed><webwork:property value="customerName" /></ajax:wellformed></customerName>
						<dateCreated><webwork:property value="createTimestamp" /></dateCreated>
						<dateDeadline><webwork:property value="deadlineTimestamp" /></dateDeadline>
						<dateFriendlyDeadline><webwork:property value="friendlyDeadline" /></dateFriendlyDeadline>
						<detail><ajax:wellformed><webwork:property value="detail" /></ajax:wellformed></detail>
						<enteredByUser><webwork:property value="enteredByUser" /></enteredByUser>
						<externalUser><webwork:property value="externalUser" /></externalUser>
						<priority><webwork:property value="priority" /></priority>
						<tag><ajax:wellformed><webwork:property value="tag" /></ajax:wellformed></tag>
					</todoItem>
				</webwork:iterator>
			</todoItems>
		</webwork:if>
	</todo>
</teamclock>