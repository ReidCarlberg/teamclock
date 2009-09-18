var lastActivityTimeStamp = new Date().getTime(); 
var refreshAfterInactivityTimeoutInMill = 5*60*1000; 
var autorefreshOn = true;

var toDoAutoCompleterInst;

function setUpActivityRefreashTimeout()
{	/* Might be nice to keep these in an array of things to do
	incase something else needs to catch this;
	*/
	document.onmousemove = updateLastActivityTimeStamp;
}

function forceRefresh()
{
	setTimeout("handleRefresh()", 500);
}

function handleRefresh(){
		getUpdateToDo();
		getUpdateCalendar();
		getUpdateActivity();
		getUpdateTagList();
		getUpdateTimeClock();
}

function updateLastActivityTimeStamp()
{
	lastActivityTimeStamp = new Date().getTime(); 
}
 
function toggleAutoRefresh()
{
	autorefreshOn = !autorefreshOn;
} 

function runAutoRefresh() {
 
	if(!autorefreshOn )
	{
		return;
	}

	var nowTimeStamp = new Date().getTime();
	var millSinceLastActivity = nowTimeStamp - lastActivityTimeStamp;
	if(millSinceLastActivity> refreshAfterInactivityTimeoutInMill)
	{
	// <%-- Update the panels! --%>
	//getUpdateToDo();
		//getUpdateCalendar();
		//getUpdateActivity();
		//getUpdateTagList();
		//getUpdateTimeClock();
		handleRefresh();
	 lastActivityTimeStamp = nowTimeStamp;
	}
	
	
	// Make sure this reflexts the "activity" refreshing --%>
	millSinceLastActivity = nowTimeStamp - lastActivityTimeStamp;
	// Try again when the time out expires.--%>
	setTimeout("runAutoRefresh()", refreshAfterInactivityTimeoutInMill- millSinceLastActivity);


	//Run each function in 5 seconds from now.
	// also run this again in 5 seconds.
	//setTimeout("getUpdateToDo()", 10000);
	//setTimeout("getUpdateCalendar()", 10000);
	//setTimeout("getUpdateActivity()", 10000);
	//setTimeout("getUpdateTagList()", 10000);
	//setTimeout("runAutoRefresh()", 10000);
}


// Init the autoComplete request  This goes for both todo and activity.--%>
function sendAutoCompleteRequest(e, searchTerm)
{

	//Firefox passes the event, IE it is global.
	if(e == null)
	{
		e= window.event;
	}

	if(e == null)	if(e.keyCode == 40)
	{
		return;
	}

	if(searchTerm.length >0)
	{
		ajaxEngine.sendRequest( 'toDoAutoCompleteAJAX',
                	           "searchTerm="+searchTerm);  
	}
}

function todoAutoComplete(e) {
	//Set Up ajax handler.
	toDoAutoCompleterInst = new ToDoAutoCompleter("detail", "toDoAutoCompleteDiv");
   	ajaxEngine.registerAjaxObject( 'ToDoAutoCompleter', toDoAutoCompleterInst );
	//Make sure we call this functions when we want to refresh the options.
	currentRefreashList = todoAutoComplete;
	
	var searchTerm = document.todo.detail.value;
	
	sendAutoCompleteRequest(e, searchTerm);
	
	
}

function activityAutoComplete(e) {
	 toDoAutoCompleterInst = new ToDoAutoCompleter("activityDetail", "activityAutoCompleteDiv");
   	ajaxEngine.registerAjaxObject( 'ToDoAutoCompleter', toDoAutoCompleterInst );
	//Make sure we call this functions when we want to refresh the options.
	currentRefreashList = activityAutoComplete;
	var searchTerm = document.activity.detail.value;
	sendAutoCompleteRequest(e, searchTerm);
}






   
   function registerAjaxStuff() {
   
	ajaxEngine.registerRequest( 'activityAutoCompleteAJAX', 'toDoAutoCompleteAJAX.ajax' );
	ajaxEngine.registerRequest( 'toDoAutoCompleteAJAX', 'toDoAutoCompleteAJAX.ajax' );
	
	ajaxEngine.registerRequest( 'tagListAJAX', 'tagListAJAX.ajax' );
   	ajaxEngine.registerRequest( 'viewDashboardToDoAJAX', 'viewDashboardToDoAJAX.ajax' );
	ajaxEngine.registerRequest( 'viewDashboardCalendarAJAX', 'viewDashboardCalendarAJAX.ajax' );
	ajaxEngine.registerRequest( 'viewDashboardActivityAJAX', 'viewDashboardActivityAJAX.ajax' );
	ajaxEngine.registerRequest( 'viewDashboardTimeClockAJAX', 'viewDashboardTimeClockAJAX.ajax' );

     


	ajaxEngine.registerRequest( 'dashboardactivitycontinueAJAX', 'dashboardactivitycontinueAJAX.ajax' );
	ajaxEngine.registerRequest( 'dashboardactivitystopAJAX', 'dashboardactivitystopAJAX.ajax' );
	ajaxEngine.registerRequest( 'dashboardtodocompleteAJAX', 'dashboardtodocompleteAJAX.ajax' );
	ajaxEngine.registerRequest( 'todotoactivityAJAX', 'todotoactivityAJAX.ajax' );
	ajaxEngine.registerRequest( 'dashboardtodoaddAJAX', 'dashboardtodoaddAJAX.ajax' );
	ajaxEngine.registerRequest( 'dashboardTodoTagSingleAJAX', 'dashboardTodoTagSingleAJAX.ajax' );	

	

	ajaxEngine.registerAjaxElement( 'toDoToActivitySuccessDiv' );
	ajaxEngine.registerAjaxElement( 'tagListAJAXDiv' );
	ajaxEngine.registerAjaxElement( 'todoAJAXDiv' );
	ajaxEngine.registerAjaxElement( 'calendarAJAXDiv' );
	ajaxEngine.registerAjaxElement( 'timeClockStatusDiv' );
	ajaxEngine.registerAjaxElement( 'activityAJAXDiv' );
   }

   function getUpdateTagList() {
 	 ajaxEngine.sendRequest( 'tagListAJAX');  
   }

   function getUpdateToDo() {
 	 ajaxEngine.sendRequest( 'viewDashboardToDoAJAX');  
   }
   function getUpdateTimeClock() {
 	 ajaxEngine.sendRequest( 'viewDashboardTimeClockAJAX');  
   }
   function getUpdateCalendar() {
 	 ajaxEngine.sendRequest( 'viewDashboardCalendarAJAX');  
   }
   function getUpdateActivity() {
   	ajaxEngine.sendRequest( 'viewDashboardActivityAJAX');  
   	
   }
   
   function sendToDoToActivity(id) {
   	ajaxEngine.sendRequest( 'todotoactivityAJAX', "target="+id); 
   	forceRefresh(); 

   }
   function sendToDoComplete(id) {
   	ajaxEngine.sendRequest( 'dashboardtodocompleteAJAX', "target="+id);  
    forceRefresh();
	//getUpdateToDo();
   }   

   function dashboardActivityContinue(id) {
   	ajaxEngine.sendRequest( 'dashboardactivitycontinueAJAX', "target="+id); 
   	forceRefresh(); 

   }
   function dashboardActivityStop(id) {
   	ajaxEngine.sendRequest( 'dashboardactivitystopAJAX', "target="+id);  
   forceRefresh();
   }  
   
   function dashboardtodoaddAJAX(quickAdd) {
   	ajaxEngine.sendRequest( 'dashboardtodoaddAJAX', "detail="+escape(quickAdd.value));  
	quickAdd.value="";
   forceRefresh();
   }  

   function dashboardToDoTagUpdateAJAX( tag,id) {
   	ajaxEngine.sendRequest( 'dashboardTodoTagSingleAJAX', "newTag="+tag.value, "todosToTagId="+id.value);  
  	forceRefresh();
   } 
   
   function dashboardToDoTagUpdateAJAXNoRefresh( tag,id) {
   	ajaxEngine.sendRequest( 'dashboardTodoTagSingleAJAX', "newTag="+tag.value, "todosToTagId="+id.value);  

   }  
   
   
   function toggleLogging() {
   	isLogOn = ! isLogOn;
   	toggle("log");
   	log("Logging On");
   }

//<%--This replaces the Tag cell with a form to edit the tag.--%>
var openId;

function replaceTagDiv(name, id, tagValue){


	//<%-- Clean up --%>
	if(openId!= null){
	if(document.todoTagSingle.newTag!= null && document.todoTagSingle.newTag.value!= null){
	openId== null
	dashboardToDoTagUpdateAJAXNoRefresh(document.todoTagSingle.newTag,document.todoTagSingle.todosToTagId);
	}
	log('todoTagDiv'+openId);
	expandById('todoTagDiv'+openId, null);
	collapseById('todoTagFormDiv'+openId);
	//We don't want to refresh while we are typeing in a new box. we should be able to 
	//individully ajax the tag values
	document.getElementById('todoTagRawDiv'+openId).innerHTML=document.todoTagSingle.newTag.value;
	document.getElementById('todoTagDiv'+openId).innerHTML=parseTag(document.todoTagSingle.newTag.value);

	document.getElementById('todoTagFormDiv'+openId).innerHTML="";	
	
	}
	openId=id;
	
	
	
	
	var form = 	"<form name='todoTagSingle' action='javascript:dashboardToDoTagUpdateAJAX(document.todoTagSingle.newTag,document.todoTagSingle.todosToTagId);' method='post'><input type='hidden' name='todosToTagId' value='"+id+"'/> <input name='newTag' size='10' value='"+tagValue+"' class='dashboard' type='text'/> </form>"
	
	//var form = 	"<form name='todoTagSingle' action='/fstxtime/dashboardTodoTagSingle.action' method='post'><input type='hidden' name='todosToTagId' value='"+id+"'/> <input name='newTag' size='20' value='"+tagValue+"' class='dashboard' type='text'/> </form>"
	var div = document.getElementById(name);

	

	div.innerHTML= form;
	document.todoTagSingle.newTag.focus();

}

function parseTag(tag)
{
if(tag.indexOf('---') ==0)
{
tag = tag.replace('---', '');
}
 return tag.replace(/---/g, ',');
  
}

var openToDoDetail;
function handleToDoDetail(id)
{
	log(id);
	if(openToDoDetail != null)
	{
        collapseById('todoFullDiv'+openToDoDetail);
	}
	javascript:expandById('todoFullDiv'+id, null);
	openToDoDetail = id;
}