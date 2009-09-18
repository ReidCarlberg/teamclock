<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Customer Auction Items</title>
	</head>
	<body>

	<webwork:iterator value="custItems">
	
		<h2><webwork:property value="customer.name" /> - <webwork:property value="itemStatusType.friendlyName" /></h2>		
		
		<jsp:include page="_auctionItemsSimple.jsp" flush="true" />
				
		<jsp:include page="_auctionItemStats.jsp" flush="true" />

	</webwork:iterator>
	
	</body>
</html>