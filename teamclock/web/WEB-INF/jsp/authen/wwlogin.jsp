<%@ taglib uri="webwork" prefix="webwork" %>
 

<html><head><title>Please Login</title></head><body>

<div align="center">
<div id="loginGreetings">

<h2 align="center">Please Login</h2>

<webwork:iterator value="actionErrors" >
	<p align="center"><webwork:property />	</p>
</webwork:iterator>

<div align="center">
<table width="100%" >
<tr>
<td width="100%" valign="top" align="center">

<webwork:form theme="'simple'" name="'loginForm'" action="'login.action'" method="'post'">
<table>
	<tr>
		<td align="right">Username:</td><td><webwork:textfield theme="'simple'" label="'Username or Email Address'" name="'username'" 
	value="" /> <span class="hint">username or email</span></td>
	</tr>
	<tr>
		<td align="right">Password:</td><td><webwork:password theme="'simple'" label="'Password'" name="'password'" 
	value="" /> <span class="hint">caSE senSiTivE</span></td>
	</tr>
	<tr>
		<td align="right">Post Login:</td><td><webwork:select theme="'simple'"  label="'Dashboard'" name="'dashboardType'"	value="dashboardType"
	list="dashboardOptions" listKey="name" listValue="name" emptyOption="'false'" />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><webwork:submit theme="'simple'" value="'Login'" name="'loginButton'" /></td>
	</tr>
</table>

</webwork:form>

</td>
</tr>
</table>




</div>





<p >
 New? <a href="<webwork:url value="'register.action'"/>">Register Here.</a>

| <a href="<webwork:url value="'passwordReset.action'"/>">Password Help</a>

| <a href="<webwork:url value="'timeclockOnlyV2.action'"/>">Time Clock Only Page</a>

</p>

<p ><a href="http://teamclock.com/blog/category/recent-changes/" target="_blank">Recent Changes</a></p>

</div></div>

<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["loginForm"].elements["username"];

  if (focusControl.type != "hidden") {
     focusControl.focus();
  }
  // -->
</script>

</body>



</html>