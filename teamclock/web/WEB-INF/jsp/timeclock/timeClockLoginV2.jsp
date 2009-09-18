<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>

<head>

<title>Time Clock</title>

<script>

var secondsToClear = 0;

function initialize() {
	dojo.byId("username").focus();
}

function executeRequest(params, url, postEvent) {
	var bindArgs = {
		url: url,
		method: "post",
		error: function(type, data, evt) { alert("error executing request"); },
		mimetype: "text/json",
		content: params
	};
	var req = dojo.io.bind(bindArgs);
	if (postEvent) {
		dojo.event.connect(req, "load", this, postEvent);
	}
}

function handleExecuteRequest(params) {
	executeRequest(params,"handleTimeClockOnlyV2.json","populateResults");
	dojo.byId("username").value = "";
	dojo.byId("passwordx").value = "";
	dojo.byId("username").focus();
}
	
function handlePunch() {
	if (validateForm()) {
		var params = new Array();
		params['username'] = dojo.byId("username").value;
		params['password'] = dojo.byId("passwordx").value;
		params['punch'] = "punch";
		handleExecuteRequest(params);
	}
}

function handleStatus() {
	if (validateForm()){
 		var params = new Array();
 		params['username'] = dojo.byId("username").value;
 		params['password'] = dojo.byId("passwordx").value;
 		params['check'] = "check";
 		handleExecuteRequest(params);
	}
}

function handlePayPeriod(periodType) {
	if (validateForm()){
 		var params = new Array();
 		params['username'] = dojo.byId("username").value;
 		params['password'] = dojo.byId("passwordx").value;
 		params['period'] = periodType;
 		handleExecuteRequest(params);
	}
}

function validateForm() {
	var ret = true;

	if (!dojo.byId("username").value || !dojo.byId("passwordx").value ) {
		ret = false;
		alert("Please enter your username and password.");
	}

	if (!dojo.byId("username").value) { !dojo.byId("username").focus(); }
	else if (!dojo.byId("passwordx").value) { !dojo.byId("passwordx").focus(); }

	return ret;

}



function populateResults(type, data, event) {
	var ret = "";
		ret += "<p align='right'><a class='jslinkText' onclick='clearResults();' >Clear Results (auto in <span id='secondsToClear'>10</span>)</a></p>\n";
	if (data['result'] == 'success') {

		if (data['periodSummary']) {

			var periodSummaryData = data['periodSummary'];

			//period summary;
			ret += "<h2 align='center'>Period Summary (" + periodSummaryData['periodType'] + ")</h2>\n";

			ret += "<p>Total hours: " + periodSummaryData['totalRoundedHours'] + "</p>";

			ret += "<div id='listTable'><table>";
			ret += "<tr><th>Date</th><th>Hours</th></tr>\n";
			for (var i = 0; i < periodSummaryData['shifts'].length; i++) {
				ret += "<tr>";
				ret += "<td>" + periodSummaryData['shifts'][i]['shiftStart'] + "</td>";
				ret += "<td>" + periodSummaryData['shifts'][i]['shiftHours'] + "</td>\n";
				ret += "</tr>";
			}

			ret += "</table></div>";

			
		} else {
			
			ret += "<h2 align='center'>" + data['username'] ;
			if (data['currentStatus']) {
				ret += " - " + data['currentStatus'];
			}
			ret += "</h2>";

			ret += "<div id='listTable'><table>";
			ret += "<tr><th>Type</th><th>Time</th><th>Actual</th><th>From</th></tr>\n";
			for (var i = data['summary'].length - 1; i > -1; i--) {
				ret += "<tr>";
				ret += "<td>" + data['summary'][i]['formattedEvent'] + "</td>";
				ret += "<td>" + data['summary'][i]['formattedEventTimestamp'] + "</td>\n";
				ret += "<td>" + data['summary'][i]['formattedTimestamp'] + "</td>\n";
				ret += "<td>" + data['summary'][i]['sourceip'] + "</td>\n";
				ret += "</tr>";
			}

			ret += "</table></div>";
		}
	} else if (data['result'] == 'failed-authenticate') {
		ret += "<h2 align='center'>Failed</h2><p>Unable to authenticate that username and password.</p>";
	} else {
		ret += "<h2 align='center'>Failed</h2><p>Your request failed.  Please try again.</p>";
	}

	ret += "\n\n";

	dojo.byId("result").innerHTML = ret;
	dojo.byId("resultContainer").style.display = "block";
	secondsToClear = 10;
	window.setTimeout("handleAutomaticClearResults()",1000);
}

function handleAutomaticClearResults() {
	if (secondsToClear > 0) {
		secondsToClear--;
	}
	if (secondsToClear == 0) {
		if (dojo.byId("resultContainer").style.display != "none") {
			clearResults();
		}
	} else {
		dojo.byId("secondsToClear").innerHTML = secondsToClear;
		window.setTimeout("handleAutomaticClearResults()",1000);
	}
}

function clearResults() {
	dojo.byId("result").innerHTML = "";
	dojo.byId("resultContainer").style.display = "none";
	dojo.byId("username").focus();
	secondsToClear = 0;
}

dojo.event.connect(window,"onload","initialize");

</script>
<style>

h1 {
	font-size: 3.5em;
}

#left {
	width: 49%;
	float: left;
}

#right {
	width: 49%;
	float: left;
}

#resultContainer {
	display: none;
	background-color: #eee;
	text-align:left;
	border:5px solid #036;
	padding: 5px;
}

#resultContainer h2, #resultContainer p  {
	margin: 0px;
	padding: 0px;	
}

#punchIn {
	width: 100%;
	font-size: 24px;
	font-weight: bold;
}

#username, #passwordx {
	font-size: 24px;
}

</style>
</head><body>

<div id="left">

<div align="center">

<h1>Time Clock</h1>

<div id="input">
<form onsubmit="handlePunch();return false;">

<table>
	<tr>
		<td valign="top" align="right"><strong>Username:</strong></td>
		<td><input type="text" id="username" size="20" maxlength="20" /></td>
	</tr>
	<tr>
		<td valign="top" align="right"><strong>Password:</strong></td>
		<td><input type="password" id="passwordx" size="20" maxlength="20" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<p>
			<input id="punchIn" type="submit" value="Time Clock Punch In/Out" >
			</p>

			<p>
			<input type="button" value="Today's Status" onclick="handleStatus();">

			<input type="button" value="Current Pay Period" onclick="handlePayPeriod('current');">

			<input type="button" value="Previous Pay Period" onclick="handlePayPeriod('previous');">
			</p>
		</td>
	</tr>
</table>


</form>
</div>

</div>

</div>
<div id="right">

<div align="center">
<div id="resultContainer">
<div id="result">


</div>
</div>
</div>

</div>


</body></html>