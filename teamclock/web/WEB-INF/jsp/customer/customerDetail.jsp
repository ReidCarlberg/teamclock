<%@ taglib uri="webwork" prefix="webwork"%>


<html>
<head>
<title>Customer Detail</title>


</head>
<body>
<h2>Customer Detail</h2>



<table width="100%">
	<tr>
		<td width="50%" valign="top">
		<p><a
	href="<webwork:url value="'/customermodify.action'">
					<webwork:param name="'target'" value="customer.id" />
					</webwork:url>">Edit</a>

			<p><strong><webwork:property value="customer.name" /></strong><br/>
			<webwork:property value="customer.street1" /><br/>
			<webwork:property value="customer.street2" /><br/>
			<webwork:property value="customer.city" /><webwork:if test="customer.state != null">, 
			<webwork:property value="customer.state" /> <webwork:property value="customer.zip" /></webwork:if><br/>
			<webwork:property value="customer.country" /></p>
			
			<p>Status: <webwork:property
			value="customerModifyContext.displayCustomer.status.fullName" /></p>

		<p><a
			href="<webwork:url value="'/viewCustomerTransactions.action'" >
		<webwork:param name="'target'" value="customer.id" /></webwork:url>">
		Time Account Transactions</a> (Current balance: <webwork:property
			value="customerModifyContext.timeAccountBalance" />)</p>



		</td>
		<td width="50%" valign="top">



		<p>Contacts (<a
			href="<webwork:url value="'/contactmodifyv2.action'">
							<webwork:param name="'target'" value="'add'" />
							</webwork:url>">Add New</a> - <a
			href="<webwork:url value="'/contactlistv2.action'">
							<webwork:param name="'associate'" value="'true'" />
							</webwork:url>">Associate w/ Contact</a>)</p>


		
		<div id="listTable">
		<table>
			<tr>
				<th>Name</th>
				<th>Phone</th>
				<th>Email</th>
			</tr>
			<webwork:iterator value="customerModifyContext.contacts">
				<tr>
					<td><a
						href="<webwork:url value="'/contactdetailv2.action'">
								<webwork:param name="'target'" value="id" />
								</webwork:url>"><webwork:property
						value="nameFirst" /> <webwork:property value="nameLast" /></a></td>
					<td><webwork:property value="organizationPhone" /></td>
					<td><a href="mailto:<webwork:property value="organizationEmail"/>"> <webwork:property
						value="organizationEmail" /></a></td>

				</tr>

			</webwork:iterator>
		</table>
		</div>

		<p>Projects (<a
			href="<webwork:url value="'customerProjectModify.action'" />">Add</a>):


		<div id="listTable" >	
		<table>
			<tr>
				<th>Full Name</th>
				<th>Short Name</th>
				<th>Postable</th>
				<th>Active</th>
			</tr>
			<webwork:iterator value="customerModifyContext.projects">
				<tr>
					<td><a
						href="<webwork:url value="'customerProjectModify.action'" ><webwork:param name="'target'" value="id" /></webwork:url>">
					<webwork:property value="longName" /> </a></td>
					<td><webwork:property value="shortName" /></td>
					<td><webwork:property value="postable" /></td>
					<td><webwork:property value="active" /></td>
				</tr>
			</webwork:iterator>
		</table>
		</div>
		
		

		
		
		
		
		


		</td>
	</tr>
</table>

		<p>Notes (<a
			href="<webwork:url value="'/customercomment.action'">
					<webwork:param name="'target'" value="id" />
					</webwork:url>">Add</a>)</p>


		
		<div id="listTable">
		<table>
			<tr>
				<th>By</th>
				<th width="70%">Comment</th>
				<th>Date</th>
			</tr>
			<webwork:iterator value="comments">
				<tr>
					<td><webwork:property value="username" /></td>
					<td><webwork:property value="comment" /></td>
					<td><webwork:property value="timestamp" /></td>
				</tr>
			</webwork:iterator>
		</table>
		</div>
</body>
</html>
