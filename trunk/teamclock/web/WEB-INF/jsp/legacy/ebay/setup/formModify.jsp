<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Auction Setup Form Modify</title>
	</head>
	<body>
		<h2>Auction Setup: Form Modify</h2>
		
		<webwork:form method="'post'" action="'/auctionSetupFormModify.action'" >

		<webwork:select label="'Type'" name="'targetForm.type'" value="targetForm.type"
			list="formTypes" listKey="name" listValue="name" emptyOption="true"  />
					
		<webwork:textfield name="'targetForm.name'" 
			label="'Name'" value="targetForm.name" size="'20'" maxlength="'20'" />

		<webwork:textfield name="'targetForm.linebreak'" 
			label="'Linebreak'" value="targetForm.linebreak" size="'60'" maxlength="'200'" />
			
		<webwork:textarea name="'targetForm.form'" label="'Form'"  
			value="targetForm.form" rows="15" cols="70"  />				
		
		<webwork:submit name="'cancelForm'" value="'Cancel'" />

		<webwork:submit name="'submitForm'" value="'Save'" />		

		<webwork:if test="modifyContext.target != null">
			<webwork:submit name="'copyForm'" value="'Copy'" />		
			<webwork:submit name="'deleteForm'" value="'Delete'" />		

		</webwork:if>
		
		</webwork:form>		

<h3>Listing Tags</h3>
<p>
&lt;listing:headline /&gt;<br>
&lt;listing:detail /&gt;<br>
&lt;listing:thumbnails /&gt;<br>
&lt;listing:fullsize /&gt;<br>
&lt;listing:fullsizeVertical /&gt;

<h3>Customer Tags</h3>
<p>
&lt;customer:nameAndAddress /&gt;<br>
&lt;customer:contactNameAndAddress /&gt;<br>
&lt;customer:contactFirstName /&gt;<br>
&lt;customer:contactLastName /&gt;<br>
&lt;customer:name /&gt;<br>
&lt;customer:id /&gt;

<h3>Item Tags</h3>
&lt;customer:name /&gt;<br>
&lt;item:descriptionShort /&gt;<br>
&lt;item:description /&gt;<br>
&lt;item:priceStart /&gt;<br>
&lt;item:length /&gt;<br>
&lt;item:prepayAmount /&gt;<br>
&lt;item:dateAdded /&gt;<br>
&lt;item:discount /&gt;<br>
&lt;item:id /&gt;

	</body>
</html>