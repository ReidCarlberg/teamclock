<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
	<head>
		<title>Initial Time Clock Settings</title>
	</head>
<body>



<div id="mainColumn">

<h1>Initial Settings</h1>


<webwork:form action="'initialSetup.action'" method="'post'" >

<webwork:password label="'Password'" name="'password1'" 
	value="password1" size="'20'" maxlength="'20'" />
	
<webwork:password label="'Password (confirm)'" name="'password2'" value="password2" size="'20'" maxlength="'20'"/>


<webwork:select label="'Pay Period Type'" name="'timeClockPayPeriodType'" value="timeClockPayPeriodType"
list="payPeriodTypes" listKey="name" listValue="name" emptyOption="false"  />

<tr>
<td align="right">Recent Period Start Date (mm/dd/yyyy):</td>
<td>
<webwork:textfield theme="'simple'" label="'Recent Period Start Date (mm/dd/yy)'" name="'payPeriodRefDate'" value="payPeriodRefDate" />
<a href="#" onclick="displayDatePicker('payPeriodRefDate');"><img border="0" src="<webwork:url value="'/images/dateIcon.gif'" />" /></a>
</td>
</tr>

<webwork:select label="'Punch Rounder Type'" name="'timeClockRounderType'" value="settings.timeClockRounderType"
list="rounderTypes" listKey="name" listValue="description" emptyOption="false"  />


	<webwork:submit name="'submitInitial'" value="'Save & Continue'" />
</webwork:form>


</div>

<div id="rightColumn">

<h2>Password</h2>

<p>Your password is transmitted and stored in an encrypted format and is <span class="yellow">never visible as plain text</span>.  If you ever need password help, we will send a password reset link to the email address we have on file.</p>

<h2>Time Clock Settings</h2>

<p>Pay period type, start date and time clock punch rounder will help TeamClock.com manage your time clock information.  You can set these to whatever you like and changed them at any time through the <span class="yellow">Admin &gt; System Settings</span> screen.</p>

<h2>On the Next Screen...</h2>

<p>On the next screen, you will be able to proceed to one of three destinations.</p>  

<p>The <span class="yellow">time clock</span> destinations will help you understand more about how the system can help manage your employees.  </p>

<p>The <span class="yellow">dashboard</span> destination will help you understand other TeamClock.com features, such as to-do list management, activity tracking, etc.</p>

</div>

</body>

</html>