#!/usr/bin/python -tt
# -*- coding: utf-8 -*-

from xml.dom.minidom import parse
from BeautifulSoup import BeautifulSoup
import xml.dom.minidom
import subprocess, sys, os, urllib, urlparse
import datetime
import urllib2
import requests
import comtypes
import comtypes.client as cc

# Download file ve
def download_file(url,filename):
    print "# Downloading... %s...\r\n -> %s\r\n"  % (urlparse.urlsplit(url).path.split("/")[-1], url)
    urllib.urlretrieve (url, filename)
    print "# --> Saved:\r\n -> %s\r\n"  % filename

# Deu loi roi (error) thi het len TRUE
def is404(url):
    headers = { 'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36' }
    if requests.head(url, headers=headers).status_code == 404:
        return True
    elif requests.head(url, headers=headers).status_code == 403:
        return True
    else:
        return False

# "01" not 1, "02" not 2
def day_to_str(input):
    if ((int(input)) < 10):
        return "0" + str(int(input))
    else:
        return str(int(input))

jday = {
    "Sun" : "NONE",
    "Mon" : "mon",
    "Tue" : "tues",
    "Wed" : "weds",
    "Thu" : "thurs",
    "Fri" : "fri",
    "Sat" : "NONE"
}

# y = "2015"
# m = "12"
# m_d = "16"
# d = 15

year = raw_input("Year: ")
month = day_to_str(raw_input("Month: "))
curDay = day_to_str(raw_input("Day: "))

# d = Yesterday
yesterday = day_to_str(int(curDay)-1)

folder_name = "%s-%s-%s" % (year, month, curDay)

# Yesterday
yDay = "%s/%s/%s" % (year, month, yesterday)

# Now
cDay = "%s%s" % (month, curDay)

format = "%a"

d = datetime.date(int(year),int(month), int(curDay))
Name_of_Day = jday[d.strftime(format)]

if (Name_of_Day == "NONE"):
    N_of_D = ""
    if (d.strftime(format) == "Sun"):
        N_of_D = "Chu nhat"
    else:
        N_of_D = "Thu bay"
    print "%s, ngay %s thang %s nam %s --> CNN khong lam viec." % (N_of_D, curDay, month, year)
    sys.exit(0)

print "%s, %s-%s-%s (%s)" % (Name_of_Day, year, month, curDay, yesterday)

url_str = ""
if (int(year) <2015):
    url_str = "education"
else:
    url_str = "studentnews"

#http://ht.cdn.turner.com/cnn/big/education/2014/09/22/orig-sn-0923.cnn.scc
#http://ht.cdn.turner.com/cnn/big/education/2014/09/22/orig-sn-0923.cnn.smil
#http://ht.cdn.turner.com/cnn/big/studentnews/2015/01/04/orig-sn-0105.cnn.smil
#http://ht.cdn.turner.com/cnn/big/studentnews/2015/11/15/sn-1116.cnn.smil
#http://ht.cdn.turner.com/cnn/big/studentnews/2016/01/03/sn-0104.cnn.smil
#http://edition.cnn.com/video/data/3.0/video/studentnews/2016/01/03/sn-0104.cnn/index.xml?xml=true

dl_folder = "g:\\[_Projects\\CNN Student News\\"
dl_local = dl_folder + folder_name + "\\"

# Dùng download file info --> Mp4, và file scc subtitle
# src_url = "http://ht.cdn.turner.com/cnn/big/"+ url_str + "/" + yDay + "/orig-sn-" + cDay + ".cnn"

# Không dùng nữa
# img_url = "http://edition.cnn.com/" + yDay + "/studentnews/sn-content-" + Name_of_Day + "/index.html"

url_apiv3 = "http://edition.cnn.com/video/data/3.0/video/studentnews/" + yDay + "/sn-" + cDay + ".cnn/index.xml?xml=true"

# src_url_bak = "http://ht.cdn.turner.com/cnn/big/"+ url_str + "/" + yDay + "/sn-" + cDay + ".cnn"
    
#Setup cho file smil, url mp4
# smil_url = src_url + ".smil"
# if(is404(smil_url) == True ):
    # print "Loi 404: smil_url \r\n--> %s\r\n=> Use BAK URL..." % (smil_url)
    # smil_url = src_url_bak + ".smil"
    
    # if(is404(smil_url) == True ):
        # print "Loi 404: smil_url bak \r\n--> %s\r\nRe-check code, please!" % (smil_url)
        # sys.exit(0)
        
# smil_filename = dl_local + urlparse.urlsplit(smil_url).path.split("/")[-1]

#Setup cho file scc, subtitle
# scc_url = src_url + ".scc"
# if(is404(scc_url) == True):
    # print "Loi 404: scc_url \r\n--> %s\r\n=> Use BAK URL..." % (scc_url)
    # scc_url = src_url_bak + ".scc"
    
    # if(is404(scc_url) == True ):
        # print "Loi 404: scc_url bak \r\n--> %s\r\nRe-check code, please!" % (scc_url)
        # sys.exit(0)
        
# scc_filename = dl_local + urlparse.urlsplit(scc_url).path.split("/")[-1]

#Setup phần tạo thư mục yyyy-mm-dd
if not os.path.exists(dl_local):
    print "# Make %s folder..." % (dl_local)
    os.makedirs(dl_local)

#Setup cho file APIv3 XML
#url_apiv3
apiv3_filename = dl_local + "sn-" + cDay + ".cnn.xml"
img_url = ""
jlink = ""
scc_url = ""
smil_url = ""
if(is404(url_apiv3) != True):
    download_file (url_apiv3, apiv3_filename)
    DOMTree = xml.dom.minidom.parse(apiv3_filename)
    collection = DOMTree.documentElement
    
    images = collection.getElementsByTagName("image")
    for i in images:
        if i.getAttribute("name") == "1600x900":
            img_url = i.firstChild.nodeValue
            print "# Img: %s" % (img_url)
    
    files = collection.getElementsByTagName("file")
    for f in files:
        if f.getAttribute("bitrate") == "1280x720_3500k_mp4":
            jlink = "http://ht.cdn.turner.com/cnn/big%s" % (f.firstChild.nodeValue)
            print "# Mp4: %s" % (jlink)
        if f.getAttribute("bitrate") == "scc":
            scc_url = "http://ht.cdn.turner.com/cnn/big%s" % (f.firstChild.nodeValue)
            print "# SCC: %s" % (scc_url)
        if f.getAttribute("bitrate") == "smil":
            smil_url = "http://ht.cdn.turner.com/cnn/big%s" % (f.firstChild.nodeValue)
            print "# smil: %s" % (smil_url)
    #/studentnews/2016/05/15/sn-0516.cnn.scc
    
else:
    print "Loi 404: url_apiv3 \r\n--> %s\r\n=> APIv3 not download." % (url_apiv3)
    sys.exit(0)

# f = urllib.urlopen(img_url)
# img_page = f.read()
# f.close()          
# soup = BeautifulSoup(img_page)

##Parse html va get img
# img_url = soup.find(id="large-media_0--thumbnail").img.get("data-src-full16x9")
# img_filename =  dl_local + urlparse.urlsplit(img_url).path.split("/")[-1]

smil_filename = dl_local + urlparse.urlsplit(smil_url).path.split("/")[-1]
scc_filename = dl_local + urlparse.urlsplit(scc_url).path.split("/")[-1]
img_filename =  dl_local + urlparse.urlsplit(img_url).path.split("/")[-1]

# Keo ve --> update thread neu can
download_file (smil_url, smil_filename)
download_file (scc_url, scc_filename)
download_file (img_url, img_filename)

# Parse con XML lay URI
# Open XML document using minidom parser urllib.urlopen(smil_url)

# DOMTree = xml.dom.minidom.parse(smil_filename)
# collection = DOMTree.documentElement
# if collection.hasAttribute("shelf"):
   # print "Root element : %s" % collection.getAttribute("shelf")

# videos = collection.getElementsByTagName("video")
# jlink = ""


# for v in videos:
   # if v.getAttribute("system-bitrate") == "3500000":
      # jlink = "http://ht.cdn.turner.com/%s" % v.getAttribute("src")
      # print "Video URL:\r\n -> %s\r\n"  % jlink


### Set up IDM

bstrUrl = jlink #Link cần download
bstrReferer = ""
bstrCookies = ""
bstrData = ""
bstrUser = ""
bstrPassword = ""

bstrLocalPath = dl_folder + folder_name #Thư mục lưu file

bstrLocalFileName = "" #Tên file (unicode)

#Flags, can be zero or a combination of the following values: 
#  1 - do not show any confirmations dialogs;
#  2 - add to queue only, do not start downloading.
lFlags = 2

jdl_filename = urlparse.urlsplit(jlink).path.split("/")[-1]

cc.GetModule("IDManTypeInfo.tlb")
import comtypes.gen.IDManLib as IDManLib
idm = cc.CreateObject("IDMan.CIDMLinkTransmitter", None, None, IDManLib.ICIDMLinkTransmitter)
idm.SendLinkToIDM(bstrUrl, bstrReferer, bstrCookies, bstrData, bstrUser, bstrPassword, bstrLocalPath, bstrLocalFileName, lFlags)
print "### Add to IDM...\r\nURL: -> %s\r\nSave to: -> %s\r\nName: -> %s"  % (bstrUrl, bstrLocalPath, jdl_filename)