# 스프링 부트 + Mysql + jpa 게시판

## 환경
* 편집기 : intellij
* 데이터베이스 : mysql
* 빌드도구 : gradle

## 기간
1월 16일 ~ 1월 24일 제작

## 프로젝트 설명

3반 친구들과 사용할 게시판 제작

### 로그인 화면
![캡처](https://user-images.githubusercontent.com/46079017/105654052-8b801d80-5f00-11eb-9d90-d74e60a9c8db.PNG)

* 로그인 성공시 게시판으로 이동
* 로그인 실패시 다시 돌아옴
* 로그인을 하지않으면 URI을 알아도 진입 불가 ( 스프링 시큐리티 사용 )

### 게시판 화면
![캡처](https://user-images.githubusercontent.com/46079017/105654479-722ba100-5f01-11eb-8346-88d502bbbebc.PNG)

* 로그인 성공시 게시판이 보임
* 회원,관리자 둘다 게시판 작성 가능 ( MEMBER, ADMIN )
* 로고를 누르면 게시판으로 이동
* 제목을 누르면 해당 게시물로 이동함

### 게시물 글쓰기
![캡처](https://user-images.githubusercontent.com/46079017/105654658-d484a180-5f01-11eb-8ffc-ffbca321a928.PNG)

* 게시물을 등록이 가능함
* 글씨체, 글자 크기, 유튜브 동영상 첨부, 이미지 업로드 가능

### 게시물 조회
![캡처](https://user-images.githubusercontent.com/46079017/105654940-68ef0400-5f02-11eb-941a-39797404694b.PNG)

* 게시물에 등록했던 사진, 동영상, 내용 확인가능
* 게시물 자신이 댓글 쓸 경우 초록색으로 표시
* 다른사람이 쓴것은 회색 표시

