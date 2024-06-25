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

Post 등록
<img src="https://github.com/yunkr/Study-Community/assets/99308074/a3662684-4dfe-48a0-8eea-a66c8e890b89">

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



