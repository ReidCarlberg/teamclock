<%@ taglib uri="webwork" prefix="webwork" %>

<table>
    <tr><td class="leftLabel">Name:</td><td>
	    <webwork:property value="targetContactV2.namePrefix"/> 
		<webwork:property value="targetContactV2.nameFirst"/> 
		<webwork:property value="targetContactV2.nameMiddle"/> 
		<webwork:property value="targetContactV2.nameLast"/> 
		<webwork:property value="targetContactV2.nameSuffix"/></td></tr>
	<tr><td class="leftLabel">Formatted:</td><td> <webwork:property value="targetContactV2.nameFormatted" /></td></tr>
	<webwork:if test="targetContactV2.nameNickname != null">
		<tr><td class="leftLabel">Nickname:</td><td><webwork:property value="targetContactV2.nameNickname"/></td></tr>
	</webwork:if>
</table>

<!--  Personal Info -->
<table>
<webwork:if test="targetContactV2.miscTimeZone != null">
	<tr><td class="leftLabel">Time Zone:</td><td> <webwork:property value="targetContactV2.miscTimeZone" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.miscDescription != null">
	<tr><td class="leftLabel">Description:</td><td> <webwork:property value="targetContactV2.miscDescription" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.birthdayMonth != null || targetContactV2.birthdayDay != null || targetContactV2.birthdayYear != null">
	<tr><td class="leftLabel">Birthday (m/d/y): </td>
		<td><webwork:property value="targetContactV2.birthdayMonth" /> /
		<webwork:property value="targetContactV2.birthdayDay" /> /
		<webwork:property value="targetContactV2.birthdayYear" /></td>
	</tr>
</webwork:if>
</table>

<!--  Contact Info -->
<table>
<webwork:if test="targetContactV2.organizationWeb != null">
	<tr><td class="leftLabel">Web: </td><td><webwork:property value="targetContactV2.organizationWeb" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.homeWeb != null">
	<tr><td class="leftLabel">Web (H):</td><td> <webwork:property value="targetContactV2.homeWeb" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationPhone != null">
	<tr><td class="leftLabel">Phone (W):</td><td> <webwork:property value="targetContactV2.organizationPhone" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.miscPhoneMobile != null">
	<tr><td class="leftLabel">Phone (C):</td><td> <webwork:property value="targetContactV2.miscPhoneMobile" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.miscPhoneCarphone != null">
	<tr><td class="leftLabel">Email (C2):</td><td> <webwork:property value="targetContactV2.miscPhoneCarphone" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.homePhone != null">
	<tr><td class="leftLabel">Phone (H):</td><td> <webwork:property value="targetContactV2.homePhone" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationFax != null">
	<tr><td class="leftLabel">Phone (Fax):</td><td> <webwork:property value="targetContactV2.organizationFax" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationEmail != null">
	<tr><td class="leftLabel">Email (W):</td><td> <webwork:property value="targetContactV2.organizationEmail" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.homeEmail != null">
	<tr><td class="leftLabel">Email (H):</td><td> <webwork:property value="targetContactV2.homeEmail" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.miscPhonePager != null">
	<tr><td class="leftLabel">Pager:</td><td> <webwork:property value="targetContactV2.miscPhonePager" /></td></tr>
</webwork:if>
</table>

<!--  IM Info -->
<table>
<webwork:if test="targetContactV2.imAim != null">
	<tr><td class="leftLabel">AIM:</td><td> <webwork:property value="targetContactV2.imAim" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.imGTalk != null">
	<tr><td class="leftLabel">Gtalk:</td><td> <webwork:property value="targetContactV2.imGTalk" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.imIcq != null">
	<tr><td class="leftLabel">ICQ:</td><td> <webwork:property value="targetContactV2.imIcq" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.imJabber != null">
	<tr><td class="leftLabel">Jabber:</td><td> <webwork:property value="targetContactV2.imJabber" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.imMsn != null">
	<tr><td class="leftLabel">MSN:</td><td> <webwork:property value="targetContactV2.imMsn" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.imYahoo != null">
	<tr><td class="leftLabel">Yahoo:</td><td> <webwork:property value="targetContactV2.imYahoo" /></td></tr>
</webwork:if>
</table>

<!--  Organization Info -->
<table>
<webwork:if test="targetContactV2.organizationName != null">
	 <tr><td class="leftLabel">Name:</td><td><webwork:property value="targetContactV2.organizationName" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationDepartment != null">
	 <tr><td class="leftLabel">Department:</td><td><webwork:property value="targetContactV2.organizationDepartment" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationTitle != null">
	 <tr><td class="leftLabel">Title:</td><td><webwork:property value="targetContactV2.organizationTitle" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationJob != null">
	 <tr><td class="leftLabel">Job:</td><td><webwork:property value="targetContactV2.organizationJob" /></td></tr>
</webwork:if>
<webwork:if test="targetContactV2.organizationAddressPresent">
	<tr><td class="leftLabel">Address (W):</td><td>
	<webwork:if test="targetContactV2.organizationAddress1 != null">
		 <webwork:property value="targetContactV2.organizationAddress1" /><br/>
	</webwork:if>
	<webwork:if test="targetContactV2.organizationAddress2 != null">
		<webwork:property value="targetContactV2.organizationAddress2" /><br/>
	</webwork:if>
	<webwork:if test="targetContactV2.organizationPostalBox != null">
		PO Box <webwork:property value="targetContactV2.organizationPostalBox" /><br/>
	</webwork:if>
	<webwork:if test="targetContactV2.organizationCity != null">
		 <webwork:property value="targetContactV2.organizationCity" />
	</webwork:if>
	<webwork:if test="targetContactV2.organizationRegion != null">
		 <webwork:property value="targetContactV2.organizationRegion" />
	</webwork:if>
	<webwork:if test="targetContactV2.organizationPostalCode != null">
		 <webwork:property value="targetContactV2.organizationPostalCode" /><br/>
	</webwork:if>
	<webwork:if test="targetContactV2.organizationCountry != null">
		 <webwork:property value="targetContactV2.organizationCountry" /><br/>
	</webwork:if>
	</td></tr>
</webwork:if>
</table>

<!--  Home Info -->
<webwork:if test="targetContactV2.homeAddressPresent">
<table>
<tr><td class="leftLabel">Address (H):</td><td>
<webwork:if test="targetContactV2.homeAddress1 != null">
	 <webwork:property value="targetContactV2.homeAddress1" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.homeAddress2 != null">
	 <webwork:property value="targetContactV2.homeAddress2" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.homePostalBox != null">
	PO Box <webwork:property value="targetContactV2.homePostalBox" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.homeCity != null">
	 <webwork:property value="targetContactV2.homeCity" />
</webwork:if>
<webwork:if test="targetContactV2.homeRegion != null">
	 <webwork:property value="targetContactV2.homeRegion" />
</webwork:if>
<webwork:if test="targetContactV2.homePostalCode != null">
	 <webwork:property value="targetContactV2.homePostalCode" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.homeCountry != null">
	 <webwork:property value="targetContactV2.homeCountry" /><br/>
</webwork:if>
</td></tr>
</table>
</webwork:if>


<!--  Postal Info -->
<webwork:if test="targetContactV2.postalAddressPresent">
<table>
<tr><td class="leftLabel">Address (P):</td><td>
<webwork:if test="targetContactV2.postalAddress1 != null">
	 <webwork:property value="targetContactV2.postalAddress1" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.postalAddress2 != null">
	 <webwork:property value="targetContactV2.postalAddress2" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.postalPostalBox != null">
	PO Box <webwork:property value="targetContactV2.postalPostalBox" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.postalCity != null">
	 <webwork:property value="targetContactV2.postalCity" />
</webwork:if>
<webwork:if test="targetContactV2.postalRegion != null">
	 <webwork:property value="targetContactV2.postalRegion" />
</webwork:if>
<webwork:if test="targetContactV2.postalPostalCode != null">
	 <webwork:property value="targetContactV2.postalPostalCode" /><br/>
</webwork:if>
<webwork:if test="targetContactV2.postalCountry != null">
	 <webwork:property value="targetContactV2.postalCountry" /><br/>
</webwork:if>
</td></tr></table>
</webwork:if>

<div id="settings">
<h2>Settings</h2>

<table>

<tr>
	<tr><td class="leftLabel">Classification<br/><a href="<webwork:url value="'/contactsettingsv2.action'" >
										<webwork:param name="'lookupTypeShortName'" value="'CONTACT_CLASS'" />
										</webwork:url>">Edit</a></td>
	<td>


<webwork:iterator value="targetContactV2.classes">
	<webwork:property value="fullName" /><br/>
</webwork:iterator>
</td>
</tr>
	<tr><td class="leftLabel">Interests<br/><a href="<webwork:url value="'/contactsettingsv2.action'" >
										<webwork:param name="'lookupTypeShortName'" value="'CONTACT_INTEREST'" />
										</webwork:url>">Edit</a></td>
	<td>


<webwork:iterator value="targetContactV2.interests">
	<webwork:property value="fullName" /><br/>
</webwork:iterator>
</td>
</tr>
	<tr><td class="leftLabel">Source<br/><a href="<webwork:url value="'/contactsettingsv2.action'" >
										<webwork:param name="'lookupTypeShortName'" value="'CONTACT_SOURCE'" />
										</webwork:url>">Edit</a></td>
	<td>


<webwork:iterator value="targetContactV2.sources">
	<webwork:property value="fullName" /><br/>
</webwork:iterator>
</td>
</tr>
</table>

</div>

<div style="clear:both;"></div>

<webwork:if test="hasKeyValues">

<h2>Extra Fields</h2>

<table>
	<webwork:iterator value="keyValues">
		<tr>
			<td class="leftLabel"><webwork:property value="key" /></td>
			<td><webwork:property value="value" /></td>
		</tr>
	</webwork:iterator>
</table>


</webwork:if>

<h2>Notes</h2>
		<p>(<a
			href="<webwork:url value="'/contactv2comment.action'" />">Add</a>)</p>


		
		<div id="listTable">
		<table>
			<tr>
				<th>By</th>
				<th width="70%">Comment</th>
				<th>Date</th>
			</tr>
			<webwork:iterator value="comments">
				<tr>
					<td><webwork:property value="username" /></td>
					<td><webwork:property value="comment" /></td>
					<td><webwork:property value="timestamp" /></td>
				</tr>
			</webwork:iterator>
		</table>
		</div>
		
<h2>To-do's</h2>
		
		<div id="listTable">
		<table>
			<tr>
				<th width="10%">Date</th>
				<th width="70%">Comment</th>
			</tr>
			<webwork:iterator value="todos">
				<tr>
					<td><webwork:property value="createTimestamp" /></td>
					<td><webwork:property value="detail" /></td>
				</tr>
			</webwork:iterator>
		</table>
		</div>		