# 스터디 커뮤니티

<br />

## 🔗 서비스설명
<br>
스터디를 원하는 사용자들을 위한 커뮤니티 플랫폼입니다. 
사용자들은 자신이 원하는 주제로 스터디 그룹을 개설하고, 다른 사용자들과 함께 학습 목표를 설정하고 달성할 수 있습니다. 
또한, 개발 관련 질문과 답변을 주고받아 지식을 공유할 수 있는 공간을 제공합니다.

<br>
<br />

## 🗓️ 프로젝트 기간

<br />

2024.03.25 ~ 2024.04.14

<br />
<br />
<br />

## 🔨 Skill Stacks
- JAVA 
- SpringBoot Framework
- Spring JPA
- Spring Security
- Mysql
  
<br/>
<br />

## 🔗 프로젝트 구현 내용
- 📂[[블로그 참고]](https://tmdgus416.tistory.com/182)
<br>

**질문, 댓글, 스터디 CRUD 기능 개발**
- Spring MVC 아키텍처를 활용하여 CRUD(Create, Read, Update, Delete) 기능을 구현
- Controller에서 HTTP 요청을 처리하여 모델과 뷰를 연결하였으며, 각각의 요청에 대응하는 핸들러 메서드를 정의하여 비즈니스 로직을 수행

<br/>

**검색 기능**
- 웹 애플리케이션에서 효율적인 검색 기능을 구현하기 위해 Pagination(페이지네이션)을 활용
- Spring MVC 아키텍처에서 컨트롤러를 통해 클라이언트의 요청을 처리하고, 비즈니스 로직을 수행하는 핸들러 메서드를 정의
- 데이터베이스에서 데이터를 검색하고 Pagination을 적용하기 위해 Spring Data JPA를 사용

<br/>

**태그 기능**
- JPA를 활용하여 태그와 게시글, 태그와 스터디 간의 다대다 관계를 매핑, @ManyToOne 및 @OneToMany 애노테이션을 사용하여 관계 설정하고 데이터베이스 내에서 효율적인 태그 관리 및 검색을 가능하게 함
- 각 스터디 그룹의 태그를 관리하기 위해 HashSet을 사용하여 중복을 방지하고 태그의 존재 여부를 빠르게 확인함

<br/>

**좋아요 기능**
- ddd

<br/>

**회원가입, 로그인**
- JWT(Json Web Token)을 활용하여 로그인 인증 기능을 구현
- Spring Security를 활용하여 애플리케이션의 보안을 강화함

<br>
<br/>
<br/>

## 📸 구현 결과

## 📸 화면 구성

|Post 페이지|Post 등록|
|:---:|:---:|
|![MAINPAGE](https://github.com/yunkr/Study-Community/assets/99308074/153d63fc-5634-434d-80a0-93012ef37144)|![signup_login](https://github.com/codestates-seb/seb44_main_021/assets/99308074/4455e006-1cae-4e2c-8408-6263922ae2f3)|
|<b>업사이클링 페이지</b>|<b>업사이클링 펀딩 글 등록</b>|
|![upcycling](https://github.com/codestates-seb/seb44_main_021/assets/99308074/3af83feb-b433-41a0-b3b1-059360f29924)|![2023_07_25_00_10_57_249 mp4_20230725_002629](https://github.com/codestates-seb/seb44_main_021/assets/99308074/fdd0ce8e-7f46-4828-9eb1-01adba485dee)|
|<b>업사이클링 펀딩 글 확인</b>|<b>업사이클링 펀딩 수정/삭제</b>|
|![UPCYCLING1](https://github.com/codestates-seb/seb44_main_021/assets/99308074/d54c2c69-6b3a-438b-a862-300ab998f1f1)|![upcycling2](https://github.com/codestates-seb/seb44_main_021/assets/99308074/9af48b56-6bb6-497d-9484-f0a4e4106f3c)|
|<b>상품 페이지</b>|<b>상품 등록</b>|
|![upcycling](https://github.com/codestates-seb/seb44_main_021/assets/99308074/ce9b0ce6-241a-462e-af23-d60bd6ec3efd)|![fe6ba8af5e93dd3e](https://github.com/codestates-seb/seb44_main_021/assets/99308074/f217ce16-7b7f-4d2b-be07-5956889e2e08)|
|<b>상품 확인</b>|<b>상품 수정/삭제</b>|
|![ce3b139fe88a1391](https://github.com/codestates-seb/seb44_main_021/assets/99308074/44afa4a3-eac6-4583-9c9d-a7816974e64e)|![debf89bd9e9cb7bf](https://github.com/codestates-seb/seb44_main_021/assets/99308074/739b9603-bb2a-4262-82e2-293a1a951189)|
|<b>마이페이지</b>|<b>About</b>|
|![mypage](https://github.com/codestates-seb/seb44_main_021/assets/99308074/f83d520d-2b90-4fac-ad4e-8e2e7a3701f5)|![about](https://github.com/codestates-seb/seb44_main_021/assets/99308074/12fab642-e582-47a8-bb2f-38e87c987c8d)|

<br>
<br/>
<br/>


## 📂 ERD
https://www.erdcloud.com/d/cySJLYNBBhnwXtCDp
<br>
<br>
<img src="https://github.com/yunkr/Study-Community/assets/99308074/409476bf-32fd-46cf-af5c-abd0cb23caa1">

<br/>
<br/>

## 📃 Docs
🔗 [사용자 요구사항 정의서](https://tmdgus416.tistory.com/179)
<br>

<br/>
<br/>



