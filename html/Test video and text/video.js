/**
* [x] Click vào subtitle --> Set Current Time
* [ ] Highlight subtitle khi video playing...
*/

var vid = null;
var link_incr_time = null;
var sub = null;


window.onload = function() {

	vid = document.getElementById("j_video");
	vid.ontimeupdate = updateCurrentTime;

	link_incr_time = document.getElementById("v_incr_time");
	link_incr_time.onclick = IncrCurrentTime;

	sub = document.getElementById("subtitle");
	sub.onclick = xuly;

	/*c = sub.childNodes;
    var txt = c.length + "<br>";
    var i;
    for (i = 0; i < c.length; i++) {
        txt = txt + c[i].nodeName + "<br>";
    }*/

	console.log(sub.childNodes[0].nodeName == "#text");

	/*if (sub.childNodes[1].classList.contains("current-subtitle") == false {
    	sub.childNodes[1].classList.add("current-subtitle");
    }*/
}

function updateCurrentTime() {
    document.getElementById("curVideoTime").innerHTML = getCurrentTime();
    

    
    //updateSubtitle();
    
}

function updateSubtitle() {
	console.log("Jea?");
	for(var i=0; i<sub.length; i++)
    {
    	console.log("Jea?");
    	var curElement = sub[i];
    	var curElementTime = parseFloat(hmsToSecondsOnly(item.getAttribute("title")));

    	var curVideoTime = parseFloat(getCurrentTime());

    	var nextElement = null;
    	var nextElementTime = null;

    	if (i < (sub.length-1))
    	{
    		nextElement = sub[i+1];
    		nextElementTime = parseFloat(hmsToSecondsOnly(item.getAttribute("title")));

    		if (curVideoTime > curElementTime && curVideoTime < nextElementTime ) {
	        	curElement.classList.add("current-subtitle");
	        }
	        else {
	        	curElement.classList.remove("current-subtitle");
	        }
    	}
    	else if (i == (sub.length-1)) {
    		if (curVideoTime > curElementTime) {
	        	curElement.classList.add("current-subtitle");
	        }
	        else {
	        	curElement.classList.remove("current-subtitle");
	        }
    	}
    }
}

function IncrCurrentTime(event) {
	SetCurrentTime(getCurrentTime() + 1);
	event.preventDefault();
}

function xuly(event){
	var item = event.target;
	var time = hmsToSecondsOnly(item.getAttribute("title"));
	SetCurrentTime(time);
	console.log(time);
	event.preventDefault();
}


function getCurrentTime() {
	return vid.currentTime;
}

function SetCurrentTime(time) {
	vid.currentTime = time;
}

function hmsToSecondsOnly(str) {
    var p = str.split(':'),
        s = 0, m = 1;

    while (p.length > 0) {
        s += m * parseInt(p.pop(), 10);
        m *= 60;
    }

    return s;
}