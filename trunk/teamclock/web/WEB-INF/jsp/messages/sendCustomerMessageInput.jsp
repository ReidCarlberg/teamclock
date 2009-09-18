<%@ taglib uri="webwork" prefix="webwork"%>

<html>
<head>
<title>Send Message</title>
<script type="text/javascript"
	src="<webwork:url value="'/js/prototype.js'" />"></script>
<script type="text/javascript"
	src="<webwork:url value="'/js/rico.js'" />"></script>

<script>


function selectRecipientMode()
{
	
	
	if(document.sendMessage.selectRecipientsBy.value == 'Select All Customers')
	{
	collapseById('customerListDiv');
	collapseById('contactListDiv');
	}
	
	if(document.sendMessage.selectRecipientsBy.value == 'Select All Contacts')
	{
		collapseById('customerListDiv');
	collapseById('contactListDiv');
	}
	
	if(document.sendMessage.selectRecipientsBy.value ==  'Select By Contact' )
	{
		collapseById('customerListDiv');
	expandById('contactListDiv');
	 createListObjectsContact()
	}
	
		if(document.sendMessage.selectRecipientsBy.value == 'Select By Customer')
	{
		expandById('customerListDiv');
	collapseById('contactListDiv');
	createListObjectsCustomer()
	}	
	

}

var selectedList;
var availableList;
function createListObjectsCustomer(){

   availableList = document.sendMessage.customerIdListBase;
   selectedList = document.sendMessage.customerIdList;
 
}
function createListObjectsContact(){

   availableList = document.sendMessage.contactIdListBase;
   selectedList = document.sendMessage.contactIdList;
 
}

function delAttribute(){
   var selIndex = selectedList.selectedIndex;
   if(selIndex < 0)
      return;
   availableList.appendChild(
      selectedList.options.item(selIndex))
   selectNone(selectedList,availableList);
   setSize(availableList,selectedList);
   	updateSize();
   	  selectAll();
}
function addAttribute(){
   var addIndex = availableList.selectedIndex;
   if(addIndex < 0)
      return;
   selectedList.appendChild( 
      availableList.options.item(addIndex));
   selectNone(selectedList,availableList);
   setSize(selectedList,availableList);
  updateSize();
  selectAll();
}

function selectAll()
{
var len = selectedList.length -1;
    for(i=len; i>=0; i--){
       selectedList.item(i).selected=true;
    }
}

function updateSize(){
	selectedList.size=10;
	availableList.size=10;
}

function setTop(top){
	document.getElementById
      ('someLayer').style.top = top;
}
function setLayerTop(lyr,top){
	lyr.style.top = top;
}



function setSize(list1,list2){
    list1.size = getSize(list1);
    list2.size = getSize(list2);
}

function selectNone(list1,list2){
    list1.selectedIndex = -1;
    list2.selectedIndex = -1;
    addIndex = -1;
    selIndex = -1;
}


function getSize(list){
    /* Mozilla ignores whitespace, 
       IE doesn't - count the elements 
       in the list */
    var len = list.childNodes.length;
    var nsLen = 0;
    //nodeType returns 1 for elements
    for(i=0; i<len; i++){
        if(list.childNodes.item(i).nodeType==1)
            nsLen++;
    }
    if(nsLen<2)
        return 2;
    else
        return nsLen;
}

function delAll(){
    var len = selectedList.length -1;
    for(i=len; i>=0; i--){
        availableList.appendChild(selectedList.item(i));
    }
    selectNone(selectedList,availableList);
    setSize(selectedList,availableList);
    updateSize();
}

function addAll(){
   
    var len = availableList.length -1;
    
    for(i=len; i>=0; i--){
        selectedList.appendChild(availableList.item(i));
    }
    selectNone(selectedList,availableList);
    setSize(selectedList,availableList);
    updateSize();
      selectAll();
}


function showSelected(){
    var optionList = 
       document.getElementById
      ("customerIdList").options;
    var data = '';
    var len = optionList.length;
    for(i=0; i<0;i++){
            data += ',';
        data += optionList.item(i).value;
    }
    alert(data);
}


   function registerAjaxStuff() {
   
		ajaxEngine.registerRequest( 'sendCustomerMessagePreviewAJAX', 'sendCustomerMessagePreviewAJAX.ajax' );
		ajaxEngine.registerAjaxElement('messagePreviewAJAXDiv');
 
   }

   
   function getMessagePreview() {
   //var index =document.sendMessage.messageId.selectedIndex;
   var id = document.sendMessage.messageId.value;
   
   	ajaxEngine.sendRequest( 'sendCustomerMessagePreviewAJAX', "messageId="+id); 
   }


</script>
</head>
<body>
<script>
	onloads.push( selectRecipientMode );
	onloads.push( registerAjaxStuff );
</script>

<h2>Send Message</h2>

<webwork:form method="'post'" theme="'simple'"
	action="'/sendCustomerMessageSelectRecipients.action'"
	name="'sendMessage'">
	<table>
		<tr>
			<td>Messages:</td>
			<td><webwork:select theme="'simple'" label="'Messages'"
				emptyOption="'true'" name="'messageId'" list="messages" listKey="id"
				listValue="name" onchange="'getMessagePreview()'" multiple="'false'" /></td>
		</tr>
		<tr>
			<td>Select Recipients By:</td>
			<td><webwork:select onchange="'selectRecipientMode()'"
				theme="'simple'" label="'Select Recipients By'"
				name="'selectRecipientsBy'"
				list="{'Select All Customers', 'Select All Contacts', 'Select By Customer', 'Select By Contact' }" />
			</td>
		</tr>
		<tr>
			<td>Customer Status By:</td>
			<td><webwork:select onchange="'submit()'"
				theme="'simple'" label="'Status Filter'"
				name="'customerStatusLookupId'"
				list="customerStatusCodes"
				listKey="id"
				listValue="fullName" />
			</td>
		</tr>		
		<tr>
			<td colspan="2">
			<div id="customerListDiv" style="display:none">
			<table>
				<tr>
					<td>CustomerList:</td>
				</tr>
				<tr>
					<td><webwork:select theme="'simple'" label="'CustomerList'"
						id="'customerIdListBase'" name="'customerIdListBase'"
						list="customers" listKey="id" listValue="name" size="'10'" /></td>

					<td><a href="#a" onclick="addAll()"> >>> </a><br />
					<a href="#a" onclick="addAttribute()"> ></a> <br />
					<a href="#a" onclick="delAttribute()">< </a> <br />
					<a href="#a" onclick="delAll()"> <<< </a> <br />

					</td>
					<td><webwork:select theme="'simple'" label="'CustomerList'"
						id="'customerIdList'" name="'customerIdList'" multiple="'true'"
						size="'10'" /></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div id="contactListDiv" style="display:none">
			<table>

				<tr>
					<td>ContactList:</td>
				</tr>
				<tr>
					<td><webwork:select theme="'simple'" id="'contactIdListBase'"
						name="'contactIdListBase'" list="contacts" listKey="id"
						listValue="name" size="'10'" /></td>

					<td><a href="#a" onclick="addAll()"> >>> </a><br />
					<a href="#a" onclick="addAttribute()"> ></a> <br />
					<a href="#a" onclick="delAttribute()">< </a> <br />
					<a href="#a" onclick="delAll()"> <<< </a> <br />

					</td>
					<td><webwork:select theme="'simple'" id="'contactIdList'"
						name="'contactIdList'" multiple="'true'" size="'10'" /></td>
				</tr>

			</table>
			</div>
			</td>
		</tr>

		<tr>
			<td></td>
			<td><webwork:submit theme="'simple'" name="'submitSend'"
				value="'Send'" /></td>
		</tr>
	</table>

	<pre>
<div id="messagePreviewAJAXDiv" style="background-color: silver;"></div>
</pre>


</webwork:form>

</body>
</html>