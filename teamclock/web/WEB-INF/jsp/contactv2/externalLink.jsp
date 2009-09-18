<%@ taglib uri="webwork" prefix="webwork" %><webwork:set scope="page" name="destination" value="resultLocationURL" /><%
	response.sendRedirect((String)pageContext.getAttribute("destination"));
%>
