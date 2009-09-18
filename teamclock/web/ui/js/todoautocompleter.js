ToDoAutoCompleter = Class.create();
ToDoAutoCompleter.prototype.myElement =myElement;
ToDoAutoCompleter.prototype.myDiv =myDiv;

function myDiv(){}
function myElement(){}

ToDoAutoCompleter.prototype = {

		
	initialize: function(elementName, divName) {
		
		this.myDiv = document.getElementById(divName);
		
		this.myElement = document.getElementById(elementName);
		//alert(this.myElement);
	// currentElement = document.todo.detail;
	},


	ajaxUpdate: function(ajaxResponse) {
		
		// var tododiv = document.getElementById("toDoAutoCompleteDiv");
	//	 tododiv.innerHTML = "";
		 
		 this.myDiv.innerHTML = "";
		 currentElement = this.myElement;
		 currentDiv = this.myDiv;
		 buildAutoCompletePopUp(ajaxResponse, this.myDiv);
  		}
}; 


var currentElement;
var currentDiv;
var currentRefreashList;

var isFocus=true;//This could be a bit nicer, we keep track of it we have given focus to the search bar.
function handleKeyPress(e)
{



	//Firefox passes the event, ie it is global.
	if(e == null)
	{
		e= window.event;
	}

	
var keyCode = e.keyCode ? e.keyCode : e.charCode;


   if (keyCode == 8|| keyCode == 13) {
  
      if (e.preventDefault) {
        e.preventDefault();
      }
    }
    
    
	

	
	if(e.keyCode == 40 || e.keyCode == 38){
	currentElement.blur();
	isFocus=false;
	}else if(e.keyCode == 13 )
	{
	
	}else if(e.keyCode == 8)//Backspace
		{
				currentElement.focus();
				isFocus=true;
				 var currentValue =currentElement.value;
				currentElement.value = currentValue.substring(0, currentValue.length-1);
				cleanUpAutoCompleteDropDown();
				currentRefreashList(e);	
		}
	else{
		if(!isFocus){
		//If user presses a alpha key, return focus to the text box, insert the charater and run the lookup.
		//This should make it feel like the text box never loses focus.
			if((keyCode >64 && keyCode < 122) ){
			
				currentElement.focus();
				isFocus=true;
				currentElement.value+=String.fromCharCode(keyCode).toLowerCase();
				cleanUpAutoCompleteDropDown();
				currentRefreashList(e);
			}
		}
	}
	
	for( i =0 ; i < kls.childNodes.length; i++){
		
		if(kls.childNodes[i].className == 'KLOptionSelected'){
		
			if(i+1<kls.childNodes.length){	
			
				if(e.keyCode == 40){
					
					;
					KLSelectOption(kls.childNodes[i+1]);	
					
					//currentElement.focus();
				}
				
			}
			if(e.keyCode == 38){
				if(i>0){	
					KLSelectOption(kls.childNodes[i-1]);
					//currentElement.focus();
				}else{
					//We are at the top of the list and press up, we should go back to the text field.
					currentElement.focus();
					isFocus=true;
				}
				}
			
			if(e.keyCode == 13){
				updateQuickToDo(kls.childNodes[i].optionValue);
				//alert(kls.childNodes[i].optionValue);
				currentElement.focus();
				isFocus=true;
				//Clean up
				cleanUpAutoCompleteDropDown();
				currentRefreashList(e);
				//activityAutoComplete(e);
				
			}
			
			return;
			
		}
	}
	//document.todo.submit.disabled=false;
	if(e.keyCode == 40 ||e.keyCode == 38){
	
		KLSelectOption(kls.childNodes[0]);	
	}
	//If nothing is selected we submit the form.
	if(e.keyCode == 13 )
	{
		//alert("click"+ new Date());
		
		//document.todo.submit.click();
	}
	
	return;
}

function cleanUpAutoCompleteDropDown(){
	var tododiv = currentDiv;
	tododiv.innerHTML="";
	document.onkeyup=null;
	document.onkeypress=null;
	
}
//Builds the dropdown
function buildAutoCompletePopUp(ajaxResponse, targetdiv)
  { 
	  //Borwsers see to handle ajaxResponse.childNodes.length differently
	  var testValue=0;
	  if (navigator.appName=="Netscape"){
	    testValue=1;
	  }else{
	    testValue=0;
	}  
//If there are no options, we shouldn't do anything
  if(ajaxResponse.childNodes.length == testValue)
  {
	cleanUpAutoCompleteDropDown();
  	return;
  }
  
  
  kls=document.createElement('div');
  

 //document.onkeyup=handleKeyPress;
 
	
if (navigator.appName=="Netscape"){
 
  document.onkeypress  =handleKeyPress;  //Firefox likes this.
}else{
 
 	document.onkeyup  =handleKeyPress;//IE prefers this.
 }

document.onclick = mouseClickCleanUp;
 
  
  kls.className = "KLSelect";
   input = document.createElement('input');
    input.type = 'hidden';
    input.name = "Long Name";
    input.value = "Short Name";
    kls.hidinp = input;

   for( i =0 ; i < ajaxResponse.childNodes.length; i++){
  
			//Node type 1 is an element.  
		if(ajaxResponse.childNodes[i].nodeType == 1) 
		{//	tododiv.innerHTML += "<a href=\"#\"  onclick=\"javascript:updateQuickToDo('"+ajaxResponse.childNodes[i].getAttribute("shortName")+"---');\">"+ajaxResponse.childNodes[i].getAttribute("longName")+"</b> </a><br>";
			with(popt=kls.appendChild(document.createElement('p')))
			{ 
	 
	 var user = ajaxResponse.childNodes[i].getAttribute("user");
	
	  popt.appendChild(document.createTextNode(ajaxResponse.childNodes[i].getAttribute("shortName")+" ("+ajaxResponse.childNodes[i].getAttribute("longName")+") " + user));
        
	  // if(sc.options[i].selected) className = 'KLOptionSelected';
           // else className = 'KLOption';
//            title = firstChild.nodeValue;
         
	  popt.onclick=KLOptionClicked;
	  popt.onmouseover=KLOptionMouseOver;
	 // if(attachEvent){
	 
	 // attachEvent('onclick',KLOptionClicked); 
        //  attachEvent('onmouseover',KLOptionMouseOver); 
        //  attachEvent('onmouseout',KLOptionMouseOut); 
	//  }
	  //  attachEvent('onkeydown' ,KLOptionClicked); 
	  // }else if(addEventListener)
	   // {
	    //	addEventListener('click',KLOptionClicked); 
		//addEventListener('mouseover',KLOptionMouseOver); 
	//	addEventListener('mouseout',KLOptionMouseOut); 
	   // }
          }  
	  
	  
	
	  
	//if(i==0)
	//{
	
	//KLSelectOption(popt);
	//////popt.className='KLOptionSelected';
	
	//}else{
	popt.className='KLOption';
	//}
	
	 
	 var userVal = ajaxResponse.childNodes[i].getAttribute("user");
	 if(userVal.length >0)
	 {
	  popt.optionValue =ajaxResponse.childNodes[i].getAttribute("shortName")+"---"+userVal+"---";
	 }else{
		 popt.optionValue =ajaxResponse.childNodes[i].getAttribute("shortName")+"---";
	 }	
	
	}
      }
   
  
   //if(sc.form) sc.form.appendChild(input);
  //  else sc.parentNode.appendChild(input);
  // sc.parentNode.replaceChild(kls,sc);
  
 //activityAutoCompleteDiv
  
  

   // var tododiv = document.getElementById("toDoAutoCompleteDiv");
    targetdiv.appendChild(kls);

   return;	
  }

  function updateQuickToDo(term) {
//document.todo.detail.value = term;
currentElement.value=term;
//document.todo.detail.focus();
}
  
  
  function KLOptionKeyPress(e)
  {
  	//alert("he");

    return;  
  }
  
function mouseClickCleanUp(e)
{
cleanUpAutoCompleteDropDown();
} 
  
function KLOptionClicked(e)
  { 
	  //Firefox passes the event, ie it is global.
	if(e == null)
	{
		e= window.event;
	}

	var element = e.srcElement;
	if(element == null)
	{
	element = e.target;
	}
  KLSelectOption(element);
  
  for( i =0 ; i < kls.childNodes.length; i++){
		if(kls.childNodes[i].className == 'KLOptionSelected'){
			updateQuickToDo(kls.childNodes[i].optionValue);
			//alert(kls.childNodes[i].optionValue);
			currentElement.focus();
			isFocus=true;
			//Clean up
			cleanUpAutoCompleteDropDown();
			currentRefreashList(e);
		}
  }
  
  
 //updateQuickToDo(e.srcElement.optionValue+"---");
    return;  
  }

function KLOptionPopUpClicked(e)
  { KLSelectOption(e.srcElement.optionNode);     
    e.srcElement.className = 'KLOptionPopUpSelected'; 
    return;
  }

function KLSelectOption(klopt)
  {
  
  with(klopt.parentNode)
        for(var i=0; i<childNodes.length; i++){
            childNodes[i].className = 'KLOption';
	}
    klopt.parentNode.hidinp.value = klopt.optionValue;
    klopt.className = 'KLOptionSelected'; 
    return;
  }




function KLOptionMouseOver(e)
  { 

  	  //Firefox passes the event, ie it is global.
	if(e == null)
	{
		e= window.event;
	}
	
	var element = e.srcElement;
	if(element == null)
	{
	element = e.target;
	}
	
	 KLSelectOption(element);
  
  }

function KLOptionMouseOut(e)
  { if(e.srcElement.textPopUp) 
        if(e.toElement != e.srcElement.textPopUp)
            document.body.removeChild(e.srcElement.textPopUp); 
  }

function KLOptionPopUpMouseOut(e)
  { document.body.removeChild(e.srcElement);
  }

function getClientX(e)
{ return e.clientX - e.offsetX - document.body.clientLeft + document.body.scrollLeft;
}

function getClientY(e)
{ return e.clientY - e.offsetY - document.body.clientTop + document.body.scrollTop;
}


