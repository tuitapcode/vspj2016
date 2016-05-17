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

	//sub.childNodes[2].classList.add("current-subtitle");

	// if (sub_child[2].classList.contains("current-subtitle") == true) {
 //    	sub_child[2].classList.remove("current-subtitle");
 //    }

 //    if (sub_child[2].classList.contains("current-subtitle") == false) {
 //    	sub_child[2].classList.add("current-subtitle");
 //    }

	
}

function updateCurrentTime() {
    document.getElementById("curVideoTime").innerHTML = getCurrentTime();
    
    //updateSubtitle();
    
}

function updateSubtitle() {
	var sub_len = sub.childNodes.length;
	var sub_child = sub.childNodes;

	

    /*for(i=0; i<sub_len; i++) {
    	console.log(i);
    	console.log(sub_child[i]);

    	if (sub_child[i].nodeName != "A")
    	{
    		console.log(sub_child[i].nodeName != "A");
    		continue;
    	}
    }*/

	for(i=0; i<sub_len; i++)
    {

    	if (sub_child[i].nodeName != "A")
    	{
    		continue;
    	}
    	


    	var curElement = sub_child[i];
    	var curElementTime = parseFloat(hmsToSecondsOnly(curElement.getAttribute("title")));

    	var curVideoTime = parseFloat(getCurrentTime());

    	var nextElement = null;
    	var nextElementTime = null;

    	

    	if (sub_child[i+1].nodeName == "A")
    	{
    		nextElement = sub_child[i+1];
    		nextElementTime = parseFloat(hmsToSecondsOnly(nextElement.getAttribute("title")));

    		console.log("time: " + curVideoTime + "-" +curElementTime + "-" + nextElementTime);
    		if ((curVideoTime >= curElementTime) && (curVideoTime < nextElementTime) ) {
    			if (curElement.classList.contains("current-subtitle") == false)
	        		curElement.classList.add("current-subtitle");
	        }
	        else {
	        	if (curElement.classList.contains("current-subtitle") == true)
	        		curElement.classList.remove("current-subtitle");
	        }
    	}
    	else {
    		if (curVideoTime >= curElementTime) {
    			if (curElement.classList.contains("current-subtitle") == false)
	        		curElement.classList.add("current-subtitle");
	        }
	        else {
	        	if (curElement.classList.contains("current-subtitle") == true)
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

	resetHighLight();
	if (item.classList.contains("current-subtitle") == false)
		item.classList.add("current-subtitle");
	event.preventDefault();
}

function resetHighLight(){
	var sub_len = sub.childNodes.length;
	var sub_child = sub.childNodes;

	for(i=0; i<sub_len; i++)
    {

    	if (sub_child[i].nodeName != "A")
    	{
    		continue;
    	}

    	var curElement = sub_child[i];

    	if (curElement.classList.contains("current-subtitle") == true)
			curElement.classList.remove("current-subtitle");
    }
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