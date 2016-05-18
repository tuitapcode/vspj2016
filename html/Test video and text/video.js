/**
* [x] Click vào subtitle --> Set Current Time
* [x] Highlight subtitle khi video playing...
* [ ] Auto scroll khi video play...
*/

var vid = null;
var link_incr_time = null;
var sub = null;
var bPlay = null;
var timer = null;


window.onload = function() {
	vid = document.getElementById("j_video");
    initVideo();
    
	//vid.ontimeupdate = updateCurrentTime;

    //bPlay = document.getElementById("bJea");
    //bPlay.onclick = vPause;//initVideo;
    //setInterval(updateCurrentTime, 3000);

	link_incr_time = document.getElementById("v_incr_time");
	link_incr_time.onclick = IncrCurrentTime;

	sub = document.getElementById("subtitle");
	removeTextChild();
	//console.log(sub.childNodes.length);
	sub.onclick = xuly;

    /*timer = setInterval(updateCurrentTime, 100);
    console.log(timer);
    clearInterval(timer);
    timer = null;
    console.log(timer);*/
}

function vPause() {
    vid.pause();
}


function initVideo(){
    vid.play(); //start loading, didn't used `vid.load()` since it causes problems with the `ended` event

    if(vid.readyState !== 4){ //HAVE_ENOUGH_DATA
        vid.addEventListener('canplaythrough', onCanPlay, false);
        vid.addEventListener('load', onCanPlay, false); //add load event as well to avoid errors, sometimes 'canplaythrough' won't dispatch.
        vid.addEventListener("timeupdate", updateCurrentTime);
        
        setTimeout(function(){
            vid.pause(); //block play so it buffers before playing
        }, 1); //it needs to be after a delay otherwise it doesn't work properly.

    }else{
        //video is ready

    }

    

    //timer = setInterval(updateCurrentTime, 100);
    //vid.addEventListener("playing", onPlayVideo);
    //vid.addEventListener("pause", onPauseVideo);
    //vid.addEventListener("timeupdate", updateCurrentTime);

    

}

function onCanPlay(){
    vid.removeEventListener('canplaythrough', onCanPlay, false);
    vid.removeEventListener('load', onCanPlay, false);
    //video is ready
    //vid.play();
    //vid.pause();
}

/*function onPauseVideo(){
    clearInterval(timer);
    timer = null;
    console.log("onPauseVideo");
    console.log(timer);
}*/

/*function onPlayVideo(){
    timer = setInterval(updateCurrentTime, 100);
    console.log("onPlayVideo");
    console.log(timer);
}*/

function scrollToElement(pageElement) {    
    var positionX = 0,         
        positionY = 0;    

    while(pageElement != null){        
        positionX += pageElement.offsetLeft;        
        positionY += pageElement.offsetTop;        
        pageElement = pageElement.offsetParent;        
        window.scrollTo(positionX, positionY);    
    }
}

function updateCurrentTime() {
    document.getElementById("curVideoTime").innerHTML = getCurrentTime();
    
    updateSubtitle();
    
}

function updateSubtitle() {
	var sub_len = sub.childNodes.length;
	var sub_child = sub.childNodes;

	for(i=0; i<sub_len; i++)
    {
    	//console.log(sub_child[i]);

    	if (sub_child[i].nodeName != "A")
    	{
    		continue;
    	}
    	
    	var curElement = sub_child[i];
    	var curElementTime = parseFloat(hmsToSecondsOnly(curElement.getAttribute("title")));

    	var curVideoTime = parseFloat(getCurrentTime());

    	var nextElement = null;
    	var nextElementTime = null;

        //Remove tat ca tru nodeName "A"
    	if (i < (sub_len-1))
    	{
    		nextElement = sub_child[i+1];
    		nextElementTime = parseFloat(hmsToSecondsOnly(nextElement.getAttribute("title")));

    		console.log("time: " + curVideoTime + "-" +curElementTime + "-" + nextElementTime);
    		if ((curVideoTime >= curElementTime) && (curVideoTime < nextElementTime) ) {
    			if (curElement.classList.contains("current-subtitle") == false) {
                    curElement.classList.add("current-subtitle");
                    sub.scrollTop = curElement.offsetTop - sub.offsetTop - 10;
                    //curElement.scrollIntoView({block: "start", behavior: "smooth"});
                }
	        		
	        }
	        else {
	        	if (curElement.classList.contains("current-subtitle") == true)
	        		curElement.classList.remove("current-subtitle");
	        }
    	}
    	else {
    		if (curVideoTime >= curElementTime) {
    			if (curElement.classList.contains("current-subtitle") == false)
                {
                    curElement.classList.add("current-subtitle");
                    sub.scrollTop = curElement.offsetTop - sub.offsetTop - 10;
                }
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

function removeTextChild(){

	for(i=0; i<sub.childNodes.length; i++)
    {
    	if (sub.childNodes[i].nodeName != "A")
    	{
    		sub.removeChild(sub.childNodes[i]);
    		i--;
    	}
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