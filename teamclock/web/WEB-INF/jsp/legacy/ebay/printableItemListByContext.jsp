<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Auction Items</title>
	</head>
	<body>
		<h2>Auction Items</h2>

		<webwork:if test="customerModifyContext != null">
			<p><webwork:property value="customerModifyContext.targetCustomer.name" />
		</webwork:if>
		
		<jsp:include page="_auctionItems.jsp" flush="true" />
				
		<jsp:include page="_auctionItemStats.jsp" flush="true" />

	</body>
</html>