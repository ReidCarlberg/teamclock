<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Contact Modify</title>

<style>
.smallLabel {
	font-size: 8px;
	color: #999;
}
.leftLabel {
	vertical-align: top;
	font-size: 10px;
	color: #999;
	width: 100;
	text-align:right;
}

.topLabel {
	font-size: 10px;
	color: #999;
	width: 100;
	text-align:center;
}

input, select, textarea {
	font-size: 9pt;
}

h3 {
	padding: 5px 0px 0px 0px;
	margin: 0px;
}

#contactLeft, #contactRight {
	width: 48%;
	float: left;
}
</style>
<script language="javascript">

function initialize() {
	dojo.byId("nameFirst").focus();
}

dojo.event.connect(window, "onload", "initialize");
</script>
	</head>
	<body>
		<h2>
		<webwork:if test="contactV2ModifyContext.target != null">
			Modify: <webwork:property value="contactV2ModifyContext.target.nameFirst" /> <webwork:property value="contactV2ModifyContext.target.nameLast" />
		</webwork:if>
		<webwork:else>
			Create New Contact
		</webwork:else>
		</h2>
		
		<form action="contactmodifyv2.action" method="post">

<div id="contactLeft">

<h3>Name</h3>

<table>

<tr>
<td class="leftLabel">Components</td>
<td>

<table border="0" cellpadding="0" cellspacing="0">

<tr>
<td><webwork:select theme="'simple'"  
			name="'contactV2.namePrefix'" value="contactV2.namePrefix" 
			list="{'Miss', 'Mr.','Mrs.', 'Ms.', 'Dr.'}" emptyOption="true" /></td>
<td><input type="text" id="nameFirst" name="contactV2.nameFirst" value="<webwork:property value="contactV2.nameFirst"/>" size="20" maxlength="50" /></td>
<td><input type="text" name="contactV2.nameMiddle" value="<webwork:property value="contactV2.nameMiddle"/>" size="3" maxlength="50" /></td>
<td><input type="text" name="contactV2.nameLast" value="<webwork:property value="contactV2.nameLast"/>" size="20" maxlength="50" /></td>
<td><webwork:select theme="'simple'"  
			name="'contactV2.nameSuffix'" value="contactV2.nameSuffix" 
			list="{'Jr.', 'Sr.','III', 'IV'}" emptyOption="true" /></td>
</tr>
<tr>
<td class="smallLabel">Prefix</td><td class="smallLabel">First</td><td class="smallLabel">Middle</td><td class="smallLabel">Last</td><td class="smallLabel">Suffix</td>
</tr>
</table>

</td></tr>

<tr>
<td class="leftLabel">Nickname</td>
<td><input type="text" name="contactv2.nameNickname" value="<webwork:property value="contactv2.nameNickname"/>" size="50" maxlength="100" /></td>
</tr>

<tr>
<td class="leftLabel">Formatted</td>
<td><input type="text" name="contactV2.nameFormatted" value="<webwork:property value="contactV2.nameFormatted"/>" size="50" maxlength="100" /></td>
</tr>

</table>

<h3>Contact</h3>

<table>
	<tr><td></td><td class="topLabel">Work</td><td class="topLabel">Home</td></tr>
	<tr>
		<td class="leftLabel">Email</td>
		<td><input type="text" name="contactV2.organizationEmail" value="<webwork:property value="contactV2.organizationEmail"/>" size="25" maxlength="200" /></td>
		<td><input type="text" name="contactV2.homeEmail" value="<webwork:property value="contactV2.homeEmail"/>" size="25" maxlength="200" /></td>
	</tr>
	<tr>
		<td class="leftLabel">Phone</td>
		<td><input type="text" name="contactV2.organizationPhone" value="<webwork:property value="contactV2.organizationPhone"/>" size="25" maxlength="25" /></td>
		<td><input type="text" name="contactV2.homePhone" value="<webwork:property value="contactV2.homePhone"/>" size="25" maxlength="25" />
</td>
	</tr>
	<tr>
		<td class="leftLabel">Fax</td>
		<td><input type="text" name="contactV2.organizationFax" value="<webwork:property value="contactV2.organizationFax"/>" size="25" maxlength="25" />
</td>
		<td><input type="text" name="contactV2.homeFax" value="<webwork:property value="contactV2.homeFax"/>" size="25" maxlength="25" />
</td>
	</tr>
	<tr>
		<td class="leftLabel">Cell</td>
		<td><input type="text" name="contactV2.miscPhoneMobile" value="<webwork:property value="contactV2.miscPhoneMobile"/>" size="25" maxlength="25" /></td>
		<td><input type="text" name="contactV2.miscPhoneCarphone" value="<webwork:property value="contactV2.miscPhoneCarphone"/>" size="25" maxlength="25" />
</td>

	</tr>
<tr>
	<td class="leftLabel">IM</td>
	<td class="topLabel">AIM</td>
	<td class="topLabel">GTalk</td>
</tr>	
<tr>
	<td class="leftLabel"></td>
	<td><input type="text" name="contactV2.imAim" value="<webwork:property value="contactV2.imAim"/>" size="20" maxlength="50" /></td>
	<td><input type="text" name="contactV2.imGTalk" value="<webwork:property value="contactV2.imGTalk"/>" size="20" maxlength="50" /></td>
</tr>	
<tr>
	<td class="leftLabel"></td>
	<td class="topLabel">ICQ</td>
	<td class="topLabel">Jabber</td>
</tr>	
<tr>
	<td class="leftLabel"></td>
	<td><input type="text" name="contactV2.imIcq" value="<webwork:property value="contactV2.imIcq"/>" size="20" maxlength="50" /></td>
	<td><input type="text" name="contactV2.imJabber" value="<webwork:property value="contactV2.imJabber"/>" size="20" maxlength="50" /></td>
</tr>	
<tr>
	<td class="leftLabel"></td>
	<td class="topLabel">Yahoo</td>
	<td class="topLabel">MSN</td>
</tr>	
<tr>
	<td class="leftLabel"></td>
	<td><input type="text" name="contactV2.imYahoo" value="<webwork:property value="contactV2.imYahoo"/>" size="20" maxlength="50" /></td>
	<td><input type="text" name="contactV2.imMessenger" value="<webwork:property value="contactV2.imMessenger"/>" size="20" maxlength="50" /></td>
</tr>	
<tr>
	<td class="leftLabel">Pager</td>
	<td><input type="text" name="contactV2.miscPhonePager" value="<webwork:property value="contactV2.miscPhonePager"/>" size="25" maxlength="25" /></td>
	<td></td>
</tr>	



</table>


<h3>Organization</h3>

	<table>


<tr>
<td class="leftLabel">Name</td>
<td><input type="text" name="contactV2.organizationName" value="<webwork:property value="contactV2.organizationName"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Title</td>
<td><input type="text" name="contactV2.organizationTitle" value="<webwork:property value="contactV2.organizationTitle"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Department</td>
<td><input type="text" name="contactV2.organizationDepartment" value="<webwork:property value="contactV2.organizationDepartment"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Job</td>
<td><input type="text" name="contactV2.organizationJob" value="<webwork:property value="contactV2.organizationJob"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Address</td>
<td><input type="text" name="contactV2.organizationAddress1" value="<webwork:property value="contactV2.organizationAddress1"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Address(2)</td>
<td><input type="text" name="contactV2.organizationAddress2" value="<webwork:property value="contactV2.organizationAddress2"/>" size="30" maxlength="100" /><span class="leftLabel"> PO Box: </span><input type="text" name="contactV2.organizationPostalBox" value="<webwork:property value="contactV2.organizationPostalBox"/>" size="5" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">City</td>
<td><input type="text" name="contactV2.organizationCity" value="<webwork:property value="contactV2.organizationCity"/>" size="20" maxlength="100" /> <span class="leftLabel">Region:</span>
<input type="text" name="contactV2.organizationRegion" value="<webwork:property value="contactV2.organizationRegion"/>" size="15" maxlength="100" />
<span class="leftLabel">Zip:</span> <input type="text" name="contactV2.organizationPostalCode" value="<webwork:property value="contactV2.organizationPostalCode"/>" size="10" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Country</td>
<td>

<webwork:select   theme="'simple'"
			name="'contactV2.organizationCountry'" value="contactV2.organizationCountry" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />
			
</td>
</tr>


<tr>
<td class="leftLabel">Web</td>
<td><input type="text" name="contactV2.organizationWeb" value="<webwork:property value="contactV2.organizationWeb"/>" size="50" maxlength="100" /></td>
</tr>


	</table>


</div>

<div id="contactRight">
<h3>Personal</h3>

<table>

<tr>
<td class="leftLabel">Time Zone</td>
<td>

<webwork:select theme="'simple'" label="'Your Time Zone'" 
			name="'contactV2.miscTimeZone'" value="contactV2.miscTimeZone" listKey="id" listValue="displayNameFull"
			list="timeZones" emptyOption="true" />

</td>
</tr>
<td class="leftLabel">Description</td>
<td>
<textarea name="contactV2.miscDescription" cols="40" rows="3"><webwork:property value="contactV2.miscDescription"/></textarea>
</td>
</tr>
<tr>
<td class="leftLabel">Birthday</td>
<td><span class="smallLabel">mm/dd/yyyy</span><br/>
<webwork:select theme="'simple'"  
	name="'contactV2.birthdayMonth'" value="contactV2.birthdayMonth" 
	list="{1, 2,3, 4,5,6,6,8,9,10,11,12}" emptyOption="true" /> /
<webwork:select theme="'simple'"  
	name="'contactV2.birthdayDay'" value="contactV2.birthdayDay" 
	list="{1, 2,3, 4,5,6,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}" emptyOption="true" /> /

<input type="text" name="contactV2.birthdayYear" value="<webwork:property value="contactV2.birthdayYear"/>" size="4" maxlength="10" />
</td>

</tr>

</table>

<h3>Home</h3>

	<table>

<tr>
<td class="leftLabel">Address</td>
<td><input type="text" name="contactV2.homeAddress1" value="<webwork:property value="contactV2.homeAddress1"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Address(2)</td>
<td><input type="text" name="contactV2.homeAddress2" value="<webwork:property value="contactV2.homeAddress2"/>" size="30" maxlength="100" /><span class="leftLabel"> PO Box: </span><input type="text" name="contactV2.homePostalBox" value="<webwork:property value="contactV2.homePostalBox"/>" size="5" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">City</td>
<td><input type="text" name="contactV2.homeCity" value="<webwork:property value="contactV2.homeCity"/>" size="20" maxlength="100" /> <span class="leftLabel">Region:</span>
<input type="text" name="contactV2.homeRegion" value="<webwork:property value="contactV2.homeRegion"/>" size="15" maxlength="100" />
<span class="leftLabel">Zip:</span> <input type="text" name="contactV2.homePostalCode" value="<webwork:property value="contactV2.homePostalCode"/>" size="10" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Country</td>
<td>

<webwork:select   theme="'simple'"
			name="'contactV2.homeCountry'" value="contactV2.homeCountry" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />
			
</td>
</tr>

<tr>
<td class="leftLabel">Web</td>
<td><input type="text" name="contactV2.homeWeb" value="<webwork:property value="contactV2.homeWeb"/>" size="50" maxlength="100" /></td>
</tr>



	</table>

<h3>Postal Address</h3>

	<table>
<tr>
<td class="leftLabel">Address</td>
<td><input type="text" name="contactV2.postalAddress1" value="<webwork:property value="contactV2.postalAddress1"/>" size="50" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Address (2)</td>
<td><input type="text" name="contactV2.postalAddress2" value="<webwork:property value="contactV2.postalAddress2"/>" size="30" maxlength="100" /><span class="leftLabel"> PO Box: </span><input type="text" name="contactV2.postalPostalBox" value="<webwork:property value="contactV2.postalPostalBox"/>" size="5" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">City</td>
<td><input type="text" name="contactV2.postalCity" value="<webwork:property value="contactV2.postalCity"/>" size="20" maxlength="100" /> <span class="leftLabel">Region:</span>
<input type="text" name="contactV2.postalRegion" value="<webwork:property value="contactV2.postalRegion"/>" size="15" maxlength="100" />
<span class="leftLabel">Zip:</span> <input type="text" name="contactV2.postalPostalCode" value="<webwork:property value="contactV2.postalPostalCode"/>" size="10" maxlength="100" /></td>
</tr>
<tr>
<td class="leftLabel">Country</td>
<td>

<webwork:select   theme="'simple'"
			name="'contactV2.postalCountry'" value="contactV2.postalCountry" listKey="shortName" listValue="longName"
			list="countries" emptyOption="true" />

</td>
</tr>


	</table>

<p align="right">
<input type="submit" name="contactSubmit" value="Save" /> 
<input type="submit" name="contactCancel" value="Cancel" />

		<webwork:if test="contactV2ModifyContext.target != null">
<input type="submit" name="contactDelete" value="Delete" />
		</webwork:if>
</p>
</div>


				
</form>
		
		
		
	</body>
</html>