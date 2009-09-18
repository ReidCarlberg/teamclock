<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
<head>
<title>Listing</title>
<script>

function show(x){
	if(x != null)
	{
		x.style.display='block';
	}
}

function hide(x){

	if(x != null)
	{
		x.style.display='none';
	}
}

function toggle(off, on) {

	var elementOff=document.getElementById(off);
	var elementOn=document.getElementById(on);
	
	hide(elementOff);
	show(elementOn);

}




</script>


</head>
<body>

<h1>Preview Or Code</h1>

<p>
<a href="#" onclick="javascript:toggle('code','preview');">Preview</a> or 
<a href="#" onclick="javascript:toggle('preview','code');">Code</a>

<div id="code" style="display: none;">

<h1>Cut and paste this code.</h1>
<form>


<textarea cols="70" rows="30">
<webwork:property value="mergedData" escape="'false'" />
</textarea>
</form>
</div>


<div id="preview" style="display: block;">

<webwork:property value="mergedData" escape="'false'" />

</div>

</body>