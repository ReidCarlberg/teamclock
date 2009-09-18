<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>


<html>
	<head>
		<title>Modify Account Transaction</title>
	</head>
	<body>
		<h2>Modify Account Transaction</h2>

		<p><webwork:property value="accountTransactionContext.fstxCustomer.name" />
		
		
		<webwork:form method="'post'" action="'accttxnModify.action'" >

		<webwork:select label="'Type'" name="'accountTransaction.type'"	value="accountTransaction.type"
			list="types" listKey="name" listValue="name" emptyOption="true" />
			
				<tr>
				<td align="right" class="label">Date:</td>
				<td>
				<webwork:textfield theme="'simple'" label="'Date'" name="'dateString'" value="dateString" />
<a href="#" onclick="displayDatePicker('dateString');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
				</td></tr>

		<webwork:textfield label="'Amount'" name="'accountTransaction.amount'" value="accountTransaction.amount" />
			
		<webwork:textarea label="'Comments'" name="'accountTransaction.comments'" value="accountTransaction.comments" rows="5" cols="50" />

		<webwork:select label="'Entered By'" name="'accountTransaction.enteredBy'"	value="accountTransaction.enteredBy"
			list="users" listKey="username" listValue="username" emptyOption="true" />
		
		<webwork:submit value="'Save'" name="'submitTxn'" />

		<webwork:submit value="'Cancel'" name="'cancelTxn'" />

		<webwork:if test="modifyAccountTransactionContext.accountTransaction != null">
			<webwork:submit value="'Delete'" name="'deleteTxn'" />
		</webwork:if>		
		
		</webwork:form>
		
	</body>
</html>