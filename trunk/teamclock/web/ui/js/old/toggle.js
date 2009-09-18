


//function setCookie(name, value){
//	document.cookie=name + "=" + escape(value);
//}

//function DelCookie(sName)
//{
//
//
//if(sName == "JSESSIONID")
//{
//	
//	return;
//}
//if(sName == "jsessionid")
//{
//	
//	return;
//}
//var element=document.getElementById(sName);
//	if(element != null){
//		document.cookie = sName + "=" + "a" + "; expires=Fri, 31 Dec 1999 23:59:59 GMT;";
//	}
//}

function expand(x, y){
	if(y != null)
	{
		y.src="img/collapse.gif";
	}
	
	if(x != null)
	{
		x.style.display='block';
	}
	
}
function collapse(x, y){

	if(x != null)
	{
		x.style.display='none';
		
	}
	if( y != null)
	{
	y.src="img/expand.gif";
	}
	
	
}
//function expand2(x){
	//alert("should be expanding xx" + x + "xx");
//	var element=document.getElementById(x);
	//document.write("element is null? " + x + "<br>");
//	var image=document.getElementById(x + "_image");
	//document.write("image is null? " +image + "<br>");
//	if(image != null &&  element != null)
//	{
//		expand(element,image);
//	}
//}

function toggle(x){

	var element=document.getElementById(x);
	var image=document.getElementById(x + "_image");
	
	if (element.style.display=='block'){
//		setCookie(x, '');
		collapse(element, image);
		}
		else {
//			setCookie(x, 'true');
			expand(element, image);
		}
}


function expandById(x){

	var element=document.getElementById(x);
	var image=document.getElementById(x + "_image");
	
	if (element.style.display=='none'){
//		setCookie(x, 'true');
		expand(element, image);
	}
}

function collapseById(x){

	var element=document.getElementById(x);
	var image=document.getElementById(x + "_image");
	
	if (element.style.display=='block'){
//		setCookie(x, '');
		collapse(element, image);
	}
		
}






//function initialize() {
//document.write("cookies: " + document.cookie);
//
//	var cookies=document.cookie.split(";");
//	//document.write("<br>");		
//	
//	var elementId = null;
//	var querySummaryFound =false;
//	
//	for(var i=0; i<cookies.length; i++){
//			a=cookies[i];
//			aparts=a.split("=");
//			//document.write("cookie " + i + " length is " + aparts.length);
//			if (aparts.length > 1 && aparts[1] != '') {
//				elementId = aparts[0];
//				elementId = elementId.replace(" ","");
//				
//				expand2(elementId);
//			}
//			//Lets see if we have a cookie for querySummaryDiv that is closed
//			if (aparts.length > 1 && aparts[1] == '') {
//				elementId = aparts[0];
//				elementId = elementId.replace(" ","");
//				
//			}
//	}
//	
//	
//	
//}
//function resetCookies() {
//document.write("cookies: " + document.cookie);
//
//	var cookies=document.cookie.split(";");
//	//document.write("<br>");		
//	
//	var elementId = null;
//	
//	for(var i=0; i<cookies.length; i++){
//			a=cookies[i];
//			aparts=a.split("=");
//			//document.write("cookie " + i + " length is " + aparts.length);
//			if (aparts.length > 1 && aparts[1] != '') {
//				elementId = aparts[0];
//				elementId = elementId.replace(" ","");
//				//document.write(elementId);
//				DelCookie(elementId);
//			}
//	}
//	
//	
//}








//-->