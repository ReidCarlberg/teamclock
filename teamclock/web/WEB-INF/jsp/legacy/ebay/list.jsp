<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Auction Items</title>
	</head>
	<body>
		<h2>Auction Items</h2>
<p> <a href="<webwork:url value="'auctionModify.action'" />" >+Add</a> | 
<a href="<webwork:url value="'auctionList.action'" ><webwork:param name="'type'" value="'a'"/></webwork:url>" >All</a> |
<a href="<webwork:url value="'auctionList.action'" ><webwork:param name="'type'" value="'b'"/></webwork:url>" >Ready To List</a> |
<a href="<webwork:url value="'auctionList.action'" ><webwork:param name="'type'" value="'c'"/></webwork:url>" >Started</a> |
<a href="<webwork:url value="'auctionList.action'" ><webwork:param name="'type'" value="'d'"/></webwork:url>" >Ended & Ready to Pay</a> |
<a href="<webwork:url value="'auctionList.action'" ><webwork:param name="'type'" value="'e'"/></webwork:url>" >Closed

<!--
<a href="<webwork:url value="'auctionListPrint.action'"/>">Printer Friendly</a>
-->
<p><webwork:property value="customerModifyContext.targetCustomer.name" /> - <webwork:property value="listType.name" />


<jsp:include page="_auctionItems.jsp" flush="true" />
		

	</body>
</html>