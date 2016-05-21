/**
* [x] Click vào subtitle --> Set Current Time
* [x] Highlight subtitle khi video playing...
* [x] Auto scroll khi video play...
* [x] Xử lý tối ưu với timeupdate...
* [x] Xử lý Smooth Scrolling... xử lý khi scroll ở bottom bị lỗi, lặp vô hạn --> ko dùng nữa
* [x] Lỗi hiển thị khi click vào subtitle --> do css hover, fix bay back-end
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

var topPadding = 30;
var bAutoScroll = null;
var flagAutoScroll = false;

var logs = null;


window.onload = function() {
    logs = document.getElementById("j_log");
    //logs.innerHTML = "?!";
    //removeHoverCSSRule();
    init_CSS();

	vid = document.getElementById("j_video");
    initVideo();
    
	//vid.ontimeupdate = updateCurrentTime;

	link_incr_time = document.getElementById("v_incr_time");
	link_incr_time.onclick = IncrCurrentTime;

    flagAutoScroll = false;
    bAutoScroll = document.getElementById("j_auto_scroll");
    bAutoScroll.onclick = toggleAutoScroll;

    

	sub = document.getElementById("subtitle");
    sub_container = document.getElementById("sub_container");
	
    removeTextChild();

    lastSubtitleTime = parseFloat(hmsToSecondsOnly(sub.childNodes[sub.childNodes.length-1].getAttribute("title")));
	sub.onclick = onClick_subtitle;

    
}

function init_CSS() {
    addLogs(navigator.userAgent);
    var elm, head;
    head = document.getElementsByTagName('head')[0] || document.body || document.documentElement;

    if (detectmob() == true) {
        

        elm = document.createElement('link');
        elm.rel = "stylesheet";
        elm.href = "style-mobile.css";
        head.appendChild(elm);
        addLogs("Mobile");
        // elm = document.createElement('script');
        // elm.src = "/s/jquery.dropkick-1.0.0.js";
        // head.appendChild(elm);
    }
    else {
        elm = document.createElement('link');
        elm.rel = "stylesheet";
        elm.href = "style.css";
        head.appendChild(elm);
        addLogs("Khác");
    }
}

function detectmob() { 
 if( navigator.userAgent.match(/Android/i)
 || navigator.userAgent.match(/webOS/i)
 || navigator.userAgent.match(/iPhone/i)
 || navigator.userAgent.match(/iPad/i)
 || navigator.userAgent.match(/iPod/i)
 || navigator.userAgent.match(/BlackBerry/i)
 || navigator.userAgent.match(/Windows Phone/i)
 ){
    return true;
  }
 else {
    return false;
  }
}

function removeHoverCSSRule() {
  if ('amara-transcript-line' in document) {
    try {
      var ignore = /:hover/;
      for (var i = 0; i < document.styleSheets.length; i++) {
        var sheet = document.styleSheets[i];
        if (!sheet.cssRules) {
          continue;
        }
        for (var j = sheet.cssRules.length - 1; j >= 0; j--) {
          var rule = sheet.cssRules[j];
          if (rule.type === CSSRule.STYLE_RULE && ignore.test(rule.selectorText)) {
            sheet.deleteRule(j);
          }
        }
      }
    }
    catch(e) {
    }
  }
}

function toggleAutoScroll() {
    if (flagAutoScroll == false) {
        bAutoScroll.innerHTML = "Auto Scroll: On";
        flagAutoScroll = true;
        AutoScroll(curSubtitle);
    }
    else {
        bAutoScroll.innerHTML = "Auto Scroll: Off";
        flagAutoScroll = false;
    }
    //console.log(flagAutoScroll);
}

function AutoScroll(curSub){
    if (flagAutoScroll == true) {
        sub_container.scrollTop = curSub.offsetTop - sub_container.offsetTop - topPadding;
    }
}

function vPause() {
    vid.pause();
}


function initVideo(){
    vid.play(); //start loading, didn't used `vid.load()` since it causes problems with the `ended` event

    if(vid.readyState !== 4){ //HAVE_ENOUGH_DATA
        vid.addEventListener('canplaythrough', onCanPlay, false);
        vid.addEventListener('load', onCanPlay, false); //add load event as well to avoid errors, sometimes 'canplaythrough' won't dispatch.
        
        //Event theo thời gian
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

/*function scrollToPosition(pos) {
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
}*/

function updateCurrentTime() {
    document.getElementById("curVideoTime").innerHTML = getCurrentTime();
    
    updateSubtitle();
    
}

function updateSubtitle() {
	var sub_len = sub.childNodes.length;
	var sub_child = sub.childNodes;

    var curVideoTime = parseFloat(getCurrentTime());

    
    //Ràng buột
    //Tối ưu, giảm số lần phải nhảy vô for, dựa trên thời gian.
    if ( (curSubtitleTime !== null) && (nextSubtitleTime !== null) && (lastSubtitleTime !== null) )
    {
        if ( ( (curVideoTime < nextSubtitleTime) && (curVideoTime > curSubtitleTime) ) || (curVideoTime > lastSubtitleTime + 1) ) {
            //console.log("break");
            return;
        }
    }
    //console.log("contious");

    /*if (scrollTimer === null && curSubtitlePostion!== null)
    {
        if (Math.abs(sub_container.scrollTop - curSubtitlePostion) > 10)
        {
            scrollToPosition(curSubtitlePostion);
        }
    }*/


	for(var i=0; i<sub_len; i++)
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
                    

                    curSubtitleTime = curElementTime;
                    nextSubtitleTime = nextElementTime;
                    //subScrollTop(curElement);
                    /*if (sub_container.scrollTop < (curElement.offsetTop - sub_container.offsetTop - 10)) {
                        sub_container.scrollTop += 5;//curElement.offsetTop - sub_container.offsetTop - 10;
                    }*/
                    //sub_container.scrollTop = curElement.offsetTop - sub_container.offsetTop - 30;
                    curSubtitle = curElement;
                    curSubtitlePostion = curElement.offsetTop - sub_container.offsetTop - topPadding;
                    AutoScroll(curSubtitle);
                    // addLogs(count_cur_sub());
                    //curElement.scrollIntoView({block: "start", behavior: "smooth"});
                    
                    //[Tối ưu] Tìm ra rồi thì STOP
                    //break;
                }
	        		
	        }
	        else {
	        	if (curElement.classList.contains("current-subtitle") == true)
	        		curElement.classList.remove("current-subtitle");
	        }
    	}
    	else { //Dùng cho last node
    		if (curVideoTime >= curElementTime) {
    			if (curElement.classList.contains("current-subtitle") == false)
                {
                    curElement.classList.add("current-subtitle");
                    //sub.scrollTop = curElement.offsetTop - sub.offsetTop - 30;
                    curSubtitle = curElement;
                    curSubtitlePostion = curElement.offsetTop - sub_container.offsetTop - topPadding;
                    // AutoScroll(curSubtitle);
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
	SetCurrentTime(getCurrentTime() + 20);
	event.preventDefault();
}

function addLogs(text) {
    logs.innerHTML += text + "<br>";
}

function onClick_subtitle(event){
    resetHighLight();

    

	var item = event.target;

    //console.log(item);
    //console.log(item.nextSibling);

    //curSubtitlePostion = item.offsetTop - sub_container.offsetTop - 30;
    //clearInterval(scrollTimer);
    //scrollTimer = null;

	var time = hmsToSecondsOnly(item.getAttribute("title"));
    
    // if (item.nextSibling != undefined)
    // {
    //     curSubtitleTime = parseFloat(time);
    //     nextSubtitleTime = parseFloat(hmsToSecondsOnly(item.nextSibling.getAttribute("title")));
    // }

	SetCurrentTime(time);

	// if (item.classList.contains("current-subtitle") == false)
	// 	item.classList.add("current-subtitle");
    // addLogs(count_cur_sub());

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

function count_cur_sub(){
    var sub_len = sub.childNodes.length;
    var sub_child = sub.childNodes;

    var nCount = 0;

    for(i=0; i<sub_len; i++)
    {
        if (sub_child[i].nodeName != "A")
        {
            continue;
        }

        var curElement = sub_child[i];
        

        if (curElement.classList.contains("current-subtitle") == true)
        {
            nCount += 1;
        }
            
    }
    return nCount;
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