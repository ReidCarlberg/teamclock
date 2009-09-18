<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>Registration Successful</title>
<style>
#nextStepInformation {
	width:700px;
	text-align: left;
}
</style>
</head><body>

<div id="loginGreetings">
<h2 align="center">Registration Successful</h2>

<p align="center">Your registration was successful.  </p>

<p align="center">We have sent an activation link to <strong><webwork:property value="systemOwner.contactEmail" /></strong>.  </p>

<p align="center">Please click on that link to continue.</p>

</div>

<div id="nextStepInformation">
<h2>Information You'll Need to Continue</h2>

<p>Once you click on the link to activate your account, you can sign in for the first time.</p>

<p>The first time you sign in, TeamClock.com will ask for four (4) pieces of important information:</p>

<p><strong>1. A new password that you like.</strong>  Don't worry.  All
passwords are stored in an encrypted format.  Put in whatever you would like.</p>

<p><strong>2. Your payroll frequency.</strong>  How do your pay periods run--are they weekly, biweekly, 
semi-monthly or monrthly?  </p>

<p><strong>3. A recent pay period start date.</strong>  We'll use this date to track your pay periods.  
Two examples:</p>

<ul>
<li>If your payroll frequency is weekly and your pay period runs Sunday through Saturday, 
you should enter a recent Sunday's date.</li>  
<li>If your payroll frequency is biweekly, running Wednesday to Tuesday, you should enter
a recent Wednesday's date.</li>
</ul>

<p><strong>4. Your time clock punch rounding preferences.</strong>  Do you want to round to the quarter hour? Sixth of an hour? Use no rounding?</p>

<p>These are the only questions you have to answer.  You can change your answers later
by clicking on "Admin" and then selecting "System Settings".</p>


</div>

</body>

</html>