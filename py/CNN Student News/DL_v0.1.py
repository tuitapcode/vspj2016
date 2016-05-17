#!/usr/bin/python

from xml.dom.minidom import parse
from BeautifulSoup import BeautifulSoup
import xml.dom.minidom
import subprocess, os, urllib, urlparse
import datetime
import urllib2
import requests

# Download file ve
def download_file(url,filename):
    print "Downloading...\r\n -> %s\r\n"  % url
    urllib.urlretrieve (url, filename)
    print "Saved:\r\n -> %s\r\n"  % filename

# Deu loi roi (error) thi het len TRUE
def is404(url):
    headers = { 'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36' }
    if requests.head(url, headers=headers).status_code == 404:
        return True
    elif requests.head(url, headers=headers).status_code == 403:
        return True
    else:
        return False

jday = {
    "Mon" : "mon",
    "Tue" : "tues",
    "Wed" : "weds",
    "Thu" : "thurs",
    "Fri" : "fri"
}

# y = "2015"
# m = "12"
# m_d = "16"

y = raw_input("Year: ")
m = raw_input("Month: ")
m_d = raw_input("Day: ")

# "01" not 1, "02" not 2
if ((int(m_d)-1) < 10):
    d = "0" + str(int(m_d)-1)
else:
    d = str(int(m_d)-1)

folder_name = "%s-%s-%s" % (y, m, m_d)
date1 = "%s/%s/%s" % (y, m, d)
date2 = "%s%s" % (m, m_d)
format = "%a"
d = datetime.date(int(y),int(m), int(m_d))
d_new = jday[d.strftime(format)]

url_str = ""
if (int(y) <2015):
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
src_url = "http://ht.cdn.turner.com/cnn/big/"+ url_str + "/" + date1 + "/orig-sn-" + date2 + ".cnn"
img_url = "http://edition.cnn.com/" + date1 + "/studentnews/sn-content-" + d_new + "/index.html"
url_apiv3 = "http://edition.cnn.com/video/data/3.0/video/studentnews/" + date1 + "/sn-" + date2 + ".cnn/index.xml?xml=true"

src_url_bak = "http://ht.cdn.turner.com/cnn/big/"+ url_str + "/" + date1 + "/sn-" + date2 + ".cnn"

if not os.path.exists(dl_local):
    os.makedirs(dl_local)
    
f = urllib.urlopen(img_url)
img_page = f.read()
f.close()          
soup = BeautifulSoup(img_page)

#Parse html va get img
img_url = soup.find(id="large-media_0--thumbnail").img.get("data-src-full16x9")
img_filename =  dl_local + urlparse.urlsplit(img_url).path.split("/")[-1]

#Setup cho file smil, url mp4
smil_url = src_url + ".smil"
if(is404(smil_url) == True ):
    smil_url = src_url_bak + ".smil"
smil_filename = dl_local + urlparse.urlsplit(smil_url).path.split("/")[-1]

#Setup cho file scc, subtitle
scc_url = src_url + ".scc"
if(is404(scc_url) == True):
    scc_url = src_url_bak + ".scc"
scc_filename = dl_local + urlparse.urlsplit(scc_url).path.split("/")[-1]

#Setup cho file APIv3 XML
#url_apiv3
apiv3_filename = dl_local + "sn-" + date2 + ".cnn.xml"
if(is404(url_apiv3) != True):
    download_file (url_apiv3, apiv3_filename)


# Keo ve --> update thread neu can
download_file (smil_url, smil_filename)
download_file (scc_url, scc_filename)
download_file (img_url, img_filename)

# Parse con XML lay URI
# Open XML document using minidom parser urllib.urlopen(smil_url)
DOMTree = xml.dom.minidom.parse(smil_filename)
collection = DOMTree.documentElement
if collection.hasAttribute("shelf"):
   print "Root element : %s" % collection.getAttribute("shelf")

videos = collection.getElementsByTagName("video")
jlink = ""


for v in videos:
   if v.getAttribute("system-bitrate") == "3500000":
      jlink = "http://ht.cdn.turner.com/%s" % v.getAttribute("src")
      print "Video URL:\r\n -> %s\r\n"  % jlink

jcommand = "\"%programfiles(x86)%\\Internet Download Manager\\IDMan.exe\"" + " /n /a /d \"" + jlink + "\" /p " + "\"" + dl_folder + folder_name + "\""
print "Add to IDM:\r\n -> %s"  % jcommand
subprocess.call(jcommand, shell=True)
