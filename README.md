[![develop CI check](https://github.com/JisooPyo/wanted-pre-onboarding-backend/actions/workflows/develop-CI.yml/badge.svg)](https://github.com/JisooPyo/wanted-pre-onboarding-backend/actions/workflows/develop-CI.yml)

# wanted-pre-onboarding-backend
Wanted 프리온보딩 백엔드 인턴십 선발과제

<br>

## 📝 과제 안내

> 다음의 서비스 개요 및 요구사항을 만족하는 API 서버를 구현
 
#### 서비스 개요
* 본 서비스는 기업의 채용을 위한 웹 서비스이다.
* 회사는 채용공고를 생성하고, 이에 사용자는 지원한다.

<br>

## ✅ 요구사항

###### 각 토글을 클릭하면 상세사항을 확인할 수 있습니다.

<details>
  <summary>✔ 필수 요구사항</summary>

<br>

1. 채용공고를 등록한다.
2. 채용공고를 수정한다. (회사 id 이외 모두 수정이 가능하다.)
3. 채용공고를 삭제한다. (DB에서 삭제된다.)
4. 채용공고 목록을 가져온다. - 사용자는 채용공고 목록을 확인할 수 있다.
5. 채용 상세 페이지를 가져온다. - '채용 내용'이 추가적으로 담겨 있다.
6. ORM 사용하여 구현
7. RDBMS 사용 (SQLite, PostgreSQL, MySQL 등)
    
</details>

<details>
  <summary>✔ 선택 요구사항</summary>

<br>

1. 채용공고 검색 기능 구현
2. 채용 상세페이지에서 해당 회사가 올린 채용공고가 추가적으로 포함된다.
3. 사용자는 채용공고에 지원한다. - 사용자는 1회만 지원 가능하다.

</details>

<details>
  <summary>✔ 참조사항</summary>

<br>

1. 필요한 모델 : 회사, 사용자, 채용공고, 지원내역(선택사항)
2. 회사, 사용자 등록 절차 생략
3. 로그인 등 사용자 인증절차 생략
4. FrontEnd는 개발 범위에 제외

</details>

<details>
  <summary>✔ 가산점 요소</summary>

<br>

1. 선택 요구사항 구현
2. Unit Test 구현
3. README에 요구사항 분석 및 구현 과정 작성
4. Git commit 메시지 컨벤션
</details>

<br>

## ☝ Git 컨벤션

###### 토글을 클릭하여 확인할 수 있습니다.

<details>
  <summary>✔ commit </summary>

<br>
  
1. 형식

```
init: 기능 구현 시 필요한 파일 생성  → init: 회원가입 파일 생성
fix: 버그 수정                      → fix: 로그인 기능 오류 수정
docs: 문서 수정                     → docs: readme 오타 수정
update: 기능 구현 중 수정           → update: 로그인 기능 수정
complete: 기능 구현 완료            → complete: 회원가입 완료
refactor: 리팩터링                 → refactor: ~~ 개선
chore: 그 외 자잘한 수정            → chore: 코드 정렬
```

2. 꼬릿말

부가 설명이 필요할 때 추가

</details>

<details>
  <summary>✔ branch</summary>

<br>

1. `main` -> 배포 브랜치
2. `develop` -> 개발 테스트 브랜치
3. 형식

```
ex. 기능 추가: feature/이슈번호/{기능 설명}
ex. 버그 수정: hotfix/이슈번호/{짧은 설명}
ex. 문서 관리: docs/이슈번호/readme
ex. 리팩터링:  refactor/이슈번호/{리팩터링 짧은 설명}
ex. 테스트 코드: test/이슈번호/{짧은 설명}
```
4. merge된 브랜치는 기능 완성 시 삭제

</details>

<details>
  <summary>✔ 기타</summary>
  
<br>

1. PR
  * PR Template 사용
  * 방식 : squash and merge
  * 제목 : 깃 commit 형식을 따르되, 기능 추가는 `feature: `를 사용한다.
   
</details>

<br>

## 🔗 ERD

<img src="https://github.com/JisooPyo/wanted-pre-onboarding-backend/assets/130378232/650ae18e-a25d-4add-8bf1-581cd874b3b6" width=900px>

<br><br>

## 📋 REST API

노션 링크 클릭 -> [REST API 보러 가기](https://sugary-curtain-79b.notion.site/REST-API-e0cec84e85a545bfb788090f3d56b799?pvs=4)

<br>

## 🛠 기술 스택

<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Querydsl-0769AD?style=flat-square&logo=&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Github Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white">&nbsp;

<br>

<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">

<br>

## 👩‍💻 구현 내용

###### 링크를 누르면 해당 PR로 이동합니다.

- [x] [채용공고 등록](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/11)
- [X] [채용공고 수정](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/12)
- [x] [채용공고 삭제](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/15)
- [x] [모든 채용공고 조회](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/18)
- [x] [채용공고 상세 조회 - 해당 회사가 올린 다른 채용 공고가 추가적으로 포함됨](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/19)
- [x] [채용공고 지원](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/22)
- [x] [채용공고 검색](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/23)
- [x] [채용공고 등록 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/24)
- [x] [채용공고 수정 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/35)
- [x] [채용공고 삭제 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/36)
- [x] [모든 채용공고 목록 조회 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/37)
- [x] [채용공고 상세 조회 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/38)
- [x] [채용공고 지원 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/39)
- [x] [채용 공고 검색 API unit test](https://github.com/JisooPyo/wanted-pre-onboarding-backend/pull/40)
