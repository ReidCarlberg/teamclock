<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Superuser Dashboard</title>
	</head>
	<body>

		<h1>Superuser Dashboard</h1>

		<p><a href="<webwork:url value="'/mostActive.action'" />">Most Active System Owners</a>
		
		<p><a href="<webwork:url value="'/ownerList.action'" />">List Owners</a>
		
		<p><a href="<webwork:url value="'/rawUserList.action'" />">Raw User List</a>

		<p><a href="<webwork:url value="'/loginHistoryStats.action'" />">Login History Stats</a>
		
		<p><a href="<webwork:url value="'/sendSystemOwnerMessage.action'" />">E-Mail All System Owners</a>
		
		
		
		<%--
		<hr>
		
		<p><a href="<webwork:url value="'/auctionHome.action'" />">Auction</a>
		<p><a href="<webwork:url value="'/auctionListByFilter.action'" />">Auction Filter</a>
		<p><a href="<webwork:url value="'/auctionCustomerList.action'" />">Auction Customers</a>
		--%>
		
	</body>
</html>