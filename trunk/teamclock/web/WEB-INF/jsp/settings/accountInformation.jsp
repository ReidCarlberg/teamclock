<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Payment Information</title>

<script language="javascript">

function initialize() {
	if (dojo.byId("account_timeclock").checked || dojo.byId("account_standard").checked) {
		toggleBillingInfo();
	}

}



function toggleBillingInfo() {
	var enableBillingInfo = dojo.byId("account_standard").checked;

	var rows = ["billing","name","street","city","state","zip","country","phone","number","expires","cvv"];
	for (var i=0; i < rows.length; i++) {
		if (enableBillingInfo) {
			dojo.byId(rows[i]).className = "";
		} else {
			dojo.byId(rows[i]).className = "disabled";
		}
	}

	var fields = ["billing_monthly", "billing_annual", "updateAccount_paymentMethod.nameOnCard", "updateAccount_paymentMethod.street", 
		"updateAccount_paymentMethod.city", "updateAccount_paymentMethod.state", "updateAccount_paymentMethod.zip", 
		"updateAccount_paymentMethod.country", "updateAccount_paymentMethod.phone", "updateAccount_paymentMethod.number", 
		"updateAccount_paymentMethod.expiresMonth", "updateAccount_paymentMethod.expiresYear","updateAccount_paymentMethod.cvv"];

	for (var i=0; i < fields.length; i++) {
		dojo.byId(fields[i]).disabled = !enableBillingInfo;
	}

}

function isValid() {
	var ret = true;

	ret = dojo.byId("account_timeclock").checked || dojo.byId("account_standard").checked;

	if (!ret) {
		alert("You must select either a timeclock account or a standard account.");
		return ret;
	}

	if (dojo.byId("account_standard").checked) {
		ret = dojo.byId("billing_monthly").checked || dojo.byId("billing_annual").checked;
		if (!ret) {
			alert("You must selected either monthly or annual billing.");
			return ret;
		}
		var fieldsAreValid = true;

		if (!validateTextField("updateAccount_paymentMethod.nameOnCard")) { fieldsAreValid = false; }

 		var fields = ["updateAccount_paymentMethod.nameOnCard", "updateAccount_paymentMethod.street", 
 		"updateAccount_paymentMethod.city", "updateAccount_paymentMethod.state", "updateAccount_paymentMethod.zip", 
 		"updateAccount_paymentMethod.phone", "updateAccount_paymentMethod.number", 
 		"updateAccount_paymentMethod.cvv"];				

			for (var i=0; i < fields.length; i++) {
				if (!validateTextField(fields[i])) { fieldsAreValid = false; }
			}

		if (!fieldsAreValid) {
			alert("Your form is missing information.  Please correct to continue.");
			return false;
		}

	}	

	return ret;
}

function validateTextField(fieldId) {
	if (!dojo.byId(fieldId).value || dojo.byId(fieldId).value.length == 0) {
		dojo.byId(fieldId).className = "missing";
		return false;
	}
	return true;
	
}

function handleOnsubmit() {
	var ret = isValid();

	return ret;
}	

dojo.event.connect(window,"onload", "initialize");

</script>
<style>
.disabled {
	color: #ccc;
}
.missing {
	border: 2px solid red;
}
</style>
	</head>
	<body>


	
<h2>Account Information</h2>

<div id="mainColumn">

<webwork:if test="reasonExplanation != null">

<p><webwork:property value="reasonExplanation" /></p>

</webwork:if>

<table>
<webwork:form theme="'simple'" name="'form1'" method="'post'" action="actionName" onsubmit="'return handleOnsubmit();'" >

	<tr>
		<td align='right' valign='top'>Account Type:</td>
		<td>

			<input type="radio" name="account" id="account_timeclock" onclick="toggleBillingInfo();" value="Time Clock Only" <webwork:if test="account == 'Time Clock Only'" >checked="checked"</webwork:if> />Time Clock Only - Free<br/>
			<input type="radio" name="account" id="account_standard" onclick="toggleBillingInfo();" value="Standard" <webwork:if test="account == 'Standard'" >checked="checked"</webwork:if> />Standard Account
		</td>
	</tr>
	<tr id="billing">
		<td align='right' valign='top'>Billing Frequency:</td>
		<td>
			<input type="radio" id="billing_monthly" name="billing" value="Monthly"<webwork:if test="billing == 'Monthly'" >checked="checked"</webwork:if> />Monthly - $14.95 per month<br/>
			<input type="radio" id="billing_annual" name="billing" value="Annual"<webwork:if test="billing == 'Annual'" >checked="checked"</webwork:if> />Annual - $99.00 per year
		</td>
	</tr>		
		
	<tr id="name">
		<td align='right'>Name On Card:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'Name On Card'" name="'paymentMethod.nameOnCard'" value="paymentMethod.nameOnCard" size="'50'" maxlength="'100'" />
		</td>
	</tr>

	<tr id="street">
		<td align='right'>Street:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'Street'" name="'paymentMethod.street'" value="paymentMethod.street" size="'50'" maxlength="'100'"  />
		</td>
	</tr>


	<tr id="city">
		<td align='right'>City:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'City'" name="'paymentMethod.city'" value="paymentMethod.city"  size="'50'" maxlength="'100'"  />
		</td>
	</tr id="billing">
	
	<tr id="state">
		<td align='right'>State/Province:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'State/Province'" name="'paymentMethod.state'" value="paymentMethod.state"  size="'25'" maxlength="'50'" />
		</td>
	</tr>
		
	<tr id="zip">
		<td align='right'>Zip/Post Code:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'Zip/Post Code'" name="'paymentMethod.zip'" value="paymentMethod.zip"  size="'25'" maxlength="'25'" />
		</td>
	</tr>
	
	<tr id="country">
		<td align='right'>Country:</td>
		<td>
	<webwork:select  theme="'simple'"   label="'Country'" value="paymentMethod.country"
			name="'paymentMethod.country'" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />
		</td>
	</tr>
			
	<tr id="phone">
		<td align='right'>Phone:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'Phone'" name="'paymentMethod.phone'" value="paymentMethod.phone"  size="'25'" maxlength="'50'" />			</td>
	</tr>

	<tr id="number">
		<td align='right'>Card Number:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'Card Number'" name="'paymentMethod.number'" value="paymentMethod.obscureNumber"  size="'16'" maxlength="'16'" />
		</td>
	</tr>
		
	<tr id="expires">
		<td align='right'>Expires:</td>
		<td>Month
	<webwork:select  theme="'simple'"   label="'Expiration Month'" value="paymentMethod.expiresMonth"
			name="'paymentMethod.expiresMonth'" 
			list="{'01','02','03','04','05','06','07','08','09','10','11','12'}" emptyOption="true" />
			Year
	<webwork:select  theme="'simple'"   label="'Expiration Year'" value="paymentMethod.expiresYear"
			name="'paymentMethod.expiresYear'" 
			list="{'2006','2007','2008','2009','2010','2011'}" emptyOption="true" />
		</td>
	</tr>

	
	<tr id="cvv">
		<td align='right'>CVV:</td>
		<td>
	<webwork:textfield theme="'simple'"  label="'CVV'" name="'paymentMethod.cvv'" value="paymentMethod.cvv"  size="'5'" maxlength="'5'"  />
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="right">
	<webwork:submit theme="'simple'"  name="'methodSubmit'" value="'Update Account Information'" />
	<webwork:submit theme="'simple'"  name="'methodCancel'" value="'Cancel'" />
		</td>
	</tr>
		
</webwork:form>
			
</table>

</div>

<div id="rightColumn">


<h3>Standard Accounts</h3>

<p>Standard accounts include access to all features -- the time clock, to-do and activity tracking, calendars, customers, etc. -- and an unlimited number of users.</p>

<h3>Time Clock Only Accounts</h3>

<p>Time clock only accounts have access to the time clock section, the time clock admin section and the time clock only page.</p>

<p>Time clock only accounts cannot use standard features such as to-do management, activitiy tracking, calendars, customer management, etc.</P>



</div>
	</body>
</html>