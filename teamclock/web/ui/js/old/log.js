var isLogOn = false;

function log(message)
{
	if(isLogOn){
		var logdiv = document.getElementById("log");
		if(logdiv!= null){
		var newDiv = document.createElement("div");
		logdiv.appendChild(newDiv);
		
			newDiv.innerHTML += message;
		}
	}
}