<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Web Forms</title>
	</head>
	<body>
	<table width="100%">
	<tr>
		<td width="50%">
	<h2>Web Forms</h2>
	</td>
	<td width="50%" align="right">
	<p><a href="<webwork:url value="'/webformmodify.action'" />">Add Web Form</a></p>
	</td>
	</tr>
	</table>
	


<div id="listTable" >
	<table cellpadding="5" border="1" style="border-collapse: collapse;">
		<tr>
			<th>Name</th>
			<th>Key</th>
			<th>Modified</th>
			<th>&nbsp;</th>

		</tr>
		<webwork:iterator value="forms" >
			<tr>

				<td>
					<webwork:property value="name" />

				</td>
				<td>
					<webwork:property value="key" />

				</td>
				<td>
					<webwork:property value="modifyDate" />
				</td>
							
				<td>
					<a href="<webwork:url value="'/webformmodify.action'" >
					<webwork:param name="'target'" value="id" /></webwork:url>">
					Modify</a>
				</td>

			</tr>
		</webwork:iterator>
	</table>
</div>
	
<h2>TeamClock Connect: How To Use a Web Form</h2>

<p>The basic idea: instead of having your own database and search mechanism on an independent website, a web form
imports your web contact information into a your TeamClock.com account.</p>

<h3>How to set it up.</h3>

<ul>
<li>Set up a web form.  You'll need notification and thank you messages as well as the lookups (Admin > Admin Menu > Lookups).</li>
<li>Create a form on an external website.</li>
<li>Your form action should be:
<ul>
<li>SSL: https://my.teamclock.com/tcconnect.jxml - if your success page is SSL, you can use this address.  If your success page is not SSL, Internet Explorer users will get a security warning.</li>
<li>Non-SSL: http://connect.teamclock.com/tcconnect.jxml</li>
</ul>
</li>
<li>Your form must include a hidden field called "contactform" that includes the form key.</li>
<li>All fields that start with "contact" will either be imported to the contact record or ignored.</li>
<li>The "contactdetail" field will become the text of a to-do.</li>
<li>Fields that do not start with "contact" will be imported as key value pairs.  You can have an unlimited number of these.</li>
</ul>

<h3>Field Names </h3>
<p>On your form, your field names should be "contact." plus the field name below in order to import into
a contact record. Field names are CasE SENsitivE. </p>

<ul>

    <li> nameTitle - Mr. Ms. Mrs.</li>

    <li> nameFirst</li>

    <li> nameMiddle</li>

    <li> nameLast</li>

    <li> nameSuffix - Jr. Sr. III IV</li>

    <li> nameFormatted - TeamClock.com will attempt to parse into nameFirst, nameMiddle and nameLast.</li>

    <li> nameNickname</li>

   <li> birthdayYear</li>

    <li> birthdayMonth</li>

    <li> birthdayDay</li>

    <li> organizationName</li>

    <li> organizationTitle</li>

    <li> organizationJob</li>

    <li> organizationDepartment</li>

    <li> homeAddress1</li>

    <li> homeAddress2</li>

    <li> homePostalBox</li>

    <li> homeCity</li>

    <li> homeRegion</li>

    <li> homePostalCode</li>

    <li> homeCountry</li>

    <li> organizationAddress1</li>

    <li> organizationAddress2</li>

    <li> organizationPostalBox</li>

    <li> organizationCity</li>

    <li> organizationRegion</li>

    <li> organizationPostalCode</li>

    <li> organizationCountry</li>

    <li> postalAddress1</li>

    <li> postalAddress2</li>

    <li> postalPostalBox</li>

    <li> postalCity</li>

    <li> postalRegion</li>

    <li> postalPostalCode</li>

    <li> postalCountry</li>

    <li> homePhone</li>

    <li> homeFax</li>

    <li> homeEmail</li>

    <li> homeWeb</li>

    <li> organizationPhone</li>

    <li> organizationFax</li>

    <li> organizationTelex</li>

    <li> organizationEmail</li>

    <li> organizationWeb</li>

    <li> miscPhoneMobile</li>

    <li> miscPhoneCarphone</li>

    <li> miscPhonePager</li>

    <li> miscTimeZone</li>

    <li> miscDescription</li>

    <li> imAim</li>

    <li> imIcq</li>

    <li> imJabber</li>

    <li> imGTalk</li>

    <li> imYahoo</li>

    <li> imMsn</li>

</ul>
	
	</body>
	
	</html>
	
	
	
	