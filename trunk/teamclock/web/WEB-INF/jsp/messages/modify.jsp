<%@ taglib uri="webwork" prefix="webwork" %>

<html>
	<head>
		<title>Modify Message</title>
	</head>
	<body>
		<h2>Modify Message</h2>
		
		<webwork:form method="'post'" action="'/messageModify.action'" >
		
		<webwork:textfield label="'Name'" name="'targetMessage.name'" 
			value="targetMessage.name" size="'50'" maxlength="'50'" />
		
		<webwork:textfield label="'Subject'" name="'targetMessage.subject'" 
			value="targetMessage.subject" size="'50'" maxlength="'50'" />
		
		<webwork:textarea label="'Message'" name="'targetMessage.message'"
			value="targetMessage.message" cols="'50'" rows="'20'" />
			
		<webwork:submit name="'cancelMessage'" value="'Cancel'" />

		<webwork:submit name="'saveMessage'" value="'Save'" />		

		<webwork:if test="modifyContext.target != null">
			<webwork:submit name="'copyMessage'" value="'Copy'" />		
			<webwork:submit name="'deleteMessage'" value="'Delete'" />		
		</webwork:if>
		
		</webwork:form>
		
		
		<pre>
		<div>
		<table border="1">
		<tr><th colspan="3">Message Merge Tags</th></tr>
		<tr>
		<td>The Customer's Name</td>
		<td>The Customer's Balance</td>
		<td>The Customer's Last Month or 10 transaction</td></tr>
		<tr>
		
		<td><pre>&lt;customer:name /&gt;</pre></td>
		<td><pre>&lt;customer:balance /&gt;</pre></td>
		<td><pre>&lt;customer:transactions /&gt;</pre></td>
		

		</tr>
		<tr>
		<td>Contact First Name</td>
		<td>Contact Last Name</td>
		<td>&nbsp;</td></tr>
		<tr>
		
		<td><pre>&lt;contact:nameFirst /&gt;</pre></td>
		<td><pre>&lt;contact:nameLast /&gt;</pre></td>
		<td></td>
		

		</tr>		
		</table>
		</div>
		</pre>
		
		
		
		<webwork:if test="sessionContext.featureSuperUser">
		
				<pre>
		<div>
		<table border="1">
		<tr><th colspan="3">System Owner Message Merge Tags</th></tr>
		<tr><td>The System Owner's Name</td>
		<td>The System Owner's Company Name</td>
		<td>The System Owner's Key</td></tr>
		<tr>
		
		<td><pre>  &lt;systemowner:name /&gt; </pre></td>
		<td><pre>&lt;systemowner:company /&gt;</pre></td>
		<td><pre>&lt;systemowner:key /&gt;</pre></td>
		

		</tr>
		</table>
		</div>
		</pre>

		</webwork:if>
		