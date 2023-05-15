# 🧀 Chatzy 프로젝트

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/EFUB-CLONE-SLACK/SLACK_BACKEND&count_bg=%23FF7B72&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

## 🥳 백엔드 팀원 소개

<table border="1" cellspacing="0" cellpadding="0" width="90%">
    <tr width="100%">
        <td width="30%" align="center"><a href= "https://github.com/siyeonkm">김시연</a></td>
        <td width="30%" align="center"><a href= "https://github.com/eunseo22mv">김은서</a></td>
        <td width="30%" align="center"><a href= "https://github.com/sunnyineverywhere">이선의</a></td>
    </tr>
    <tr width="100%">
        <td width="30%" align="center"><img src = "https://github.com/siyeonkm.png" width="80%"/></td>
        <td width="30%" align="center"><img src = "https://github.com/eunseo22mv.png" width="80%"/></td>
        <td width="30%" align="center"><img src = "https://github.com/sunnyineverywhere.png" width="80%"/></td>
    </tr>
    <tr width="100%">
       <td width="30%" align="center">[채팅] Websocket과 Stomp를 이용한 1:1 및 n:n 채팅 기능, 채팅방 관련 CRUD 기능  
          </br> [프로필/워크스페이스] 프로필 및 워크스페이스 리팩토링 및 오류해결 진행
          </br> [기타] mongodb 설계, api문서 작성, 리드미 작성 등.</td>
       <td width="30%" align="center">[워크스페이스] 워크스페이스 CRUD 초대메일 발송 기능 
          </br> [프로필] 유저와 1:n 관계인 프로필 관련 CRUD
          </br> [기타] 데이터베이스 설계, api 문서 작성 등</td>
        <td width="30%" align="center">[로그인] Google API를 이용한 Oauth2 로그인/회원가입, JWT와 Redis를 이용한 인가작업 관리 등 
          </br> [유저] 유저 관련 CRUD
          </br> [배포] Travis ci 및 Github Action을 이용한 CICD 파이프라인 구축
          </br> [기타] mongodb 설계, api 문서 작성 등 </td>
   </tr>
</table>


## 🧀 개요
'Chatzy'는 협업을 위한 채팅 웹서비스로, 프로젝트마다 1:1, n:n 채팅방을 자동으로 생성하여 비대면으로도 효율적인 협업을 진행할 수 있도록 도모합니다.

## 🧀 기술 스택    
- DEVELOP &nbsp; 
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=round-square&logo=Spring&logoColor=white) <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/Kakao-FFCD00?style=flat-square&logo=Kakao&logoColor=white"/>

- AWS &nbsp;
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon%20AWS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=AmazonS3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon LoadBalancer-E68B49?style=flat-square&logo=Amazon LoadBalancer&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon CodeDeploy-7D9B4B?style=flat-square&logo=Amazon CodeDeploy&logoColor=white"/>

- ETC &nbsp; 
<img src="https://img.shields.io/badge/GitHub -181717?style=flat-square&logo=GitHub&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub Action-256EE0?style=flat-square&logo=GitHub Action&logoColor=white"/></br>

<img src="https://user-images.githubusercontent.com/99666136/183558301-95416e18-9b6f-455b-a02c-1878840cae5f.png"/>


## 🧀 프로젝트 구조

## 라이브러리
1. spring boot web
2. spring boot mongodb
3. spring boot mail
4. spring boot thymeleaf
5. spring boot security
6. spring boot data redis
7. spring boot websocket
8. spring boot test
9. lombok
10. sockjs-client
11. stomp-websocket
12. gson
13. jjwt

### 폴더 
<pre>
<code>
└── 🗂 main
    ├── 🗂 java
    │   └── 🗂 web
    │       └── 🗂 slack
    │           ├── 📑 SlackCloneProjectApplication.java
    │           ├── 🗂 config
    │           │   ├── 🗂 annotation
    │           │   │   └── 📑 AuthMember.java
    │           │   ├── 🗂 handlers
    │           │   │   └── 📑 ChatPreHandler.java
    │           │   │   └── 📑 CustomLoginSuccessHandler.java
    │           │   │   └── 📑 CustomLogoutSucessHandler.java
    │           │   ├── 🗂 jwt
    │           │   │   └── 📑 JwtAuthenticationFilter.java
    │           │   │   └── 📑 JwtTokenProvider.java
    │           │   ├── 📑 AuthMemberArgumentResolver.java
    │           │   ├── 📑 CorsConfig.java
    │           │   ├── 📑 WebConfig.java
    │           │   ├── 📑 WebSocketConfig.java
    │           │   └── 📑 SecurityConfig.java
    │           ├── 🗂 controller
    │           │   ├── 🗂 dto - 생략
    │           │   ├── 📑 EventController.java
    │           │   ├── 📑 MemberController.java
    │           │   ├── 📑 SheetController.ja
    │           │   └── 📑 StoreController.java
    │           ├── 🗂 domain
    │           │   ├── 📑 BaseTimeEntity.java
    │           │   ├── 📑 Comment.java
    │           │   ├── 📑 Event.java
    │           │   ├── 📑 Member.java
    │           │   ├── 📑 Recomment.java
    │           │   ├── 📑 Sheet.java
    │           │   └── 📑 Store.java
    │           ├── 🗂 dto ── 생략
    │           ├── 🗂 exception
    │           │   ├── 📑 CustomException.java
    │           │   ├── 📑 ErrorCode.java
    │           │   ├── 📑 ErrorResponse.java
    │           │   └── 📑 GlobalExceptionHandler.java
    │           ├── 🗂 repository
    │           │   ├── 📑 CommentRepository.java
    │           │   ├── 📑 EventRepository.java
    │           │   ├── 📑 MemberRepository.java
    │           │   ├── 📑 RecommentRepository.java
    │           │   ├── 📑 SheetRepository.java
    │           │   └── 📑 StoreRepository.java
    │           ├── 🗂 service
    │           │   ├── 📑 CommentService.java
    │           │   ├── 📑 EventService.java
    |           │   ├── 📑 ImageUploadService.java
    │           │   ├── 📑 MemberService.java
    │           │   ├── 📑 OAuthUserService.java
    │           │   ├── 📑 SheetService.java
    │           └── └── 📑 StoreService.java
    └── 🗂 resources
        ├── 📑 application.properties
        ├── 📑 application-aws.properties
        └── 📑 application-oauth.properties
</code>
</pre>


## 🍰 데이터베이스 설계도(E-R diagram)
<img src = "https://github.com/Bakery-EFUB/Bakery-Back/blob/develop/erd-diagram.PNG"/>

## 🍰 API 명세서
### [🔗 Link](https://www.notion.so/efub/API-6461422a295b47ee831e14a51340c2a0)


