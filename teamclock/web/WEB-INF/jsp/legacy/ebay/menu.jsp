<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Auction Menu</title>
	</head>
	<body>
	
	<h2>Auction Menu</h2>

	<p><a href="<webwork:url value="'auctionListByFilter.action'" />">List All Items by Filter</a>

	<p><a href="<webwork:url value="'auctionCustomerList.action'" />">List All Customers<a/>
	
	<p><a href="<webwork:url value="'/auctionCustomerModify.action'" />">Add a Customer</a>
	
	<p>By Status:
	
	<ul>
	<webwork:iterator value="status">
		<li><a href="<webwork:url value="'auctionUpdateFilter.action'" ><webwork:param name="'targetStatus'" value="name" /><webwork:param name="'targetCustomerId'" value="0" /></webwork:url>"><webwork:property value="friendlyName" /></a>
		<a href="<webwork:url value="'auctionItemByStatus.action'" ><webwork:param name="'targetStatus'" value="name" /></webwork:url>">(By Cust)</a>
		</li>
	</webwork:iterator>
	</ul>
	
	

	
	<!--
	<p><a href="<webwork:url value="'auctionListAll.action'" ><webwork:param name="'type'" value="'b'" /></webwork:url>">List All Auction Items</a>
	-->
	
	<h2>Setup</h2>
	
	<p><a href="<webwork:url value="'auctionSetupBoxList.action'" />">Boxes</a>
	
	<p><a href="<webwork:url value="'auctionSetupFormsList.action'" />">Forms</a>
	
	<p><a href="<webwork:url value="'auctionSetupCommissionList.action'" />">Commissions</a>
	
	</body>
</html>