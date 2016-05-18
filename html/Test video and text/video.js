/**
* [x] Click vào subtitle --> Set Current Time
* [x] Highlight subtitle khi video playing...
* [x] Auto scroll khi video play...
* [x] Xử lý tối ưu với timeupdate...
* [ ] Xử lý Smooth Scrolling... xử lý khi scroll ở bottom bị lỗi, lặp vô hạn
*/

var vid = null;
var link_incr_time = null;
var sub = null;
var sub_container = null;
var bPlay = null;
var timer = null;
var curSubtitle = null;
var nextSubtitle = null;

var curSubtitleTime = null;
var nextSubtitleTime = null;

var lastSubtitleTime = null;

var scrollTimer = null;
var scrollCounter = null;
var curSubtitlePostion = null;


window.onload = function() {
	vid = document.getElementById("j_video");
    initVideo();
    
	//vid.ontimeupdate = updateCurrentTime;

	link_incr_time = document.getElementById("v_incr_time");
	link_incr_time.onclick = IncrCurrentTime;

	sub = document.getElementById("subtitle");
    sub_container = document.getElementById("sub_container");
	
    removeTextChild();

    lastSubtitleTime = parseFloat(hmsToSecondsOnly(sub.childNodes[sub.childNodes.length-1].getAttribute("title")));
	sub.onclick = xuly;

    
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
}

function onCanPlay(){
    vid.removeEventListener('canplaythrough', onCanPlay, false);
    vid.removeEventListener('load', onCanPlay, false);
    //video is ready
    //vid.play();
    //vid.pause();
}

/*function scrollToElement(pageElement) {    
    var positionX = 0,         
        positionY = 0;    

    while(pageElement != null){        
        positionX += pageElement.offsetLeft;        
        positionY += pageElement.offsetTop;        
        pageElement = pageElement.offsetParent;        
        window.scrollTo(positionX, positionY);    
    }
}*/

function scrollToPosition(pos) {
    //scrollCounter = 0;
    scrollTimer = setInterval(function(){

        console.log(pos);
        //sub_container.scrollTop = curElement.offsetTop - sub_container.offsetTop - 10;
        if ( (sub_container.scrollTop - pos) < 0)
        {
            sub_container.scrollTop += 5;
        }
        else {
            sub_container.scrollTop -= 5;
        }
        


        if (Math.abs(sub_container.scrollTop - pos) < 10)
        {
            clearInterval(scrollTimer);
            scrollTimer = null;
            console.log(scrollTimer);
        }
    }, 20);
}

function updateCurrentTime() {
    document.getElementById("curVideoTime").innerHTML = getCurrentTime();
    
    updateSubtitle();
    
}

function updateSubtitle() {
	var sub_len = sub.childNodes.length;
	var sub_child = sub.childNodes;
    var curVideoTime = parseFloat(getCurrentTime());

    

    //Tối ưu, giảm số lần phải nhảy vô for, dựa trên thời gian.
    if ( (curSubtitleTime !== null) && (nextSubtitleTime !== null) && (lastSubtitleTime !== null) )
    {
        if ( ( (curVideoTime < nextSubtitleTime) && (curVideoTime > curSubtitleTime) ) || (curVideoTime > lastSubtitleTime + 1) ) {
            console.log("break");
            return;
        }
    }
    console.log("contious");

    /*if (scrollTimer === null && curSubtitlePostion!== null)
    {
        if (Math.abs(sub_container.scrollTop - curSubtitlePostion) > 10)
        {
            scrollToPosition(curSubtitlePostion);
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

    	var nextElement = null;
    	var nextElementTime = null;

        //PHẢI remove tat ca các node TRỪ nodeName "A", nếu không func này sẽ lỗi.
    	if (i < (sub_len-1))
    	{
    		nextElement = sub_child[i+1];
    		nextElementTime = parseFloat(hmsToSecondsOnly(nextElement.getAttribute("title")));

    		//console.log("time: " + curVideoTime + "-" +curElementTime + "-" + nextElementTime);
    		if ((curVideoTime >= curElementTime) && (curVideoTime < nextElementTime) ) {
    			if (curElement.classList.contains("current-subtitle") == false) {
                    curElement.classList.add("current-subtitle");

                    //nextSubtitle = nextElement;
                    //curSubtitle = curElement;
                    curSubtitlePostion = curElement.offsetTop - sub_container.offsetTop - 10;

                    curSubtitleTime = curElementTime;
                    nextSubtitleTime = nextElementTime;
                    //subScrollTop(curElement);
                    /*if (sub_container.scrollTop < (curElement.offsetTop - sub_container.offsetTop - 10)) {
                        sub_container.scrollTop += 5;//curElement.offsetTop - sub_container.offsetTop - 10;
                    }*/
                    sub_container.scrollTop = curElement.offsetTop - sub_container.offsetTop - 10;
                    //curElement.scrollIntoView({block: "start", behavior: "smooth"});
                    break;
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

    //console.log(item);
    //console.log(item.nextSibling);

    curSubtitlePostion = item.offsetTop - sub_container.offsetTop - 10;
    clearInterval(scrollTimer);
    scrollTimer = null;

	var time = hmsToSecondsOnly(item.getAttribute("title"));
    
    if (item.nextSibling != undefined)
    {
        curSubtitleTime = parseFloat(time);
        nextSubtitleTime = parseFloat(hmsToSecondsOnly(item.nextSibling.getAttribute("title")));
    }

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