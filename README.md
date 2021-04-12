# MAYCAN Project 

**+) apk 파일 위치 : client\app-debug.apk**


**+) 앱과 파이썬의 연동성 문제로 인해 서버가 잠시 끊길 때가 있습니다. 혹시 그런 일이 발생한다면 연락부탁드립니다.**



**☎ 서버 관리자 이승희 : 010 3833 9124**

[![Gmail Badge](https://img.shields.io/badge/Gmail-d14836?style=flat-square&logo=Gmail&logoColor=white&link=mailto:rmaskdus0208@gmail.com)](mailto:rmaskdus0208@gmail.com)




### Hi there 👋

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fhuiseon37&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)


## **App Logo**

<img src="https://user-images.githubusercontent.com/68985625/113411433-adaa8600-93f0-11eb-9d71-2a793d1774e5.jpg" width="40%">


# 
## **Introduction**




* 미국 시장조사기관인 퓨 리서치(Pew Research)가 2019년 세계 27개 국가를 대상으로 스마트폰 보급률을 조사한 결과 한국이 95%로 1위를 차지하였다. 2020년 2월 (주)카카오가 발표한 기업설명활동 보고서에 따르면 카카오톡은 이용시간 기준 메신저 시장점유율은 96%이다. 이처럼 스마트폰 보급율이 높은 대한민국에서 가장 시장 점유율이 높은 메신저인 카카오톡은 이제 실생활과 뗄 수 없는 필수 메신저이며 사람들은 카카오톡과 일상의 큰 부분을 공유하고 있다. 

* 여러 소통의 창구로 쓰이는 카카오톡에서 본 프로젝트가 주목한 부분은 대학생들의 조별과제를 위한 플랫폼으로서의 역할이다. 특히 언택트 사회인 지금, 대학생들은 대면보다는 비대면으로 조별과제를 수행하게 되는데, 직접 만나지 못하고 과제를 수행하다보니 이른바 “프리라이더”라 불리는 무임승차자가 발생할 확률이 높아졌다. 
* 따라서 범용적인 플랫폼인 카카오톡의 특성을 이용하여 카카오톡 채팅방에서 여러 피쳐를 추출하여 각 조원들의 기여도를 측정하고 이를 제시하여 무임승차를 방지하고자 이 앱을 제작하게 되었다.
*  더불어 여러 피쳐(웃음왕, 궁금왕, 이모티콘왕, 물음표왕, 느낌표왕)를 분석하여 흥미 유발을 위해 힘썼다.








## **실행 화면**

### 
#### -시작 화면, 업로드 화면

<img src="https://user-images.githubusercontent.com/68985625/113420173-d1c39280-9403-11eb-9a3c-1304879ccb3b.jpg" width="40%">&nbsp;<img src="https://user-images.githubusercontent.com/68985625/113420175-d2f4bf80-9403-11eb-9db4-62749efc2056.jpg" width="40%">

### 
#### -업로드 화면; 로컬 저장소 접근

<img src="https://user-images.githubusercontent.com/68985625/113420178-d38d5600-9403-11eb-94c8-76d36e57c987.jpg" width="40%">

### 
#### -로컬 저장소에서 업로드, 로딩

<img src="https://user-images.githubusercontent.com/68985625/113420179-d38d5600-9403-11eb-8b21-f9e2daa1f96a.jpg" width="40%">

### 
#### -결과창(기여도, 왕 )

<img src="https://user-images.githubusercontent.com/68985625/113420180-d425ec80-9403-11eb-849c-149715436808.jpg" width="40%">&nbsp;<img src="https://user-images.githubusercontent.com/68985625/113420182-d425ec80-9403-11eb-9d5d-c8ec85539f7b.jpg" width="40%">



## **실행 영상**
![시연영상](https://user-images.githubusercontent.com/68985625/113429510-9df06900-9413-11eb-88a2-36dcee6b8872.gif)



# 
## **Development Environment**

### * Firebase Realtime Database/Storage

### * TCP/IP Socket

### * AWS EC2 instance

### * Android Studio 4.1.1 

  * compileSDKversion : 30
  * buildToolsversion : 30.0.1
  * SDK 11(API Level 30) 기준 호환

  

  

  
# 
## **Process**



### *1. Data Analysis*



#### * Define Problem

  카톡 대화내용을 기반으로 프로젝트의 기여도를 측정하고 참여가 저조한 조원을 찾아낸다. 앱을 통하여 카톡 대화 데이터를 입력받고 분석을 통하여 기여도를 추출하는 과정을 가진다.





#### * Data Collection

  카톡 데이터를 통하여 프로젝트의 기여도를 측정하기 때문에 프로젝트에 관련한 카톡데이터를 수집하였다.





#### * Data Processing

  카톡 데이터를 수집하는 과정에서 웹으로 카톡 대화 내용을 추출한 파일과 안드로이드 앱과 IOS 앱을 통하여 카톡 대화내용을 추출한 파일의 형식이 다르다는 점을 발견하였다. 그렇기에 전처리하는 과정에서 정규표현식을 이용하여 각 파일에 대하여 동일하게 분석을 진행하였다. 각 feature들을 Engineering하기에 앞서 각 feature들의 범위가 다르기 때문에 Minmaxscaler를 통하여 각 feature들의 범위를 1부터 5까지 Scaling하였다.





#### * Feature Engineering

  조원들의 기여도를 측정함에 있어서 필요한 Feature들을 회귀 분석을 통하여 선정하였다. 회귀분석에 의해 기여도에 영향을 많이 주는 feature들의 계수는 다음과 같다.  

  Feature & Coefficient

  * 카톡 빈도수(Frequency): 10
  * 카톡 빈도수 대비 길이(per): 4
  * 물음표의 개수: 2
  * 느낌표의 개수: 2
  * 공지, 투표, 그룹콜 수(leadership): 8
  * 파일, 링크, 사진, 동영상 전송 횟수(prt_rate): 6





#### * Visualization and Insight

  회귀분석을 통하여 각 feature들에 가중치를 주어 조원들의 참여 점수를 Score로설정하였다. Score를 기준으로 각 조원들의 기여도의 백분율을 Pie chart로 표현하였다.  추가적인 구현으로는 ㅋ을 많이 보낸 사람, ㅠ를 많이 보낸 사람, !을 많이 보낸 사람, ?을 많이 보낸 사람, 이모티콘을 많이 보낸 사람을 선정하여 출력하였다. 





### *2. App*



#### * Server

  AWS EC2 인스턴스를 이용하여 가상 서버를 실행하고 server.py 파일을 비롯한 서버 환경을 설치하여 인스턴스 내에서 서버 환경을 구축하였다.

  

#### * DB

  Firebase Realtime Database를 사용하였다.

  

#### * Storage

  이미지 파일과 txt 파일을 더 손 쉽게 입출력 하기 위해 Firebase 내의 Storage 시스템을 사용하였다.

  

#### * TCP/IP Socket 통신

   App과 데이터 분석 처리의 통신을 위해 Android Studio와 Python의 TCP/IP Socket 통신을 이용하였다. 

  

#### * Process

  어플과 Python에서 Socket으로 서버를 열고 파일을 Firebase DB에 올린 후 server.py에 통신을 보내면 python 에서 socket 통신을 받아 데이터 분석을 시작한다. 데이터 분석이 끝나면 python은 결과 값과 이미지를 각 Firebase Realtime Database와 Storage에 업데이트 하고 서버에 “show” Socket 메시지를 전송한다. 그 후 어플에서 socket 메시지를 받으면 분석 한 결과를 어플 프론트에 띄워 보여주도록 구현하였다.




