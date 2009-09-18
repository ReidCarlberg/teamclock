<%@ taglib uri="webwork" prefix="webwork"%>
<%@ taglib uri="fstxtime" prefix="time"%>
<html>
<head>
<title>Dashboard</title>

<link rel="stylesheet" type="text/css" href="<webwork:url value="'/css/dashboard-aug.css'" />">

  

<script type="text/javascript">
	dojo.require('dojo.io.cookie');
	dojo.require('dojo.date.format');
	dojo.require('dojo.lfx.*');
	dojo.require('dojo.widget.*');
	dojo.hostenv.setModulePrefix('utils', 'utils');
	dojo.widget.manager.registerWidgetPackage('utils');
	dojo.require("utils.AutoComplete");
</script>
<script language="javascript" >

var todoData;
var todoSortKey = "id";
var todoSortAscending = true;
if (dojo.io.cookie.getCookie("todoSortKey")) {
	todoSortKey = dojo.io.cookie.getCookie("todoSortKey");
	todoSortAscending = !!dojo.io.cookie.getCookie("todoSortAscending");
}


var todoFilteredByProject = 0;
<webwork:if test="dashboardContext.toDoProjectFilter != null">
	todoFilteredByProject = <webwork:property value="dashboardContext.toDoProjectFilter" />;
</webwork:if>

var todoDetailId = null;
var activityDetailId = null;

var activityData; 
var activitySortKey = "startMilliseconds";
var activitySortAscending = false;

var lastUpdateTime = 0;

var showPayPeriodSummary = false;
<time:userIsPrivileged>
	showPayPeriodSummary = true;
</time:userIsPrivileged>

var userIsPrivileged = false;
<time:userIsPrivileged>
	userIsPrivileged = true;
</time:userIsPrivileged>

var inErrorCondition = false;

var rightColumnContent = "activity";

if (dojo.io.cookie.getCookie("rightColumnContent")) {
	rightColumnContent = dojo.io.cookie.getCookie("rightColumnContent");
} else {
	<webwork:if test="sessionContext.systemOwner.account == 'Demo'">
		rightColumnContent = "demowelcome";
	</webwork:if>
}

function focusOnQuickEntry() {
	var focusControl = document.getElementById("todoQuickAdd");
	if (focusControl.type != "hidden") {
		focusControl.focus();
	}
}

function handleErrorCondition() {
	inErrorCondition = true;
	var statusDiv = document.getElementById('timeClockStatusDiv');
	statusDiv.innerHTML = "<p>Communication Error.  Please click on home to reload.</p>";
}

function updateLastUpdateTime() {
	lastUpdateTime = new Date().getTime();
}



function handleAutomaticUpdate() {
	if (inErrorCondition) {
		return;
	}
	var now = new Date().getTime();
	if (now - lastUpdateTime > 60000) {
		initialize();
		lastUpdateTime = now;
	}
	window.setTimeout("handleAutomaticUpdate()",10000);
}


function timeclockPunchIn() {
	timeclockGeneric("timeclockPunchInDashboard.json");
}

function timeclockPunchOut() {
	timeclockGeneric("timeclockPunchOutDashboard.json");
}

function timeclockBreakStart() {
	timeclockGeneric("timeclockBreakStartDashboard.json");
}

function timeclockBreakStop() {
	timeclockGeneric("timeclockBreakStopDashboard.json");
}

function timeclockGeneric(targetAction) {
	executeWithoutParameters(targetAction, "getTimeClockSummary");
}

function executeWithoutParameters(url, postEvent) {
	var bindArgs = {
		url: url,
		method: "post",
		error: function(type, data, evt){handleErrorCondition();},
		mimetype: "text/json"
	};
	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, postEvent);
}

function getToDoCloud() {
	executeWithoutParameters("dashboardGetToDoProjectCloud.json", "populateToDoProjectCloud");
}


function getToDoList() {
	executeWithoutParameters("getToDoList.json","populateToDoDiv");
	refreshRightColumn();
	getToDoProjectContext();
	updateLastUpdateTime();
}

function getDelegatedToDoList() {
	executeWithoutParameters("getDelegatedToDoList.json","populateDelegatedToDoDiv");
	updateLastUpdateTime();
}

function getToDoProjectContext() {
	if (todoFilteredByProject != 0) {
		executeWithoutParameters("dashboardGetToDoProjectContext.json","populateToDoProjectContext");
	} else {
		dojo.byId('todoProjectContext').innerHTML = "";	
		dojo.byId('todoProjectContext').style.display = "none";	
	}
}

function getActivityList() {
	executeWithoutParameters("viewDashboardActivityAJAX.json","populateActivityDiv");
	updateLastUpdateTime();
}
function getUnpostedActivityList() {
	executeWithoutParameters("getUnpostedActivity.json","populateUnpostedActivityDiv");
	updateLastUpdateTime();
}
function postAllUnpostedActivity() {

	executeWithoutParameters("postAllUnpostedActivity.json","getUnpostedActivityList");
	updateLastUpdateTime();
}
function getTimeClockSummary() {
	executeWithoutParameters("timeclockSummary.json","populateTimeClockSummary");
	updateLastUpdateTime();
}
function getRecentActions() {
	executeWithoutParameters("dashboardGetRecentActions.json","populateRecentActions");
	updateLastUpdateTime();
}

function getContactList() {
	executeWithoutParameters("dashboardGetContactList.json","populateContactList");
}

function getCalendar() {
	executeWithoutParameters("dashboardGetCalendar.json","populateCalendar");
}

function setRightColumnContent(ct) {
	rightColumnContent = ct;
	dojo.io.cookie.setCookie("rightColumnContent",ct);
	refreshRightColumn();
}

function refreshRightColumn() {
	dojo.byId("rightColumnActivityQuickAdd").style.display = "none";
	if (rightColumnContent=="calendar") {
		getCalendar();
	} else if (rightColumnContent == "timeclock") {
		getTimeClockStatusSummary();
	} else if (rightColumnContent == "demowelcome") {
		getDemoWelcome();
	} else if (rightColumnContent == "activity") {
		getActivityList();
		dojo.byId("rightColumnActivityQuickAdd").style.display = "block";
	} else if (rightColumnContent == "unpostedactivity") {
		getUnpostedActivityList();
	} else {
		getRecentActions();
	}
}

<webwork:if test="sessionContext.systemOwner.account == 'Demo'">
function getDemoWelcome() {
	var ret = "";

	ret += "<h1>Welcome!</h1>\n";

	ret += "<p>TeamClock.com is an easy to use, integrated system\n";
	ret += "designed to help you keep track of <span class='yellow'>time clock entries, to-do's, activities, customers, contacts</span> and more.\n";

	ret += " Have questions?  We're here to help.  Contact us at <a href='mailto:service@teamclock.com'>service@teamclock.com</a> or <span class='yellow'>708-386-4200</span>.</p>\n";

	ret += "<h2>About The Dashboard</h2>\n";

	ret += "<p>Most users can accomplish everthing they need from the <span class='yellow'>dashboard</span>.  The left side is all about what needs to be done in the present. \n";
	ret += "The right side is more about the past and, with the calendar, the future.</p>\n";

	ret += "<h2>Customize Your Experience</h2>\n";
	ret += "<p>You can easily <span class='yellow'>customize</span> your <a href='settingsModify.action'>system settings</a>. \n";
	ret += "You can add additional <a href='userList.action'>users</a> and <a href='customerlist.action'>customers</a>. \n";
	ret += "And you can also customize your <a href='userSettingsModify.action'>individual preferences</a>.</p> \n";
	
	ret += "<h2>Time Clock Administration</h2>\n";
	ret += "<p><span class='yellow'>Time clock administration</span> is easy.  \n";
	ret += "From <a href='timeclockAdmin.action'>one screen</a>, you can produce standard payroll reports and search any user's complete history.</p>";

	dojo.byId("rightColumnDetail").innerHTML = ret;
}
</webwork:if>

function getTimeClockStatusSummary() {
	if (showPayPeriodSummary) {
		executeWithoutParameters("timeclockStatusSummary.json","populateTimeClockStatusSummary");
		updateLastUpdateTime();
	}
}

function populateRecentActions(type,data,evt) {
	var ret="";

	ret+="<p align='center'><strong>Recent History</strong></p>";

	for (var i=0; i < data.length; i++) {

		ret+="<p>";

		ret+=data[i]['typeName'];

		ret+=" ";
		ret+=data[i]['description'];

		if (data[i]['id']) {
		ret+=" <img  class='jslink' onclick='showToDoDetail(" + data[i]['id'] + ");' src='images/icon-todo-more-light.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' />";
		}

		ret+="</p>\n";

	}

	ret+="\n";
	
	dojo.byId("rightColumnDetail").innerHTML = ret;

}

function populateCalendar(type,data,evt) {
	var ret="";

	ret+="<p align='center'><strong>Calendar</strong></p>";

	var bins = data['bins'];
	
	for (var i=0; i < bins.length; i++) {

		var binItems = bins[i]['items'];

		ret+="<p><span class='calendarLabel'>";

		ret+=bins[i]['label'];

		ret+="</span><br/> ";
		
		for (var j=0; j< binItems.length; j++) {
			var actionId = "cal" + binItems[j]['id'];
			
			ret += "<span onMouseOver='show(\"" + actionId + "\");' onMouseOut='hide(\"" + actionId + "\");'>\n";
		
			ret += binItems[j]['description'];
			ret += "";
			ret += "<span id='" + actionId + "' style='display: none'>";
			ret += "<img class='jslink' src='images/icon-todo-start.gif' border='0' onclick='startActivityFromCalendar(" + binItems[j]['id'] + ");'/>";
			ret += "<a href='calendarmodify.action?target=" + binItems[j]['id'] + "'><img class='jslink' src='images/icon-todo-more-hollow.gif' border='0' /></a>";
			ret += "</span></span>";
		}

		ret+="</p>\n";

	}

	ret+="\n";
	
	dojo.byId("rightColumnDetail").innerHTML = ret;

}

function populateTimeClockStatusSummary(type,data,evt) {
	var ret = "<p align='center'><strong>Time Clock Status</strong></p>";
	for (var i=0; i < data.length; i++) {
		ret += "<p><strong>"+ data[i]['username'] + "</strong><br/> " + data[i]['status'] + " / " + data[i]['totalHours'] + " / " + data[i]['breakHours'] + "</p>\n";
	}
	var d = document.getElementById("rightColumnDetail");
	d.innerHTML = ret;
}

function executeGenericTargetedFunction(targetValue, url, postEvent) {
	var params = new Array();
	params['target']=targetValue;
	var bindArgs = {
		url: url,
		method: "post",
		error: function(type, data, evt){ dojo.debug(data);alert("error" + data);},
		mimetype: "text/json",
		content: params
	};
	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, postEvent);
	focusOnQuickEntry();
}

function startToDo(id) {
	executeGenericTargetedFunction(id, "todo2activity.json", "getActivityList");
}

function ackToDo(id) {
	executeGenericTargetedFunction(id, "todo2acknowledge.json", "getToDoList");
}

function completeToDo(id) {
	executeGenericTargetedFunction(id, "todo2complete.json", "updateToDoLists");
}

function stopActivity(id) {
	executeGenericTargetedFunction(id, "activity2stop.json", "getActivityList");
}

function startActivity(id) {
	executeGenericTargetedFunction(id, "activity2start.json", "getActivityList");
}

function startActivityFromCalendar(id) {
	executeGenericTargetedFunction(id, "calendar2activity.json", "getActivityList");
}

function setToDoPriority(id, pri) {
	var params = new Array();
	params['target']=id;
	params['priority'] = pri;
	var bindArgs = {
		url: "setToDoPriority.json",
		method: "post",
		error: function(type, data, evt){alert("error");},
		mimetype: "text/json",
		content: params
	};
	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, "updateToDoLists");
	focusOnQuickEntry();
}

function executeQuickAddFunction(searchField, url, postEvent) {
	var searchTerm = searchField.value;
	var params = new Array();
	params['searchTerm']=searchTerm;
	var bindArgs = {
		url: url,
		method: "post",
		error: function(type, data, evt){alert("error");},
		mimetype: "text/json",
		content: params
	};
	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, postEvent);
	searchField.value = "";
	focusOnQuickEntry();
}

function executeQuickAddToDo(searchField) {
	executeQuickAddFunction(searchField, "dashboardtodoaddAJAX.json", "updateToDoLists");
	return false;
}

function executeQuickAddActivity(searchField) {
	executeQuickAddFunction(searchField, "dashboardActivityAdd.json", "getActivityList");
	return false;
}

//filters
function setGenericFilter(key,value, postEvent) {
	var params = new Array();
	params[key]=value;
	var bindArgs = {
		url: "homeFilter.json",
		method: "post",
		error: function(type, data, evt){alert("error setting generic filter; host unreachable.");},
		mimetype: "text/json",
		content: params
	};
	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, postEvent);
}

function setToDoPriorityFilter(id) {
	setGenericFilter('priority',id,'updateToDoLists');
	updatePriorityIcon("I",false);
	updatePriorityIcon("II",false);
	updatePriorityIcon("III",false);
	updatePriorityIcon("IV",false);
	updatePriorityIcon(id,true);
}

function updatePriorityIcon(id, on) {
	var pricon = document.getElementById("priority_"+id);
	if (pricon) {
	if (on) {
		pricon.setAttribute("src","images/icon-todo-priority"+id+"-on.gif");
	} else {
		pricon.setAttribute("src","images/icon-todo-priority"+id+".gif");
	}
	}

}

function setToDoUsername(toDoUsername) {
	setGenericFilter('toDoUsername',toDoUsername,'getToDoList');
	setToDoProjectFilter(0);
	focusOnQuickEntry();
}

function setToDoProjectFilter(toDoProjectFilter) {
	todoFilteredByProject = toDoProjectFilter;
	setGenericFilter('toDoProjectFilter',toDoProjectFilter,'getToDoList');
	focusOnQuickEntry();
}

function setActivityUsername(activityUsername) {
	setGenericFilter('activityUsername',activityUsername,'getActivityList');
}

function setActivityTargetDate(activityTargetDate) {
	setGenericFilter('activityTargetDate',activityTargetDate,'getActivityList');
}

function populateToDoDiv(type, data, evt) {
	if (data) {
		todoData = data;
		handleDrawingToDoTable();
	}
	getToDoCloud();
}

function handleDrawingToDoTable() {
	var tbl = redrawToDoTable(todoData );
	var table = document.getElementById('todoTableBody');
	table.innerHTML = tbl;
}

function populateDelegatedToDoDiv(type, data, evt) {
	if (data) {
		var tbl = redrawToDoTable(data, true);
		var table = document.getElementById('delegatedTodoTableBody');
		if (table) {
			table.innerHTML = tbl;
		}
	}
	//2006-08-12 getToDoCloud();
}

function populateToDoProjectContext(type, data, evt) {

	var ret = "";

	ret += "<table width='100%'><tr><td width='50%'>";
	ret += "<p><span class='project'>" + data['project']['longName'] + "</span><br/>\n";
	ret += "<span class='customer'>" + data['customer']['name'] + " " + data['customer']['city'];
	if (data['customer']['state']) {
		ret += ", " + data['customer']['state'];
	}
	ret += "</span>";

	ret += "<a href='customerdetail.action?target=" + data['customer']['id'] + "'><img  class='jslink' src='images/icon-todo-more-hollow.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>\n";
	if (data['project']['postable']) {
		ret += "<br/>\n";
		ret += "<span class='customerBalance'>Balance: " + data['balance'] + "</span>";
	}
	ret += "</p>\n";
	ret += "</td><td width='50%'>\n";

	
	ret += "<p>";

	for (var i = 0; i < data['contacts'].length; i++) {
		ret+="<span class='nameFirst'>" + data['contacts'][i]['nameFirst'] + "</span> <span class='nameLast'>" + data['contacts'][i]['nameLast'] + " ";
		if (data['contacts'][i]['organizationPhone']) {
			ret += " " + data['contacts'][i]['organizationPhone'] + " ";
		}
		if (data['contacts'][i]['organizationEmail']) {
			ret += "<a class='email' href='mailto:" + data['contacts'][i]['organizationEmail'] + "'>" + data['contacts'][i]['organizationEmail'] + "</a>";
		}
		ret += "</span> "

		if (data['contacts'][i]['miscPhoneMobile']) {
			ret += "<span class='altPhone'>" + data['contacts'][i]['miscPhoneMobile'] + "</span>";
		}
		ret += "<br/>";
	}
	
	ret += "</p>\n";

	ret += "</td></tr></table>\n";

	ret += "<table id='contextActivityListTable' width='100%'>\n"
	
	var qty = data['activity'].length;

	if (qty > 10) { qty = 10; }

	ret += "<tr><th width='80px'>Date</th><th>User</th><th>Comments</th><th>Elapsed</th><th>Chargeable</th></tr>\n";

	var actionId = "";
	var actionRowId = "";

	for (var i = 0; i < qty; i++) {
		actionId="pcaid"+data['activity'][i]['id'];
		actionRowId="pcaid-row"+data['activity'][i]['id'];


		ret += "<tr id='"+ actionRowId + "' ";
		//ret += " onMouseOver='show(\"" + actionId + "\");' onMouseOut='hide(\"" + actionId + "\");'>\n";
		
		ret += " onMouseOver='handleOnMouseOver(\"" + actionId + "\",\"" + actionRowId + "\");' onMouseOut='handleOnMouseOut(\"" + actionId + "\",\"" + actionRowId + "\");'>\n";
		
		var dt = dojo.date.fromSql(data['activity'][i]['date']);

		ret += getTableCell(dojo.date.format(dt, "%m/%d/%y"), null);
		ret += getTableCell(data['activity'][i]['username'], null);
		ret += "<td> " +data['activity'][i]['comments'];
		ret += "<span id='" + actionId + "' style='display: none'>";
		ret += " <img class='jslink' onclick='startActivity(" + data['activity'][i]['id'] + ");' src='images/icon-todo-start.gif' alt='stop' border='0'  height='10px' width='10px' /></span>";
		ret += "</td>\n";
		ret += getTableCell(data['activity'][i]['elapsed'], null);
		ret += getTableCell(data['activity'][i]['chargeableElapsed'],null);
		ret += "</tr>\n";
	}
	
	ret += "</table>\n";

	dojo.byId('todoProjectContext').style.display = "block";
	dojo.byId('todoProjectContext').innerHTML = ret;
	
}

function getTableCell(value, clazz) {
	var ret = "";

	ret += "<td";

	if (clazz) {
		ret += " class='" + clazz + "'";
	}
	ret += ">";
	if (value){
		ret+=value;
	} else {
		ret+="&nbsp;";
	}
	ret += "</td>\n";
	return ret;

}

function populateActivityDiv(type, data, evt) {
	if (data) {
		redrawActivityTable(data);
		activityData = data;
	}
}

function redrawActivityTable(data) {
	data['activities'].sort(activitySorter);
	var tbody = "";

	tbody +="<p class='activityProjectKey' align='center'>Today's Activity | <a class='jslinktext' onclick='setRightColumnContent(\"unpostedactivity\");'>Unposted Activity</a></p>\n";
	tbody += "<table id='activityTable' border='0' cellspacing='0' cellpadding='2' width='98%'>\n";
	//tbody += "<tr >\n";
	//tbody += "<th width='100%' align='center' >Today's Activities</th>\n";
	//tbody += "</tr>\n";

	var actionId = "";
	var actionRowId = "";

	var clazz = "";

	for(i = 0; i < data['activities'].length; i++) {

		actionId = "aaction" + data['activities'][i]['id'];
		actionRowId = "activity-" + data['activities'][i]['id'];

		tbody += "<tr id='"+ actionRowId + "' ";

		if (i % 3 == 0) {	
			clazz = "dashboardToDo_Alternate";
		} else {
			clazz="";
		}

		if (clazz != "") {
			tbody += " class='" + clazz + "' ";
		}
		tbody += " onMouseOver='handleOnMouseOver(\"" + actionId + "\", \"" 
				+ actionRowId + "\");' onMouseOut='handleOnMouseOut(\"" 
				+ actionId + "\", \"" + actionRowId + "\", \"" + clazz + "\");'>\n";

		//tbody += getTableCell(data['activities'][i]['projectKey'],clazz);
		//tbody += getTableCell(data['activities'][i]['start'],clazz);
		//tbody += getTableCell(data['activities'][i]['stop'],clazz);

		tbody += "<td class='" + clazz + "'>";

		tbody += "<span class='activityProjectKey'>" + getClean(data['activities'][i]['projectKey']) + "</span> - ";
		tbody += getClean(data['activities'][i]['comments']) + "\n";

		tbody += data['activities'][i]['start'];
		
		if (data['activities'][i]['stop']) {
			tbody += " - " + data['activities'][i]['stop'];
		}



		
		if (!data['activities'][i]['stop']) {

		tbody += " <img class='jslink' onclick='stopActivity(" + data['activities'][i]['id'] + ");' src='images/icon-todo-stop.gif' alt='stop' border='0' height='10px' width='10px' />\n";
			
		}

tbody += " <span id='" + actionId + "' style='display:none'>";
		if (data['activities'][i]['stop']) {


		tbody += "<img class='jslink' onclick='startActivity(" + data['activities'][i]['id'] + ");' src='images/icon-todo-start.gif' alt='stop' border='0'  height='10px' width='10px' />\n";
		}

		tbody += "<img  class='jslink' onclick='showActivityDetail(" + data['activities'][i]['id'] + ");' src='images/icon-todo-more.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>\n";

		tbody += "</span>";

		tbody += "</td>\n";
		

		tbody += "</tr>\n";

	}	

	tbody += "</table>\n";
	
	tbody += "<p class='activityProjectKey'>Totals - Elapsed: " + data['totalElapsed'].toFixed(2) + " Chargeable: " + data['totalChargeableElapsed'].toFixed(2) + "</p>\n";

	if (data['totalsByClass']) {
		tbody += "<p class='activityProjectKey'>Totals By Class:<br/>\n";
		
		tbody += "<ul>\n";
		
		for(i = 0; i < data['totalsByClass'].length; i++) {
			tbody += "<li>" + data['totalsByClass'][i]['className']+ ": Elapsed: " + data['totalsByClass'][i]['totalElapsed'].toFixed(2) + " Chargeable: " + data['totalsByClass'][i]['totalChargeableElapsed'].toFixed(2) + "</li>\n";			
		}
		
		tbody += "</ul></p>\n";
	}

	dojo.byId("rightColumnDetail").innerHTML = tbody;
}

function populateUnpostedActivityDiv(type, data, evt) {
	if (data) {
		redrawUnpostedActivityTable(data);
	}
}

function redrawUnpostedActivityTable(data) {

	var tbody = "";

	tbody +="<p class='activityProjectKey' align='center'><a class='jslinktext' onclick='setRightColumnContent(\"activity\");'>Today's Activity</a> | Unposted Activity</p>\n";
	tbody += "<table id='activityTable' border='0' cellspacing='0' cellpadding='2' width='98%'>\n";


	var actionId = "";
	var actionRowId = "";

	var clazz = "";

	for(i = 0; i < data['activities'].length; i++) {

		actionId = "aaction" + data['activities'][i]['id'];
		actionRowId = "activity-" + data['activities'][i]['id'];

		tbody += "<tr id='"+ actionRowId + "' ";

		if (i % 3 == 0) {	
			clazz = "dashboardToDo_Alternate";
		} else {
			clazz="";
		}

		if (clazz != "") {
			tbody += " class='" + clazz + "' ";
		}


		tbody += "<td class='" + clazz + "'>";

		tbody += "<span class='activityProjectKey'>" + getClean(data['activities'][i]['projectKey']) + "</span> - ";
		tbody += getClean(data['activities'][i]['comments']) + "\n";

		tbody += "Elapsed: " + data['activities'][i]['elapsed'].toFixed(2);
		tbody += "; Chargeable: " + data['activities'][i]['chargeableElapsed'].toFixed(2);		

		tbody += "<img  class='jslink' onclick='showActivityDetail(" + data['activities'][i]['id'] + ");' src='images/icon-todo-more.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>\n";

		tbody += "</td>\n";
		

		tbody += "</tr>\n";

	}	

	tbody += "</table>\n";
	
	tbody += "<p class='activityProjectKey'><a class='jslinktext' onclick='postAllUnpostedActivity();'>Post All Unposted Activity</a></p>";
	
	tbody += "<p class='activityProjectKey'>Totals - Elapsed: " + data['totalElapsed'].toFixed(2) + " Chargeable: " + data['totalChargeableElapsed'].toFixed(2) + "</p>\n";

	if (data['totalsByClass']) {
		tbody += "<p class='activityProjectKey'>Totals By Class:<br/>\n";
		
		tbody += "<ul>\n";
		
		for(i = 0; i < data['totalsByClass'].length; i++) {
			tbody += "<li>" + data['totalsByClass'][i]['className']+ ": Elapsed: " + data['totalsByClass'][i]['totalElapsed'].toFixed(2) + " Chargeable: " + data['totalsByClass'][i]['totalChargeableElapsed'].toFixed(2) + "</li>\n";			
		}
		
		tbody += "</ul></p>\n";
	}

	dojo.byId("rightColumnDetail").innerHTML = tbody;
}

function redrawActivityTableA(data) {
	data['activities'].sort(activitySorter);
	var tbody = "";


	tbody += "<table id='todoTable' border='0' cellspacing='0' cellpadding='2' width='98%'>\n";
	tbody += "<tr id='todoTableHeaders'>\n";
	tbody += "<th width='12.5%' class='jslinkText'><a onclick='sortActivityData(\"projectKey\");'>Project</a></th>\n";
	tbody += "<th width='10%' class='jslinkText'><a onclick='sortActivityData(\"startMilliseconds\");'>Start</a></th>\n";
	tbody += "<th width='10%' class='jslinkText'><a onclick='sortActivityData(\"stopMilliseconds\");'>Stop</a></th>\n";
	tbody += "<th width='67.5%' class='jslinkText'><a onclick='sortActivityData(\"comments\");'>Comments</a></th>\n";
	tbody += "</tr>\n";

	var actionId = "";
	var actionRowId = "";

	var clazz = "";

	for(i = 0; i < data['activities'].length; i++) {

		actionId = "aaction" + data['activities'][i]['id'];
		actionRowId = "activity-" + data['activities'][i]['id'];

		tbody += "<tr id='"+ actionRowId + "' ";

		if (i % 3 == 0) {	
			//tbody += " class='dashboardToDo_Alternate' \n";
			clazz = "dashboardToDo_Alternate";
		} else {
			clazz="";
		}

		if (clazz != "") {
			tbody += " class='" + clazz + "' ";
		}
		tbody += " onMouseOver='handleOnMouseOver(\"" + actionId + "\", \"" 
				+ actionRowId + "\");' onMouseOut='handleOnMouseOut(\"" 
				+ actionId + "\", \"" + actionRowId + "\", \"" + clazz + "\");'>\n";
		

		tbody += getTableCell(data['activities'][i]['projectKey'],clazz);
		tbody += getTableCell(data['activities'][i]['start'],clazz);

		tbody += getTableCell(data['activities'][i]['stop'],clazz);
		tbody += "<td class='" + clazz + "'>" + getClean(data['activities'][i]['comments']) + "\n";


		
		if (!data['activities'][i]['stop']) {

		tbody += "<img class='jslink' onclick='stopActivity(" + data['activities'][i]['id'] + ");' src='images/icon-todo-stop.gif' alt='stop' border='0' height='10px' width='10px' />\n";
			
		}

tbody += "<span id='" + actionId + "' style='display:none'>";
		if (data['activities'][i]['stop']) {


		tbody += "<img class='jslink' onclick='startActivity(" + data['activities'][i]['id'] + ");' src='images/icon-todo-start.gif' alt='stop' border='0'  height='10px' width='10px' />\n";
		}

		tbody += "<img  class='jslink' onclick='showActivityDetail(" + data['activities'][i]['id'] + ");' src='images/icon-todo-more.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>\n";

		tbody += "</span>";

		tbody += "</td>\n";
		

		tbody += "</tr>\n";

	}	

	tbody += "</table>\n";
	
	tbody += "<p>Totals - Elapsed: " + data['totalElapsed'].toFixed(2) + " Chargeable Elapsed: " + data['totalChargeableElapsed'].toFixed(2) + "</p>\n";

	//var table = document.getElementById('activityTableBody');

	//table.innerHTML = tbody;
	dojo.byId("rightColumnDetail").innerHTML = tbody;
}

function redrawToDoTable(data, showAssigned) {

	if (!showAssigned) {
		data.sort(todoSorter);
	}

	var tbody = "";
	var clazz = "";

	tbody += "<table id='todoTable' border='0' cellspacing='0' cellpadding='2' width='100%'>\n";
	tbody += "<tr id='todoTableHeaders'>\n";

	if (!showAssigned) {
		tbody += "<th width='12.5%' class='jslinkText'><a onclick='sortToDoData(\"projectKey\");'>Project</a></th>\n";
		tbody += "<th width='65%' class='jslinkText'><a onclick='sortToDoData(\"detail\");'>Detail</a></th>\n";
		tbody += "<th width='5%' class='jslinkText'><a onclick='sortToDoData(\"estimatedRemainingHours\");'>Est</a></th>\n";
		tbody += "<th width='5%' class='jslinkText'><a onclick='sortToDoData(\"age\");'>Age</a></th>\n";
		tbody += "<th width='12.5%' class='jslinkText'><a onclick='sortToDoData(\"status\");'>Status</a></th>\n";

	} else {
		tbody += "<th width='12.5%' >Project</th>\n";
		tbody += "<th width='65%' >Detail</th>\n";
		tbody +="<th width='10%' >Assigned </th>\n";
		tbody += "<th width='12.5%' >Status</th>\n";
	}
	tbody += "</tr>\n";

	var actionId = "";
	var actionRowId = "";
	
	for(i = 0; i < data.length; i++) {

		actionId = "taction" + data[i]['id'];

		actionRowId = "todo-"+data[i]['id'];
		tbody += "<tr id='" + actionRowId +"' ";
		if (data[i]['status']) {
			clazz="dashboardToDo_Status";
		} else if (i % 2 == 1) {
			clazz = "dashboardToDo_Alternate";
		} else {
			clazz = "";
		}
		
		if (clazz != "") {
			tbody += " class='" + clazz + "' ";
		}
		tbody += " onMouseOver='handleOnMouseOver(\"" + actionId + "\", \"" 
				+ actionRowId + "\");' onMouseOut='handleOnMouseOut(\"" 
				+ actionId + "\", \"" + actionRowId + "\", \"" + clazz + "\");'>\n";

		tbody += getTableCell(data[i]['projectKey'], clazz);



		tbody += "<td class=" + clazz+ ">" + data[i]['detail'] + "\n";

		if (data[i]['linkedObjectType'] && data[i]['linkedObjectId']) {
			
			if (data[i]['linkedObjectType'] ='com.fivesticks.time.contactv2.ContactV2') {
	tbody += "<a href='contactdetailv2.action?target=" + data[i]['linkedObjectId'] + "'><img  class='jslink' src='images/icon-todo-more-hollow.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' /></a>\n";
			}
		}

		tbody += "<span id='" + actionId + "' style='display: none'>";
		tbody += "<img class='jslink' onclick='startToDo(" + data[i]['id'] + ");' src='images/icon-todo-start.gif' class='jslink' border='0' alt='start'  height='10px' width='10px' />\n";

		tbody += "<img  class='jslink' onclick='completeToDo(" + data[i]['id'] + ");' src='images/icon-todo-complete.gif' class='jslink' border='0' alt='complete' />\n";



		tbody += "<img  class='jslink' onclick='showToDoDetail(" + data[i]['id'] + ");' src='images/icon-todo-more.gif' class='jslink' border='0' alt='more'  height='10px' width='10px' />\n";

		tbody += "<img  class='jslink' onclick='setToDoPriority(" + data[i]['id'] + ", \"I\");' src='images/icon-todo-priorityI.gif' class='jslink' border='0' alt='priority I' />\n";

		tbody += "<img  class='jslink' onclick='setToDoPriority(" + data[i]['id'] + ", \"II\");' src='images/icon-todo-priorityII.gif' class='jslink' border='0' alt='priority II' />\n";

		tbody += "<img  class='jslink' onclick='setToDoPriority(" + data[i]['id'] + ", \"III\");' src='images/icon-todo-priorityIII.gif' class='jslink' border='0' alt='priority III' />\n";

		tbody += "<img  class='jslink' onclick='setToDoPriority(" + data[i]['id'] + ", \"IV\");' src='images/icon-todo-priorityIV.gif' class='jslink' border='0' alt='priority IV' />\n";

		if (getClean(data[i]['status']) == 'ASSIGNED') {
			tbody += "<img class='jslink' onclick='ackToDo(" + data[i]['id'] + ");' src='images/icon-todo-ack.gif' class='jslink' border='0' alt='ack'  height='10px' width='10px' />\n";		
		}

		tbody += "</span></td>\n";

		if (!showAssigned) {
			tbody += getTableCell(data[i]['estimatedRemainingHours'], clazz);
			tbody += getTableCell(data[i]['age'], clazz);
		} else {
			tbody += getTableCell(data[i]['assignedToUser'], clazz);
		}

		tbody += getTableCell(data[i]['status'], clazz);

		/*
		tbody += "<td>\n";



		tbody += "</td>\n";
		*/		

		tbody += "</tr>\n";
	}	

	tbody += "</table>";

	return tbody;
}

function populateTimeClockSummary(type, data, evt) {

	if (!data) {
		alert("no timeclock data");
	}

	
	var html = "";

	if (data['showPunchIn']) {
		html +=  " <a class='jslinkText' onclick='timeclockPunchIn();'>Punch In</a> ";
	}

	if (data['showPunchOut']) {
		html += " <a class='jslinkText' onclick='timeclockPunchOut();'>Punch Out</a> ";
	}

	if (data['showBreakStart']) {
		html += " <a class='jslinkText' onclick='timeclockBreakStart();'>Break Start</a> ";
	}

	if (data['showBreakStop']) {
		html += " <a class='jslinkText' onclick='timeclockBreakStop();'>Break Stop</a> ";
	}

	html += " Today: " + data['totalToday'];

	html += " Pay Period: " + data['totalPeriod'];



	var statusDiv = dojo.byId('timeClockStatusDiv');
	statusDiv.innerHTML = html;

	refreshRightColumn();


}


function populateToDoProjectCloud(type,data,evt) {

	var min = 0;
	var max = 0;
	var quart = 0;

	for (var i = 0; i < data.length; i++) {
		if (data[i]['count'] > max) {
			max = data[i]['count'];
			if (min == 0) {
				min = max;
			}
		} else if (data[i]['count'] < min) {
			min = data[i]['count'];
		}
	}

	quart = (max - min) / 4;

	var ret = "<a class='jslink' onclick='setToDoProjectFilter(0);'>[all projects]</a> ";

	for (var i = 0; i < data.length; i++) {
		
		ret += "<a class='jslink' onclick='setToDoProjectFilter(" + data[i]['projectId'] + ");'>";
		
		var fs = "12px";

		if (data[i]['count'] - min >= quart * 3) {
			fs = "20px";
		} else if (data[i]['count'] - min >= quart * 2) {
			fs = "18px";
		} else if (data[i]['count'] - min >= quart )  {
			fs = "16px";
		}
		

		ret += "<span style='font-size: " + fs + ";'>" + data[i]['shortName'] + "<span></a>";

		ret += " ";
	}

	dojo.byId("todoCloud").innerHTML = ret;

}

function todoSorter(a,b) {
	var asc = 1;
	var ret = 0;
	if (!todoSortAscending) { asc = -1; }
	var key1 = a[todoSortKey];
	var key2 = b[todoSortKey];
	if (key1<key2) ret = -1 * asc;
	if (key1>key2) ret =  1 * asc;
	return ret;
}

function activitySorter(a,b) {
	var asc = 1;
	if (!activitySortAscending) { asc = -1; }
  	if (a[activitySortKey]<b[activitySortKey]) return -1 * asc;
	if (a[activitySortKey]>b[activitySortKey]) return 1 * asc;
	return 0;
}

function sortToDoData(key) {
	if (!key) {
		return;
	}

	if (key == todoSortKey) {
		todoSortAscending = !todoSortAscending;
	} else {
		todoSortKey = key;
	}
	dojo.io.cookie.setCookie("todoSortKey",key);
	if (todoSortAscending) {
		dojo.io.cookie.setCookie("todoSortAscending",todoSortAscending );
	} else {
		dojo.io.cookie.setCookie("todoSortAscending","" );
	}


	handleDrawingToDoTable();
}

function sortActivityData(key) {
	if (!key) {
		return;
	}
	if (key == activitySortKey ) {
		activitySortAscending = !activitySortAscending;
	} else {
		activitySortKey = key;
	}
	redrawActivityTable(activityData);
}


function updateToDoLists() {
	getToDoList();
	getDelegatedToDoList();
	focusOnQuickEntry();
}

function initialize() {
	updateToDoLists();
	//getActivityList();
	getTimeClockSummary();
	getToDoCloud();
	window.setTimeout("handleAutomaticUpdate()",10000);
}

function showToDoDetail(id) {
	executeGenericTargetedFunction(id, "dashboardTodoDetail.json", "showToDoDetailPopulate");
	todoDetailId = id;
}

function updateToDoDetail(reopen) {

	var params = new Array();


	params['target']=todoDetailId;
	params['priority']=document.todoDetailForm.todo_priority[document.todoDetailForm.todo_priority.selectedIndex].value;
	if (userIsPrivileged ) {
		params['assignToUser']=document.todoDetailForm.todo_assignToUser[document.todoDetailForm.todo_assignToUser.selectedIndex].value;
	}
	params['estimatedTotalTime']=document.todoDetailForm.todo_estimatedTotalHours.value;
	params['estimatedRemainingTime']=document.todoDetailForm.todo_estimatedRemainingHours.value;
	params['status']=document.todoDetailForm.todo_status.value;	
	params['detail']=document.todoDetailForm.todo_detail.value;
	params['reopen']=reopen;

	var bindArgs = {
		url: "dashboardTodoDetailUpdate.json",
		method: "post",
		error: function(type, data, evt){alert("error");},
		mimetype: "text/json",
		content: params
	};

	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, "hideToDoDetail");

}
	
function hideToDoDetail(type, data, evt) {
	todoDetailId = null;
	/* 2006-06-25 reid@fivesticks.com
	var detailDiv = document.getElementById("dashboardToDoDetail");
	detailDiv.innerHTML = "";
	detailDiv.style.display = "none";
	*/
	//fadeOut('dashboardToDoDetail');
	dojo.byId('dashboardToDoDetail').style.display = "none";
	var hideSelect = document.getElementById("homeFilter_activityUsername");
	if (hideSelect) {
		hideSelect.style.display = "block";
	}
	updateToDoLists();
}

function showToDoDetailPopulate(type, data, evt) {
	var newhtml = "";
	
	var todo = data['todo'];
	

	newhtml += "<form name='todoDetailForm'>\n";

	newhtml += "<table>\n";
	newhtml += "<tr><td>Customer</td><td>" + data['customerName'] + "</td></tr>\n";
	<%--
	newhtml += "<tr><td>Balance</td><td>" + data['customerBalance'] + "</td></tr>\n";
	--%>

	newhtml += "<tr><td>Project</td><td>" + todo['projectKey'] + "</td></tr>\n";

	newhtml += "<tr><td valign='top'>Detail</td><td><textarea name='todo_detail' cols='33' rows='3'>" + todo['detail'] + "</textarea></td></tr>\n";





	if (userIsPrivileged ) {
	newhtml += "<tr><td>Assignee</td><td>" + getHtmlSelect('todo_assignToUser',data['userlist'], todo['assignedToUser']) 
				+ "</td></tr>\n";
	}

	newhtml += "<tr><td>Priority</td><td>" + getHtmlSelect('todo_priority', data['prioritylist'], todo['priority']) 
				+ "</td></tr>\n";

	newhtml += "<tr><td>Est Total</td><td><input type='text' name='todo_estimatedTotalHours' value='" 
				+ getClean(todo['estimatedTotalHours']) + "' /></td></tr>\n";

	newhtml += "<tr><td>Est Remain</td><td><input type='text' name='todo_estimatedRemainingHours' value='" 
				+ getClean(todo['estimatedRemainingHours']) + "' /></td></tr>\n";

	newhtml += "<tr><td>Status</td><td><input type='text' name='todo_status' value='" 
				+ getClean(todo['status']) + "' /></td></tr>\n";
				
	newhtml += "</table>\n";
	
	newhtml += "<p style='text-align: center;'> \n";
	newhtml += "<input type='button' name='submit' value='Update' onclick='updateToDoDetail(false);'/>\n";
	if (todo['complete']) {
		newhtml += "<input type='button' name='submit' value='Update & Reopen' onclick='updateToDoDetail(true);'/>\n";
	}
	newhtml += "<input type='button' name='submit' value='Delete' onclick='deleteToDo();'/>\n";	
	newhtml += "<input type='button' name='submit' value='Close' onclick='hideToDoDetail();'/>\n";
	newhtml += "<br/><a href='todomodify.action?target=" + todo['id'] + "'>Full Modify</a>\n";



	newhtml += "</p>\n";

	newhtml += "</form>\n";

	newhtml += "<p>";

	if (todo['createTimestamp']) {
		newhtml += " Created: " + dojo.date.format(dojo.date.fromSql(todo['createTimestamp']), "%m/%d/%y");
	}
	if (todo['modifyDate']) {
		newhtml += " Modified: " + dojo.date.format(dojo.date.fromSql(todo['modifyDate']), "%m/%d/%y");
		newhtml += " By: " + todo['modifiedByUser'];
	}
	if (todo['totalElapsedHours'] &&  todo['totalChargeableElapsedHours']) {
	newhtml += "<br/>Total Elapsed: " + todo['totalElapsedHours'].toFixed(2);
	newhtml += " Total Chargeable: " + todo['totalChargeableElapsedHours'].toFixed(2);
	}

	newhtml += "</p>\n";




	var detailDiv = document.getElementById("dashboardToDoDetail");

	detailDiv.innerHTML = newhtml;

	//detailDiv.style.display = "block";

	var hideSelect = document.getElementById("homeFilter_activityUsername");
	if (hideSelect) {
		hideSelect.style.display = "none";
	}

	//fadeIn('dashboardToDoDetail');
	dojo.byId('dashboardToDoDetail').style.display = "block";
}

function showActivityDetailPopulate(type, data, evt) {
	var newhtml = "";
	
	var activity = data['activity'];



	

	newhtml += "<form name='activityDetailForm'>\n";

	newhtml += "<table>\n";
	newhtml += "<tr><td>Customer</td><td>" + data['customerName'] + "</td></tr>\n";
	<%--
	newhtml += "<tr><td>Balance</td><td>" + data['customerBalance'] + "</td></tr>\n";
	--%>
	newhtml += "<tr><td>Project</td><td>" + activity['projectKey'] + "</td></tr>\n";

	newhtml += "<tr><td>Date</td><td><input type='text' name='activity_date' value='" + activity['date'] + "' /></td></tr>\n";
	newhtml += "<tr><td>Start</td><td><input type='text' name='activity_start' value='" + activity['start'] + "' /></td></tr>\n";
	newhtml += "<tr><td>Stop</td><td>" + getHtmlText('activity_stop', activity['stop']) + "</td></tr>\n";
	
	
	newhtml += "<tr><td>Elapsed</td><td>" + getHtmlText('activity_elapsed', activity['elapsed']) + "</td></tr>\n";
	newhtml += "<tr><td>Elapsed Chargeable</td><td>" + getHtmlText('activity_chargeable', activity['chargeableElapsed']) + "</td></tr>\n";	


	newhtml += "<tr><td valign='top'>Comments</td><td><textarea name='activity_comments' cols='33' rows='3'>" + activity['comments'] + "</textarea></td></tr>\n";



				
	newhtml += "</table>\n";
	
	newhtml += "<p style='text-align: center;'> \n";
	newhtml += "<input type='button' name='submit' value='Update' onclick='updateActivityDetail(true);'/>\n";
	newhtml += "<input type='button' name='submit' value='Update & Override Chargeable' onclick='updateActivityDetail(false);'/>\n";
	newhtml += "<input type='button' name='submit' value='Delete' onclick='deleteActivity();'/>\n";
	newhtml += "<input type='button' name='submit' value='Close' onclick='hideActivityDetail();'/>\n";
	newhtml += "<br/><a href='activityrecordmodify.action?target=" + activity['id'] + "'>Full Edit</a>\n";



	newhtml += "</p>\n";

	newhtml += "</form>\n";



	var detailDiv = document.getElementById("dashboardActivityDetail");

	detailDiv.innerHTML = newhtml;


	var hideSelect = document.getElementById("homeFilter_activityUsername");
	if (hideSelect) {
		hideSelect.style.display = "none";
	}

	dojo.byId('dashboardActivityDetail').style.display = "block";

	

}

function showActivityDetail(id) {
	executeGenericTargetedFunction(id, "dashboardActivityDetail.json", "showActivityDetailPopulate");
	activityDetailId = id;
}

function updateActivityDetail(updateChargeable) {

	var params = new Array();


	params['target']=activityDetailId;
	params['date']=document.activityDetailForm.activity_date.value;
	params['startString']=document.activityDetailForm.activity_start.value;
	params['stopString']=document.activityDetailForm.activity_stop.value;
	params['computeChargeable']=updateChargeable;
	params['comments']=document.activityDetailForm.activity_comments.value;
	params['elapsedChargeable']=document.activityDetailForm.activity_chargeable.value;


	var bindArgs = {
		url: "dashboardActivityUpdateDetail.json",
		method: "post",
		error: function(type, data, evt){alert("error");},
		mimetype: "text/json",
		content: params
	};

	var req = dojo.io.bind(bindArgs);
	dojo.event.connect(req, "load", this, "hideActivityDetail");

}

function deleteActivity() {

	if (!activityDetailId) {
		return;
	}

	if (!confirm("Are you sure you want to delete this record?")) {
		return;
	}

	var params = new Array();

	executeGenericTargetedFunction(activityDetailId, "dashboardDeleteActivity.json", "hideActivityDetail");

	activityDetailId = 0;
}

function deleteToDo() {

	if (!todoDetailId) {
		return;
	}

	if (!confirm("Are you sure you want to delete this record?")) {
		return;
	}

	var params = new Array();

	executeGenericTargetedFunction(todoDetailId, "dashboardDeleteTodo.json", "hideToDoDetail");

	activityDetailId = 0;
}

function hideActivityDetail() {
	activityDetailId = null;
	//fadeOut('dashboardActivityDetail');
	dojo.byId('dashboardActivityDetail').style.display = "none";
	var hideSelect = document.getElementById("homeFilter_activityUsername");
	if (hideSelect) {
		hideSelect.style.display = "block";
	}
	//getActivityList();
	refreshRightColumn();

}

function getHtmlSelect(name, dataArray, selected) {
	var ret = "";

	ret = ret + "<select name='" + name + "'>\n";

	for (var i = 0; i < dataArray.length; i++) {
		ret = ret + "<option value='" + dataArray[i] + "' ";
		if (dataArray[i] == selected) { ret = ret + " selected "; }
		ret = ret + ">" + dataArray[i] + "</option>\n";
	}

	ret = ret + "</select>\n";
	return ret;
}

function getHtmlText(name, value, size, maxlength) {

	var ret = "";

	ret += "<input type='text' name='" + name + "' value='";
	if (value) {
		ret+= value;
	}
	ret += "' ";
	if (size) {
		ret+= " size='" + size + "' ";
	}
	if (maxlength) {
		ret += " maxlength='" + maxlength + "' "
	}
	ret += "/>";

	return ret;
}

function getClean(data) {
	if (!data) {
		return '';
	} else {
		return data;
	}
}

function handleOnMouseOver(showDivId, clazzDivId) {
	show(showDivId);
	dojo.byId(clazzDivId).className = "dashboardToDo_Main_MouseOver";
}

function handleOnMouseOut(showDivId, clazzDivId, mouseOutClazz) {
	hide(showDivId);
	dojo.byId(clazzDivId).className = mouseOutClazz;
}

function show(divId) {
	dojo.byId(divId).style.display = "block";
}

function hide(divId) {
	dojo.byId(divId).style.display = "none";
}

/* 2006-06-25 reid@fivesticks.com not quite working... */
function fadeIn(divid) {
	dojo.byId(divid).style.display='block';
	var v = dojo.lfx.html.fadeShow(divid, 500);
	v.play();
}

function fadeOut(divid) {
	var v = dojo.lfx.html.fadeOut(divid, 500, null, function(node) { node.style.display='none';} );
	v.play();
}


dojo.event.connect(window, "onload", "initialize");


</script>

</head>
<body>
<div id="dashboard">
<div id="mainColumn">
<div id="dashboardToDo">
<table >
	<tr>
			<td >

<form id="addToDoForm" onsubmit="return executeQuickAddToDo(this.todoQuickAdd);">
<input type="text" style="width: 500px" name="searchTerm" id="todoQuickAdd"/>
<dojo:AutoComplete formId="addToDoForm"
		   textboxId="todoQuickAdd"
		   action="toDoAutoCompleteAJAX.json"/>
</form>

	</td>
		<time:userIsPrivileged>
			<td>
				<form method="post" action="homeFilter.action" id="todoUserForm" >
				<webwork:select theme="'simple'" label="'User'"
					name="'toDoUsername'" value="dashboardContext.toDoUsername"
					list="activeUsers" listKey="user.username"
					listValue="user.username" emptyOption="false"
					onchange="'setToDoUsername(this.options[this.selectedIndex].value);'" cssClass="'dashboard_header_drop'" />
			</form></td>
		</time:userIsPrivileged>
		<td valign="middle"><webwork:iterator value="toDoPriorities">
			<webwork:if
				test="dashboardContext.priority != null && dashboardContext.priority.equals(name)">
				<a class="jslink" onclick="setToDoPriorityFilter('<webwork:property value="name"/>');"><img id="priority_<webwork:property value="name"/>" align="top" border="0" src="<webwork:url value="iconOn" />"></a>
			</webwork:if>
			<webwork:else>
				<a class="jslink"
					onclick="setToDoPriorityFilter('<webwork:property value="name"/>');"><img
					id="priority_<webwork:property value="name"/>"
					align="top" border="0" src="<webwork:url value="icon" />"></a>
			</webwork:else>
		</webwork:iterator> <a class="dashboard_header"
					onclick="setToDoPriorityFilter('all');"><img
			align="top" border="0"
			src="<webwork:url value="'images/icon-todo-pall.gif'" />"></a> 

</td>


	</tr>
</table>
<div id="todoCloud"></div>
<div id="todoTableBody"></div>
<div id="todoProjectContext"></div>

</div>


<time:userIsPrivileged>
	<div id="delegatedTodoTableBody"></div>
</time:userIsPrivileged>

</div>


<div id="rightColumn">
<p align="center" class="header">
<webwork:if test="sessionContext.systemOwner.account == 'Demo'">
	<a class="jslinktext" onclick="setRightColumnContent('demowelcome');">Welcome</a> |
</webwork:if>
<a class="jslinktext" onclick="setRightColumnContent('activity');">Activity</a> |
<a class="jslinktext" onclick="setRightColumnContent('history');">History</a> | 
<a class="jslinktext" onclick="setRightColumnContent('calendar');">Calendar</a> 
<time:userIsPrivileged>
| 
<a class="jslinktext" onclick="setRightColumnContent('timeclock');">Time Clock</a>
</time:userIsPrivileged>
</p>
<div id="rightColumnActivityQuickAdd" style="display: none;">
<table width="100%">
	<tr>
			<td width="80%">
		
		<form id="addActivityForm" onsubmit="return executeQuickAddActivity(this.activityQuickAdd);">
<input type="text" style="width: 100%" name="searchTerm" id="activityQuickAdd"/>
<dojo:AutoComplete formId="addActivityForm"
		   textboxId="activityQuickAdd"
		   action="toDoAutoCompleteAJAX.json"/>
</form>

		</td>
		<time:userIsPrivileged>
			<td class="dashboard_header" width="20%"><webwork:form method="'post'" theme="'simple'" action="'homeFilter.action'">
				<webwork:select theme="'simple'" label="'User'"
					name="'activityUsername'" value="dashboardContext.activityUsername"
					list="activeUsers" listKey="user.username"
					listValue="user.username" emptyOption="false"
					onchange="'setActivityUsername(this.options[this.selectedIndex].value);'" cssClass="'dashboard_header_drop'" />
			</webwork:form></td>
		</time:userIsPrivileged>


		

	</tr>
</table>
</div>

<div id="rightColumnDetail"></div>
</div>


</div>

<script type="text/javascript" language="JavaScript">
<!--
focusOnQuickEntry();
 // -->
</script>


<div  id="dashboardToDoDetail" ></div>
<div  id="dashboardActivityDetail" ></div>
</body>


</html>

