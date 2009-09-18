<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
	<head>
		<title>Active Account Transactions</title>
	</head>
	<body>
		<h2>Active Account Transactions</h2>
<p> <a href="<webwork:url value="'accttxnModify.action'" />" >+Add</a> | 

<a href="<webwork:url value="'accttxnListPrint.action'"/>">Printer Friendly</a> |

<a href="<webwork:url value="accountTransactionContext.backAction"/>">Back</a>

<jsp:include page="_transactions.jsp" flush="true" />
		

	</body>
</html>