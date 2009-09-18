<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Customer Settings Modify</title>
	</head>
	<body>
		<h2>Customer Settings Modify</h2>
		
		<webwork:form action="'customerSettingsModify.action'" method="'post'">

		
		<webwork:textfield label="'Account Balance Notify E-Mail Address'" name="'customerSettingVO.accountBalanceNotifyEmailAddress'" value="customerSettingVO.accountBalanceNotifyEmailAddress" />
		<webwork:select label="'Account Balance Notify Frequency'" 
			name="'customerSettingVO.accountBalanceNotifyFrequency'" 
				list="{'Weekly', 'Monthly'}" emptyOption="true" />
		
		
		
		
		<webwork:submit value="'Save'" name="'customerSettingSubmit'" />
	    <webwork:submit value="'Cancel'" name="'customerSettingCancel'" />
		
		</webwork:form>
		
		
	</body>
</html>