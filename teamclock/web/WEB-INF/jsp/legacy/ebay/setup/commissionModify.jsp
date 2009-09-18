<%@ taglib uri="webwork" prefix="webwork" %>
<html>
	<head>
		<title>Auction Setup Commission Modify</title>

	<webwork:if test="modifyContext.target != null">
		<script>
		// <!-- 
		function confirmDelete() {
		
			var ok = confirm("Delete this commission?");
			if (ok) {
				var loc="<webwork:url value="/auctionSetupCommissionModify.action" ><webwork:param name="'confirm'" value="'yes'" /><webwork:param name="'deleteCommission'" value="'delete'" /></webwork:url>";
				location.href = loc;
			}
		}
		// -->
		</script>
	</webwork:if>
		
	</head>
	<body>
		<h2>Auction Setup: Commission Modify</h2>
		
		<webwork:form method="'post'" action="'/auctionSetupCommissionModify.action'" >

		<p>Name: 
		
		<webwork:textfield theme="'simple'" name="'targetCommission.name'" value="targetCommission.name" size="'50'" maxlength="'50'" />

		<p>Minimum: 
		
		<webwork:textfield theme="'simple'" name="'targetCommission.minimum'" value="targetCommission.minimum" size="'10'" maxlength="'10'" />

<table border="0" >
	<tr>
		<th>Tier</th>
		<th>Start</th>
		<th>End</th>
		<th>Rate</th>
	</tr>
				
	<tr>
		<td>1</td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.oneMin'" value="targetCommission.oneMin" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.oneMax'" value="targetCommission.oneMax" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.oneRate'" value="targetCommission.oneRate" size="'10'" maxlength="'10'" /></td>
	</tr>

	<tr>
		<td>2</td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.twoMin'" value="targetCommission.twoMin" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.twoMax'" value="targetCommission.twoMax" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.twoRate'" value="targetCommission.twoRate" size="'10'" maxlength="'10'" /></td>
	</tr>
	
	<tr>
		<td>3</td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.threeMin'" value="targetCommission.threeMin" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.threeMax'" value="targetCommission.threeMax" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.threeRate'" value="targetCommission.threeRate" size="'10'" maxlength="'10'" /></td>
	</tr>
	
	<tr>
		<td>4</td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.fourMin'" value="targetCommission.fourMin" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.fourMax'" value="targetCommission.fourMax" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.fourRate'" value="targetCommission.fourRate" size="'10'" maxlength="'10'" /></td>
	</tr>

	<tr>
		<td>5</td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.fiveMin'" value="targetCommission.fiveMin" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.fiveMax'" value="targetCommission.fiveMax" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.fiveRate'" value="targetCommission.fiveRate" size="'10'" maxlength="'10'" /></td>
	</tr>

	<tr>
		<td>6</td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.sixMin'" value="targetCommission.sixMin" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.sixMax'" value="targetCommission.sixMax" size="'10'" maxlength="'10'" /></td>
		<td><webwork:textfield theme="'simple'" name="'targetCommission.sixRate'" value="targetCommission.sixRate" size="'10'" maxlength="'10'" /></td>
	</tr>
<table>		

		
		<webwork:submit theme="'simple'" name="'cancelCommission'" value="'Cancel'" /><br>

		<webwork:submit theme="'simple'" name="'saveCommission'" value="'Save'" />

		<webwork:if test="modifyContext.target != null">
			<br>
			<webwork:submit theme="'simple'" name="'copyCommission'" value="'Copy'" /><br>		

			<a href="#" onclick="javascript:confirmDelete();" >Delete</a>
		</webwork:if>
		
		</webwork:form>		

<p>Rate should be whole numbers.  For example, enter "25%" as "25" NOT ".25".


	</body>
</html>